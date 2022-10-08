package com.lippio.shortest_path.pojo;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.StdConverter;

public class CountryDeserializerSanitizer implements Converter<Country, Country> {

    @Override
    public Country convert(Country country) {
        return null;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }

}





//public class CountryDeserializerSanitizer extends StdConverter<Country, Country> {
//
//    @Override
//    public Country convert(Country country) {
//        return null;
//    }
//
//}