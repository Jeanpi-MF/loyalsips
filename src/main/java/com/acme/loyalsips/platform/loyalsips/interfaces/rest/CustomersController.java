package com.acme.loyalsips.platform.loyalsips.interfaces.rest;

import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetCustomerByAcmeCustomerRecordIdQuery;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;
import com.acme.loyalsips.platform.loyalsips.domain.services.CustomerCommandService;
import com.acme.loyalsips.platform.loyalsips.domain.services.CustomerQueryService;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.CreateCustomerResource;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.CustomerResource;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform.CreateCustomerCommandFromResourceAssembler;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform.CustomerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Customers", description = "Customer Management Endpoints")
public class CustomersController {
    private final CustomerCommandService customerCommandService;
    private final CustomerQueryService customerQueryService;

    public CustomersController(CustomerCommandService customerCommandService, CustomerQueryService customerQueryService) {
        this.customerCommandService = customerCommandService;
        this.customerQueryService = customerQueryService;
    }

    /**
     * Creates a new student
     * @param resource the resource with the student data
     * @return the student resource
     */
    @PostMapping
    public ResponseEntity<CustomerResource> createCustomer(@RequestBody CreateCustomerResource resource) {
        var createCustomerCommand = CreateCustomerCommandFromResourceAssembler.toCommandFromResource(resource);
        var customerId = customerCommandService.handle(createCustomerCommand);
        if (customerId.customerRecordId().isEmpty()) return ResponseEntity.badRequest().build();
        var getCustomerByAcmeCustomerRecordIdQuery = new GetCustomerByAcmeCustomerRecordIdQuery(customerId);
        var customer = customerQueryService.handle(getCustomerByAcmeCustomerRecordIdQuery);
        if (customer.isEmpty()) return ResponseEntity.badRequest().build();
        var customerResource = CustomerResourceFromEntityAssembler.toCreateCustomerResource(customer.get());
        return new ResponseEntity<>(customerResource, HttpStatus.CREATED);
    }
    /**
     * Gets a student by its acme student record id
     * @param customerRecordId the acme student record id
     * @return the student resource for the given record id
     */
    @GetMapping("/{customerRecordId}")
    public ResponseEntity<CustomerResource> getCustomerByAcmeCustomerRecordId(@PathVariable String customerRecordId) {
        var acmeCustomerRecordId = new AcmeCustomerRecordId(customerRecordId);
        var getCustomerByAcmeCustomerRecordIdQuery = new GetCustomerByAcmeCustomerRecordIdQuery(acmeCustomerRecordId);
        var customer = customerQueryService.handle(getCustomerByAcmeCustomerRecordIdQuery);
        if (customer.isEmpty()) return ResponseEntity.notFound().build();
        var customerResource = CustomerResourceFromEntityAssembler.toCreateCustomerResource(customer.get());
        return ResponseEntity.ok(customerResource);
    }

}
