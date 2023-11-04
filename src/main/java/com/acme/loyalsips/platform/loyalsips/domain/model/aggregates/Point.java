package com.acme.loyalsips.platform.loyalsips.domain.model.aggregates;


import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.PointStatus;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.ProgressRecord;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Point extends AbstractAggregateRoot<Point> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "bar_id")
    private Bar bar;

    @Getter
    @Embedded
    private AcmeCustomerRecordId acmeCustomerRecordId;

    private PointStatus status;

    @Embedded
    private ProgressRecord progressRecord;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Point() {

    }
    public Point(AcmeCustomerRecordId customerRecordId, Bar bar) {
        this.acmeCustomerRecordId = customerRecordId;
        this.bar = bar;
        this.status = PointStatus.REQUESTED;
        this.progressRecord = new ProgressRecord();
    }

    public void confirm() {
        this.status = PointStatus.CONFIRMED;
        // this.registerEvent(new EnrollmentConfirmedEvent(this));
    }
    public void reject() {
        this.status = PointStatus.REJECTED;
        // this.registerEvent(new EnrollmentRejectedEvent(this));
    }
    public void cancel() {
        this.status = PointStatus.CANCELLED;
        // this.registerEvent(new EnrollmentCancelledEvent(this));
    }
    public String getStatus() {
        return status.name().toLowerCase();
    }
    public long calculateDaysElapsed() {
        return progressRecord.calculateDaysElapsedForEnrollment(this);
    }

    public boolean isConfirmed() {
        return status == PointStatus.CONFIRMED;
    }

    public boolean isRejected() {
        return status == PointStatus.REJECTED;
    }

}