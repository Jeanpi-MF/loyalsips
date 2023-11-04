package com.acme.loyalsips.platform.loyalsips.domain.model.commands;

public record CreateCustomerCommand(String firstName, String lastName, String email, String street, String number, String city, String country) {
}
