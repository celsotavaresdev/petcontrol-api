package com.ongpatinhasquebrilham.petcontrol.api.assembler;

import com.ongpatinhasquebrilham.petcontrol.api.model.PetResponse;
import com.ongpatinhasquebrilham.petcontrol.domain.model.Pet;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PetResponseAssembler {

    private final ModelMapper modelMapper;

    public PetResponse toModel(Pet pet) {
        return modelMapper.map(pet, PetResponse.class);
    }

    public List<PetResponse> toCollectionModel(List<Pet> pets) {
        return pets.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}