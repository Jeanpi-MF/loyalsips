package com.acme.loyalsips.platform.loyalsips.interfaces.rest;

import com.acme.loyalsips.platform.loyalsips.domain.model.commands.DeleteBarCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetAllBarQuery;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetBarByIdQuery;
import com.acme.loyalsips.platform.loyalsips.domain.services.BarCommandService;
import com.acme.loyalsips.platform.loyalsips.domain.services.BarQueryService;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.BarResource;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.CreateBarResource;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.UpdateBarResource;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform.BarResourceFromEntityAssembler;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform.CreateBarCommandFromResourceAssembler;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform.UpdateBarCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/bars",produces = APPLICATION_JSON_VALUE)
@Tag(name = "Bar", description = "Bar Management Endpoint")
public class BarController {
    private final BarCommandService barCommandService;
    private final BarQueryService barQueryService;

    public BarController(BarCommandService barCommandService, BarQueryService barQueryService) {
        this.barCommandService = barCommandService;
        this.barQueryService = barQueryService;
    }

    @PostMapping
    public ResponseEntity<BarResource> createBar(@RequestBody CreateBarResource createBarResource) {
        var createBarCommand = CreateBarCommandFromResourceAssembler.toCommandFromResource(createBarResource);
        var barId = barCommandService.handle(createBarCommand);
        if (barId == 0L) return ResponseEntity.badRequest().build();
        var getBarByIdQuery = new GetBarByIdQuery(barId);
        var bar = barQueryService.handle(getBarByIdQuery);
        if (bar.isEmpty()) return ResponseEntity.notFound().build();
        var barResource = BarResourceFromEntityAssembler.toResource(bar.get());
        return new ResponseEntity<>(barResource, HttpStatus.CREATED);

    }

    @PostMapping("/{barId}")
    public ResponseEntity<BarResource> getBarById(@PathVariable Long barId) {
        var getBarByIdQuery = new GetBarByIdQuery(barId);
        var bar = barQueryService.handle(getBarByIdQuery);
        if (bar.isEmpty()) return ResponseEntity.notFound().build();
        var barResource = BarResourceFromEntityAssembler.toResource(bar.get());
        return new ResponseEntity<>(barResource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BarResource>>getAllBar() {
        var getAllBarQuery = new GetAllBarQuery();
        var bars = barQueryService.handle(getAllBarQuery);
        var barResources = bars.stream()
                .map(BarResourceFromEntityAssembler::toResource)
                .toList();
        return ResponseEntity.ok(barResources);

    }

    @PutMapping("/{barId}")
    public ResponseEntity<BarResource> updateBar(@PathVariable Long barId, @RequestBody UpdateBarResource updateBarResource) {
        var updateBarCommand = UpdateBarCommandFromResourceAssembler.toCommandFromResource(barId, updateBarResource);
        var updatedBar = barCommandService.handle(updateBarCommand);
        if (updatedBar.isEmpty()) return ResponseEntity.notFound().build();
        var barResource = BarResourceFromEntityAssembler.toResource(updatedBar.get());
        return ResponseEntity.ok(barResource);

    }
    @DeleteMapping("/{BarId}")
    public ResponseEntity<?> deleteBar(@PathVariable Long barId) {
        var deleteBarCommand = new DeleteBarCommand(barId);
        barCommandService.handle(deleteBarCommand);
        return ResponseEntity.ok("Bar with given id successfully deleted...");
    }


}
