package com.acme.loyalsips.platform.loyalsips.domain.services;

import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CreateBarCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.DeleteBarCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.UpdateBarCommand;

import java.util.Optional;

/**
 * Bar Command Service
 */
public interface BarCommandService {
    /**
     * Handle the command
     * @param command
     * @return
     */

    long handle(CreateBarCommand command);

    /**
     * Handle the command
     * @param command
     * @return
     */
    Optional<Bar> handle(UpdateBarCommand command) ;

    /**
     * Handle the command
     * @param command
     *
     */
    void handle(DeleteBarCommand command);
}
