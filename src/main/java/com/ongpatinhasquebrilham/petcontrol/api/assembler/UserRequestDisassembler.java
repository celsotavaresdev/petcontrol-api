package com.ongpatinhasquebrilham.petcontrol.api.assembler;

import com.ongpatinhasquebrilham.petcontrol.api.model.UserRequest;
import com.ongpatinhasquebrilham.petcontrol.domain.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserRequestDisassembler {

    private final ModelMapper modelMapper;

    public User toDomainObject(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    public void copyToDomainObject(UserRequest userRequest, User user) {
        modelMapper.map(userRequest, user);
    }

}