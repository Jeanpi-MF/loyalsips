package com.acme.loyalsips.platform.loyalsips.application.internal.commandservices;

import com.acme.loyalsips.platform.loyalsips.domain.exceptions.CourseNotFoundException;
import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Bar;
import com.acme.loyalsips.platform.loyalsips.domain.model.aggregates.Point;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.CancelPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.ConfirmPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.RejectPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.model.commands.RequestPointCommand;
import com.acme.loyalsips.platform.loyalsips.domain.services.PointCommandService;
import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.BarRepository;
import com.acme.loyalsips.platform.loyalsip.infrastructure.persistence.jpa.repositories.CustomerRepository;
import com.acme.loyalsips.platform.loyalsips.infrastructure.persistence.jpa.repositories.PointRepository;
import org.springframework.stereotype.Service;
 