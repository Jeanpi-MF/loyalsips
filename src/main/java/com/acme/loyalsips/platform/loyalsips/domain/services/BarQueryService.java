package com.acme.loyalsips.platform.loyalsips.domain.services;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetAllBarQuery;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetBarByIdQuery;

import java.util.List;
import java.util.Optional;

public interface BarQueryService {
    Optional<Bar> handle(GetBarByIdQuery query);
    List<Bar> handle(GetAllBarQuery query);
}
