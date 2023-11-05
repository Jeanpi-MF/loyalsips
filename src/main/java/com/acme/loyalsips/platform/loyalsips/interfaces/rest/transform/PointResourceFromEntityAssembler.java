package com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Point;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.PointResource;

public class PointResourceFromEntityAssembler {
    public static PointResource toResourceFromEntity(Point point){
        return new PointResource(
                point.getId(),
                point.getAcmeCustomerRecordId().customerRecordId(),
                point.getBar().getId(),
                point.getStatus()
        );
    }


}
