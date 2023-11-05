package com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform;

import com.acme.loyalsips.platform.loyalsips.domain.model.commands.RequestPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.RequestPointResource;

public class RequestPointCommandFromResourceAssembler {
    public static RequestPointCommand toCommandFromResource(RequestPointResource resource) {
        return new RequestPointCommand(
                new AcmeCustomerRecordId(resource.customerRecordId()),
                resource.barId());
    }

}
