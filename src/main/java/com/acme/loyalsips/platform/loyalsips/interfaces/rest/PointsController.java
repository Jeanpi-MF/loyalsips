package com.acme.loyalsips.platform.loyalsips.interfaces.rest;

import com.acme.loyalsips.platform.loyalsips.domain.services.PointCommandService;
import com.acme.loyalsips.platform.loyalsips.domain.services.PointQueryService;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.PointResource;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.RequestPointResource;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform.PointResourceFromEntityAssembler;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform.RequestPointCommandFromResourceAssembler;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CancelPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.ConfirmPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.RejectPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetPointByIdQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/points")
@Tag(name = "Points", description = "Point Management Endpoints")
public class PointsController {

    private final PointCommandService pointCommandService;

    private final PointQueryService pointQueryService;

    public PointsController(PointCommandService pointCommandService, PointQueryService pointQueryService) {
        this.pointCommandService = pointCommandService;
        this.pointQueryService = pointQueryService;
    }

    @PostMapping
    public ResponseEntity<PointResource> requestEnrollment(@RequestBody RequestPointResource resource) {
        var command = RequestPointCommandFromResourceAssembler.toCommandFromResource(resource);
        var pointId = pointCommandService.handle(command);
        if (pointId == 0L) return ResponseEntity.badRequest().build();
        var getPointByIdQuery = new GetPointByIdQuery(pointId);
        var point = pointQueryService.handle(getPointByIdQuery);
        if (point.isEmpty()) return ResponseEntity.badRequest().build();
        var pointResource = PointResourceFromEntityAssembler.toResourceFromEntity(point.get());
        return new ResponseEntity<>(pointResource, HttpStatus.CREATED);

    }

    @PostMapping("/{pointId}/confirmations")
    public ResponseEntity<?> confirmPoint(@PathVariable Long pointId) {
        var command = new ConfirmPointCommand(pointId);
        var confirmedPointId = pointCommandService.handle(command);
        return ResponseEntity.ok(confirmedPointId);
    }

    @PostMapping("/{pointId}/rejections")
    public ResponseEntity<?> rejectPoint(@PathVariable Long pointId) {
        var command = new RejectPointCommand(pointId);
        var rejectedPointId = pointCommandService.handle(command);
        return ResponseEntity.ok(rejectedPointId);
    }

    @PostMapping("/points{pointId}/cancellations")
    public ResponseEntity<?> cancelPoint(@PathVariable Long pointId) {
        var command = new CancelPointCommand(pointId);
        var canceledPointId = pointCommandService.handle(command);
        return ResponseEntity.ok(canceledPointId);
    }

}



