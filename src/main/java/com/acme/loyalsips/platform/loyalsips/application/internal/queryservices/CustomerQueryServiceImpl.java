package com.acme.loyalsips.platform.loyalsips.application.internal.queryservices;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Customer;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetCustomerByAcmeCustomerRecordIdQuery;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetCustomerByProfileIdQuery;
import com.acme.loyalsips.platform.loyalsips.domain.services.CustomerQueryService;
import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

 
