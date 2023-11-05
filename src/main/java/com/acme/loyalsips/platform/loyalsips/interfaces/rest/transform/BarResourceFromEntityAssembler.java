package com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.BarResource;

public class BarResourceFromEntityAssembler {
    public static BarResource toResource(Bar entity) {
        return new BarResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription());
    }

}
