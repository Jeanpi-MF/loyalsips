package com.acme.loyalsips.platform.loyalsips.application.internal.commandservices;



import com.acme.loyalsips.platform.loyalsips.application.internal.outboundservices.acl.ExternalProfileService;
import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Customer;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CreateCustomerCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;
import com.acme.loyalsips.platform.loyalsips.domain.services.CustomerCommandService;
import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerCommandServiceImpl  implements CustomerCommandService {

    private final CustomerRepository customerRepository;

    private final ExternalProfileService externalProfileService;

    public CustomerCommandServiceImpl(CustomerRepository customerRepository, ExternalProfileService externalProfileService) {
        this.customerRepository = customerRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public AcmeCustomerRecordId handle(CreateCustomerCommand command){

        var profileId = externalProfileService.fetchProfileByEmail(command.email());

        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(command.firstName(), command.lastName(), command.email(), command.street(), command.number(), command.city(), command.country());
        } else {
            customerRepository.findByProfileId(profileId.get()).ifPresent(customer -> {
                throw new RuntimeException("Customer already exists");
            });
        }

        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");

        var customer = new Customer(profileId.get());
        customerRepository.save(customer);
        return customer.getAcmeCustomerRecordId();

    }


}
