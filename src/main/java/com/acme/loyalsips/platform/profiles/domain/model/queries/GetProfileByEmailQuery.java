package com.acme.loyalsips.platform.profiles.domain.model.queries;

import com.acme.loyalsips.platform.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress emailAddress) {
}
