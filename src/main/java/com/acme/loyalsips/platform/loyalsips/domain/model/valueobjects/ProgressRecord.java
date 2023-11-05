package com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects;


import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Point;
import com.acme.loyalsips.platform.loyalsips.domain.model.entities.Offer;
import com.acme.loyalsips.platform.loyalsips.domain.model.entities.ProgressRecordItem;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ProgressRecord {
    @OneToMany(mappedBy = "point")
    private List<ProgressRecordItem> progressRecordItems;

    public ProgressRecord() {
        progressRecordItems = new ArrayList<>();
    }

    public void initializeProgressRecord(Point pointId, LoyalsipsPath loyalsipsPath) {
        Offer offerId = loyalsipsPath.getFirstTutorialInLearningPath();
        ProgressRecordItem progressRecordItem = new ProgressRecordItem(pointId, offerId);
        progressRecordItems.add(progressRecordItem);
    }

    private ProgressRecordItem getProgressRecordItemWithTutorialId(Long offerId) {
        return progressRecordItems.stream()
                .filter(progressRecordItem -> progressRecordItem.getOffer()
                        .getId().equals(offerId)).findFirst().orElse(null);
    }

    private boolean hasAnItemInProgress() {
        return progressRecordItems.stream()
                .anyMatch(ProgressRecordItem::isInProgress);
    }

    public void startOffer(Long offerId) {
        if (hasAnItemInProgress()) throw new IllegalStateException("A Offer is already in progress");
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(offerId);
        if (progressRecordItem != null) {
            if (progressRecordItem.isNotStarted()) progressRecordItem.start();
            else throw new IllegalStateException("Offer with given Id is already started or completed");
        }
        else throw new IllegalArgumentException("Offer with given Id not found in progress record");
    }
    public void completeOffer(Long offerId, LoyalsipsPath loyalsipsPath) {
        ProgressRecordItem progressRecordItem = getProgressRecordItemWithTutorialId(offerId);
        if (progressRecordItem != null) progressRecordItem.complete();
        else throw new IllegalArgumentException("Offer with given Id not found in progress record");

        if (loyalsipsPath.isLastTutorialInLearningPath(offerId)) return;

        Offer nextOffer = loyalsipsPath.getNextTutorialInLearningPath(offerId);
        if (nextOffer != null) {
            ProgressRecordItem nextProgressRecordItem = new ProgressRecordItem(progressRecordItem.getPoint(), nextOffer);
            progressRecordItems.add(nextProgressRecordItem);
        }
    }
    public long calculateDaysElapsedForEnrollment(Point point) {
        return progressRecordItems.stream().filter(progressRecordItem ->
                        progressRecordItem.getPoint().equals(point))
                .mapToLong(ProgressRecordItem::calculateDaysElapsed).sum();
    }


}