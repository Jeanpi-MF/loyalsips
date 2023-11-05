package com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record RequestPointResource(@NotNull
                                   String customerRecordId,
                                   @NotNull
                                   Long barId) {


}