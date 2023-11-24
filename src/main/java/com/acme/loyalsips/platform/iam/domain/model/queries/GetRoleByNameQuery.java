package com.acme.loyalsips.platform.iam.domain.model.queries;

import com.acme.loyalsips.platform.iam.domain.model.valueobjects.Roles;
//RECORD RECEIVE A PARAMETER ROLES
public record GetRoleByNameQuery(Roles name) {
}
