package com.acme.loyalsips.platform.loyalsips.interfaces.rest.resources;

public record CreateCustomerResource(String firstName, String lastName, String email, String street, String number, String city, String country) {
}

