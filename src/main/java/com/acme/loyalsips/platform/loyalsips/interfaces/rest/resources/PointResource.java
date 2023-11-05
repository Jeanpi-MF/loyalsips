package com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources;

public record PointResource(Long pointId,
                            String customerRecordId,
                            Long offerId,
                            String status) {
}
