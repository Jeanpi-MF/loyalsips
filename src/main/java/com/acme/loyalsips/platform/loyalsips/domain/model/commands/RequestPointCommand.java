package com.acme.loyalsips.platform.loyalsips.domain.model.commands;

import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;

public record RequestPointCommand(AcmeCustomerRecordId customerRecordId, Long pointId) {
}