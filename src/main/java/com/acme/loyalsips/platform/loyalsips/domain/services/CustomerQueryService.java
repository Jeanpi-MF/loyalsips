package com.acme.loyalsips.platform.loyalsips.domain.services;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Customer;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetCustomerByAcmeCustomerRecordIdQuery;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetCustomerByProfileIdQuery;

import java.util.Optional;

public interface CustomerQueryService {
    Optional<Customer> handle(GetCustomerByProfileIdQuery query);
    Optional<Customer> handle(GetCustomerByAcmeCustomerRecordIdQuery query);

}
