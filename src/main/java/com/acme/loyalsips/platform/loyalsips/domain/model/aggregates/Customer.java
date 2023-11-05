package com.acme.loyalsips.platform.loyalsips.domain.model.aggregates;

import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.CustomerPerformanceMetricSet;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.ProfileId;
import com.acme.loyalsips.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Customer extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    @Column(name = "acme_customer_id")
    private AcmeCustomerRecordId acmeCustomerRecordId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private CustomerPerformanceMetricSet performanceMetrics;

    public Customer() {
        this.acmeCustomerRecordId = new AcmeCustomerRecordId();
        this.performanceMetrics = new CustomerPerformanceMetricSet();
    }

    public Customer(ProfileId profileId) {
        this();
        this.profileId = profileId;
    }

    public void updateMetricsOnCourseCompleted() {
        this.performanceMetrics = this.performanceMetrics.incrementTotalCompletedBars();
    }

    public void updateMetricsOnTutorialCompleted() {
        this.performanceMetrics = this.performanceMetrics.incrementTotalOffers();
    }

    public String getCustomerRecordId() {
        return this.acmeCustomerRecordId.customerRecordId();
    }

    public Long getProfileId() {
        return this.profileId.profileId();
    }

}