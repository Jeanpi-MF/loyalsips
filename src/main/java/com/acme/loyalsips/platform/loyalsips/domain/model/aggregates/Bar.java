package com.acme.loyalsips.platform.loyalsips.domain.model.aggregates;

import com.acme.loyalsips.platform.loyalsips.domain.model.entities.Offer;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.LoyalsipsPath;
import com.acme.loyalsips.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

@Entity
@Getter
public class Bar extends AuditableModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Embedded

    private final LoyalsipsPath loyalsipsPath;

    public Bar() {
        this.name = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.loyalsipsPath = new LoyalsipsPath();
    }
    /**
     * @param name
     * @param description
     */
    public Bar(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    /**
     * @param name
     * @param description
     * @return
     */
    public Bar updateInformation(String name, String description) {
        this.name = name;
        this.description = description;
        return this;
    }

    public void addOffer(Offer offer) {
        this.loyalsipsPath.addItem(this, offer);
    }
    public void addOfferTotalLoyalPath(Offer offer, Long nextOfferId) {
        this.loyalsipsPath.addItem(this, offer, nextOfferId);
    }

}