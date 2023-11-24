package com.acme.loyalsips.platform.loyalsips.application.internal.queryservices;

import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Customer;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetCustomerByAcmeCustomerRecordIdQuery;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetCustomerByProfileIdQuery;
import com.acme.loyalsips.platform.loyalsips.domain.services.CustomerQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final CustomerRepository customerRepository;

    public CustomerQueryServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> handle(GetCustomerByProfileIdQuery query) {
        return customerRepository.findByProfileId(query.profileId());
    }

    @Override
    public Optional<Customer> handle(GetCustomerByAcmeCustomerRecordIdQuery query) {
        return customerRepository.findByAcmeCustomerRecordId(query.acmeCustomerRecordId());
    }


}
