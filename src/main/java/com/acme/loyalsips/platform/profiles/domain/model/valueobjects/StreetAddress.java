package com.acme.loyalsips.platform.profiles.domain.model.valueobjects;

public record StreetAddress(
        String street,
        String number,
        String city,
        String country
) {
    public StreetAddress() {
        this(null, null, null, null);
    }

    public StreetAddress {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street must not be null or blank");
        }

        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City must not be null or blank");
        }

        if (country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country must not be null or blank");
        }
    }

    public StreetAddress(String street, String city, String country) {
        this(street, null, city, country);
    }

    public String getStreetAddress() {
        return String.format("%s %s %s %s %s", street, number, city, country);
    }
}
