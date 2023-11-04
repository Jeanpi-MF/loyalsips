package com.acme.loyalsips.platform.loyalsips.application.internal.commandservices;



import com.acme.loyalsips.platform.loyalsips.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Customer;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CreateCustomerCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;
import com.acme.loyalsips.platform.loyalsips.domain.services.CustomerCommandService;
import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
