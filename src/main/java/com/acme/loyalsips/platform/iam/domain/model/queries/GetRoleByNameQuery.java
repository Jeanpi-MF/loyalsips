package com.acme.loyalsips.platform.iam.domain.model.queries;

import com.acme.loyalsips.platform.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
