package com.acme.loyalsips.platform.loyalsips.domain.services;

import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CreateCustomerCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;

public interface CustomerCommandService {
    AcmeCustomerRecordId handle(CreateCustomerCommand command);
}
