package com.ongpatinhasquebrilham.petcontrol.core;

import com.ongpatinhasquebrilham.petcontrol.api.model.PetRequest;
import com.ongpatinhasquebrilham.petcontrol.api.model.PetResponse;
import com.ongpatinhasquebrilham.petcontrol.domain.model.Pet;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        Converter<Integer, LocalDate> toEstimatedBirthdate =
                ctx -> LocalDate.now().minusMonths(ctx.getSource());

        Converter<LocalDate, Integer> toAgeInMonths =
                ctx -> Long.valueOf(
                        ChronoUnit.MONTHS.between(
                                ctx.getSource(),
                                LocalDate.now()))
                        .intValue();

        modelMapper.createTypeMap(PetRequest.class, Pet.class)
                .addMappings(mapper -> mapper.using(toEstimatedBirthdate)
                        .map(PetRequest::getAgeInMonths, Pet::setBirthdate));

        modelMapper.createTypeMap(Pet.class, PetResponse.class)
                .addMappings(mapper -> mapper.using(toAgeInMonths)
                        .map(Pet::getBirthdate, PetResponse::setAgeInMonths));

        return modelMapper;
    }
    
}