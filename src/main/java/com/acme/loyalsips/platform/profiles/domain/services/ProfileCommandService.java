package com.acme.loyalsips.platform.profiles.domain.services;

import com.acme.loyalsips.platform.profiles.domain.model.commands.CreateProfileCommand;

public interface ProfileCommandService {
    Long handle(CreateProfileCommand command);
}
