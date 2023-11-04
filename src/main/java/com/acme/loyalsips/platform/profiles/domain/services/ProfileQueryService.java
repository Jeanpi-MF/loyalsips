package com.acme.loyalsips.platform.profiles.domain.services;

import com.acme.loyalsips.platform.profiles.domain.model.aggregates.Profile;
import com.acme.loyalsips.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.acme.loyalsips.platform.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByEmailQuery query);
    Optional<Profile> handle(GetProfileByIdQuery query);
}
