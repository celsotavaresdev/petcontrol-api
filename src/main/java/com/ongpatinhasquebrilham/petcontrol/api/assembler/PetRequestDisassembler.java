package com.ongpatinhasquebrilham.petcontrol.api.assembler;

import com.ongpatinhasquebrilham.petcontrol.api.model.PetRequest;
import com.ongpatinhasquebrilham.petcontrol.domain.model.Pet;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PetRequestDisassembler {

    private final ModelMapper modelMapper;

    public Pet toDomainObject(PetRequest petRequest) {
        return modelMapper.map(petRequest, Pet.class);
    }

    public void copyToDomainObject(PetRequest petRequest, Pet pet) {
        modelMapper.map(petRequest, pet);
    }

}