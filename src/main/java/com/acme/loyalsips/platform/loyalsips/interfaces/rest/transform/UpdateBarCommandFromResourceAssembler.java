package com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform;

import com.acme.loyalsips.platform.loyalsips.domain.model.commands.UpdateBarCommand;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.UpdateBarResource;

public class UpdateBarCommandFromResourceAssembler {
    public static UpdateBarCommand toCommandFromResource(Long barId, UpdateBarResource resource) {
        return new UpdateBarCommand(barId, resource.name(), resource.description());
    }
}
