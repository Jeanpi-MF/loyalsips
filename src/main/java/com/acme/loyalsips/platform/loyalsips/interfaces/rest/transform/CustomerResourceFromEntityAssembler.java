package com.acme.loyalsips.platform.loyalsips.interfaces.rest.transform;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Customer;
import com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources.CustomerResource;

public class CustomerResourceFromEntityAssembler {

    public static CustomerResource toCreateCustomerResource(Customer entity) {
        return new CustomerResource(entity.getCustomerRecordId(), entity.getProfileId());
    }

}
