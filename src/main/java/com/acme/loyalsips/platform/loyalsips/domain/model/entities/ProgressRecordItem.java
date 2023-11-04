package com.acme.loyalsips.platform.loyalsips.domain.model.entities;


import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Point;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.ProgressStatus;
import com.acme.loyalsips.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class ProgressRecordItem extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "point_id")
    private Point point;

    @Getter
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;


    private ProgressStatus status;

    private Date startedAt;

    private Date completedAt;

    public ProgressRecordItem() {
    }

    public ProgressRecordItem(Point point, Offer offer) {
        this.point = point;
        this.offer = offer;
        this.status = ProgressStatus.NOT_STARTED;
    }

    public void start() {
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }

    public void complete() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }

    public boolean isInProgress() {
        return this.status == ProgressStatus.STARTED;
    }

    public boolean isCompleted() {
        return this.status == ProgressStatus.COMPLETED;
    }

    public boolean isNotStarted() {
        return this.status == ProgressStatus.NOT_STARTED;
    }

    public long calculateDaysElapsed() {

        if (this.isNotStarted()) {
            return 0;
        }

        var defaultTimeZone = java.time.ZoneId.systemDefault();
        // Only started items are registered, so it should be the started date
        var fromDate = this.startedAt.toInstant();
        // If completed it should be the completed date, otherwise it should be today
        var toDate = this.isCompleted() ? this.completedAt.toInstant() : LocalDate.now().atStartOfDay(defaultTimeZone).toInstant();

        return java.time.Duration.between(fromDate, toDate).toDays();

    }
}
