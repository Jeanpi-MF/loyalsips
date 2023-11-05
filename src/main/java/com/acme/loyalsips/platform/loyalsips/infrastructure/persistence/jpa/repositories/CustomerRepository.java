package com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Customer;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByAcmeCustomerRecordId(AcmeCustomerRecordId CustomerRecordId);

    Optional<Customer> findByProfileId(ProfileId profileId);



}
