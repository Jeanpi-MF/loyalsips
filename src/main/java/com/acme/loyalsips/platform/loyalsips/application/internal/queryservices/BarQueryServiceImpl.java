package com.acme.loyalsips.platform.loyalsips.application.internal.queryservices;

import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.BarRepository;
import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetAllBarQuery;
import com.acme.loyalsips.platform.loyalsips.domain.model.queries.GetBarByIdQuery;
import com.acme.loyalsips.platform.loyalsips.domain.services.BarQueryService;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional;

@Service
public class BarQueryServiceImpl implements BarQueryService {
    private final BarRepository barRepository;

    public BarQueryServiceImpl(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

    @Override
    public Optional<Bar> handle(GetBarByIdQuery query) {
        return barRepository.findById(query.barId());
    }

    @Override
    public List<Bar> handle(GetAllBarQuery query) {
        return barRepository.findAll();
    }
}
