package com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform;

import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CreateBarCommand;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.CreateBarResource;

public class CreateBarCommandFromResourceAssembler {
    public static CreateBarCommand toCommandFromResource(CreateBarResource resource) {
        return new CreateBarCommand(
                resource.name(),
                resource.description()
        );
    }
}
