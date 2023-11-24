package com.acme.loyalsips.platform.iam.infrastructure.hashing.bcrypt;

import com.acme.loyalsips.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Hashing service implementation.
 * Implementation of {@link HashingService} using BCrypt algorithm.
 * Implementation of {@link PasswordEncoder} using BCrypt algorithm.
 */

//INJECTION
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
