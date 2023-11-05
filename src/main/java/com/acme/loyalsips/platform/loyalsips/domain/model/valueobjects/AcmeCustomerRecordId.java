package com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable

public record AcmeCustomerRecordId(String customerRecordId) {
    public AcmeCustomerRecordId(){this(UUID.randomUUID().toString());}

    public AcmeCustomerRecordId{
        if(customerRecordId == null || customerRecordId.isBlank()){
            throw new IllegalArgumentException("Customer record id cannot be null or blank");
        }
    }
}
