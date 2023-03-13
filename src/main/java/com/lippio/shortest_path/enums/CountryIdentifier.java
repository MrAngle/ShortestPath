package com.lippio.shortest_path.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.lippio.shortest_path.dijkstra.CountryNode;

import java.util.function.Predicate;

public enum CountryIdentifier {
    ISO_CODE("ISO_CODE"), CCA_3("CCA_3"), NAME("NAME");

    private final String value;

    CountryIdentifier(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


    public Predicate<CountryNode> getCountryFilterPredicate(String identifier) {
        return switch (this) {
            case ISO_CODE -> c -> c.getCountry().getIsoCode().equalsIgnoreCase(identifier);
            case CCA_3 -> c -> c.getCountry().getCountryCode().equalsIgnoreCase(identifier);
            case NAME -> c -> c.getCountry().getName().equalsIgnoreCase(identifier);
        };
    }
}
