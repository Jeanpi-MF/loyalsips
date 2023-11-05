package com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform;

import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CreateCustomerCommand;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.CreateCustomerResource;

public class CreateCustomerCommandFromResourceAssembler {
    public static CreateCustomerCommand toCommandFromResource(CreateCustomerResource resource) {
        return new CreateCustomerCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.street(),
                resource.number(),
                resource.city(),
                resource.country()
        );
    }
}
