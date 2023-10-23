package com.ongpatinhasquebrilham.petcontrol.api.assembler;

import com.ongpatinhasquebrilham.petcontrol.api.model.UserResponse;
import com.ongpatinhasquebrilham.petcontrol.domain.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserResponseAssembler {

    private final ModelMapper modelMapper;

    public UserResponse toModel(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public List<UserResponse> toCollectionModel(List<User> users) {
        return users.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

}