package com.acme.loyalsips.platform.loyalsips.domain.model.entities;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import com.acme.loyalsips.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

@Entity

public class LoyalsipsPathItem extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bar_id")
    private Bar bar;

    @Getter
    @ManyToOne
    @JoinColumn(name = "next_item_id")
    private LoyalsipsPathItem nextItem;

    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(name = "offer_id")
    private Offer offer;

    public LoyalsipsPathItem(Bar bar, Offer offer, LoyalsipsPathItem nextItem) {
        this.bar = bar;
        this.offer = offer;
        this.nextItem = nextItem;
    }

    public LoyalsipsPathItem() {
        this.offer = null;
        this.nextItem = null;
    }

    public void updateNextItem(LoyalsipsPathItem nextItem) {
        this.nextItem = nextItem;
    }
}