package com.acme.loyalsips.platform.loyalsips.domain.services;

import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CancelPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.ConfirmPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.RejectPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.RequestPointCommand;

public interface PointCommandService {

    Long handle(RequestPointCommand command);

    Long handle(ConfirmPointCommand command);

    Long handle(RejectPointCommand command);

    Long handle(CancelPointCommand command);
}
