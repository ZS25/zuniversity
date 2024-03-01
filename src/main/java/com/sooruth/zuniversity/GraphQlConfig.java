package com.sooruth.zuniversity;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                //.scalar(ExtendedScalars.DateTime) //does not work for localDateTime.
                .scalar(ExtendedScalars.GraphQLBigDecimal); //to support bigDecimal in Java (currently not needed)

    }
}