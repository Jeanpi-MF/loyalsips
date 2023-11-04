package com.acme.loyalsips.platform.loyalsips.application.internal.commandservices;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CreateBarCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.DeleteBarCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.UpdateBarCommand;
import com.acme.loyalsips.platform.loyalsips.domain.services.BarCommandService;
import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.BarRepository;
import org.springframework.stereotype.Service;  

import java.util.Optional;

/**
 * Implementation of {@link BarCommandService}.
 this is important*/

@Service
public class BarCommandServiceImpl implements BarCommandService {
    private final BarRepository barRepository;

    public BarCommandServiceImpl(BarRepository barRepository) {
        this.barRepository = barRepository;
    }

    /**
     * *(@inheritDoc)
     */

    @Override
    public long handle(CreateBarCommand command) {
        if (barRepository.existsByName(command.name()))
            throw new IllegalArgumentException("Bar with name ");
        var bar = new Bar(command.name(), command.description());
        barRepository.save(bar);
        return bar.getId();
    }

    /**
     * *(@inheritDoc)
     */

    @Override
    public Optional<Bar> handle(UpdateBarCommand command) {
        if (!barRepository.existsById(command.id())) throw new IllegalArgumentException("Bar with id does not exist");
        var barToUpdate = barRepository.findById(command.id()).get();
        if (barRepository.existsAllByNameAndIdIsNot(command.name(), command.id()))
            throw new IllegalArgumentException("Bar with name already exists");
        var updatedBar = barRepository.save(barToUpdate.updateInformation(command.name(), command.description()));
           return Optional.of(updatedBar);
    }

    @Override
    public void handle(DeleteBarCommand command) {
        if (!barRepository.existsById(command.barId())) throw new IllegalArgumentException("Bar with id does not exist");
        barRepository.deleteById(command.barId());

    }
}
