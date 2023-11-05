package com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects;

public record ProfileId(Long profileId) {

    public ProfileId() {
        this(0L);
    }

    public ProfileId {
        if (profileId < 0) {
            throw new IllegalArgumentException("Profile Id cannot be negative");
        }
    }
}