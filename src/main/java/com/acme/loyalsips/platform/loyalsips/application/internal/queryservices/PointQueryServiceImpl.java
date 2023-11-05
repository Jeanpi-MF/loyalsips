package com.acme.loyalsips.platform.loyalsips.application.internal.queryservices;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Point;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetCustomerPointsQuery;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetPointByIdQuery;
import com.acme.loyalsips.platform.loyalsips.domain.services.PointQueryService;
import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.PointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PointQueryServiceImpl implements PointQueryService {

    private final PointRepository pointRepository;

    public PointQueryServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public List<Point> handle(GetCustomerPointsQuery query) {
        return pointRepository.findAllByAcmeCustomerRecordId(query.customerRecordId());
    } 

    @Override
    public Optional<Point> handle(GetPointByIdQuery query) {
        return pointRepository.findById(query.pointId());
    }

}
