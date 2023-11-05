package com.acme.loyalsips.platform.loyalsips.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter

public class OfferFinishEvent extends ApplicationEvent {
    private final Long offerId;
    private final Long pointId;

    public OfferFinishEvent(Object source, Long offerId, Long pointId) {
        super(source);
        this.offerId = offerId;
        this.pointId = pointId;
    }

}
