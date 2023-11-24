package com.acme.loyalsips.platform.loyalsips.application.internal.commandservices;

import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.BarRepository;
import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.PointRepository;
import com.acme.loyalsips.platform.loyalsips.domain.exceptions.CourseNotFoundException;
import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Point;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CancelPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.ConfirmPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.RejectPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.RequestPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.services.PointCommandService;
import org.springframework.stereotype.Service;

@Service
public class PointCommandServiceImpl implements PointCommandService {

    private final BarRepository barRepository;

    private final CustomerRepository customerRepository;

    private final PointRepository pointRepository;

    public PointCommandServiceImpl(BarRepository barRepository, CustomerRepository customerRepository, PointRepository pointRepository) {
        this.barRepository = barRepository;
        this.customerRepository = customerRepository;
        this.pointRepository = pointRepository;
    }

    @Override
    public Long handle(RequestPointCommand command) {
        customerRepository.findByAcmeCustomerRecordId(command.customerRecordId()).map(customer -> {
            Bar bar = barRepository.findById(command.pointId()).orElseThrow(() -> new CourseNotFoundException(command.pointId()));
            Point point = new Point(command.customerRecordId(), bar);
            pointRepository.save(point);
            return point.getId();
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
        return 0L;
    }

    @Override
    public Long handle(ConfirmPointCommand command) {
        pointRepository.findById(command.pointId()).map(point -> {
            point.confirm();
            pointRepository.save(point);
            return point.getId();
        }).orElseThrow(() -> new RuntimeException("Point not found"));
        return null;
    }

    @Override
    public Long handle(RejectPointCommand command) {
        pointRepository.findById(command.pointId()).map(point -> {
            point.reject();
            pointRepository.save(point);
            return point.getId();
        }).orElseThrow(() -> new RuntimeException("Point not found"));
        return null;
    }

    @Override
    public Long handle(CancelPointCommand command) {
        pointRepository.findById(command.pointId()).map(point -> {
            point.confirm();
            pointRepository.save(point);
            return point.getId();
        }).orElseThrow(() -> new RuntimeException("Point not found"));
        return null;
    }


}
