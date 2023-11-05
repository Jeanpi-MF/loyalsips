package com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Point;
import com.acme.loyalsips.platform.loyalsips.domain.model.valueobjects.AcmeCustomerRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByAcmeCustomerRecordId(AcmeCustomerRecordId acmeCustomerRecordId);
}
