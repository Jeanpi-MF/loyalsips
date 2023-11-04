package com.acme.loyalsips.platform.loyalsips.domain.services;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Point;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetCustomerPointsQuery;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetPointByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PointQueryService {

    List<Point> handle(GetCustomerPointsQuery query);
    Optional<Point> handle(GetPointByIdQuery query);
}
