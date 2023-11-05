package com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import com.acme.loyalsips.platform.loyalsips.domain.model.entities.LoyalsipsPathItem;
import com.acme.loyalsips.platform.loyalsips.domain.model.entities.Offer;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class LoyalsipsPath {

    @OneToMany(mappedBy = "bar")
    private List<LoyalsipsPathItem> loyalsipsPathsItems;

    public LoyalsipsPath() {
        this.loyalsipsPathsItems = new ArrayList<>();
    }

    public void addItem(Bar bar, Offer offer, LoyalsipsPathItem nextItem) {
        LoyalsipsPathItem loyalsipsPathItem = new LoyalsipsPathItem(bar, offer, nextItem);
        this.loyalsipsPathsItems.add(loyalsipsPathItem);
    }

    public void addItem(Bar bar, Offer offer) {
        LoyalsipsPathItem originalLastItem = getLastItemInLoyalsipsPath();

        LoyalsipsPathItem loyalsipsPathItem = new LoyalsipsPathItem(bar, offer, null);
        loyalsipsPathsItems.add(loyalsipsPathItem);
        if (originalLastItem != null) originalLastItem.updateNextItem(loyalsipsPathItem);
    }
    public void addItem(Bar bar, Offer offer, Long nextOfferId){

        LoyalsipsPathItem nextItem = getLLoyalsipsPathItemWithId(nextOfferId);
        addItem(bar, offer, nextItem);
    }

    public Long getFirstOfferIdInLoyalsipsPath() {
        return loyalsipsPathsItems.get(0).getOffer().getId();
    }

    public Offer getFirstTutorialInLearningPath() {
        return loyalsipsPathsItems.get(0).getOffer();
    }

    public Offer getNextTutorialInLearningPath(Long currentOfferId) {
        LoyalsipsPathItem item = getLearningPathItemWithTutorialId(currentOfferId).getNextItem();
        return item != null ? item.getOffer() : null;
    }
    public boolean isLastTutorialInLearningPath(Long currentOfferId) {
        return getLearningPathItemWithTutorialId(currentOfferId).getNextItem() == null;
    }
    private LoyalsipsPathItem getLLoyalsipsPathItemWithId(Long itemId) {
        return loyalsipsPathsItems.stream()
                .filter(loyalsipsPathsItems -> loyalsipsPathsItems.getId().equals(itemId))
                .findFirst().orElse(null);
    }
    private LoyalsipsPathItem getLearningPathItemWithTutorialId(Long offerId) {
        return loyalsipsPathsItems.stream()
                .filter(loyalsipsPathsItems -> loyalsipsPathsItems.getOffer().getId().equals(offerId))
                .findFirst().orElse(null);
    }

    private LoyalsipsPathItem getLastItemInLoyalsipsPath() {
        return loyalsipsPathsItems.stream().filter(item -> item.getNextItem() == null)
                .findFirst().orElse(null);
    }

}
