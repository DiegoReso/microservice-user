package com.reso.usuario.business;

import com.reso.usuario.business.converter.UserConverter;
import com.reso.usuario.business.dto.UserDTO;
import com.reso.usuario.infrascture.entity.User;
import com.reso.usuario.infrascture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO insertUser(UserDTO userDTO){
        User user = userConverter.userDTOToUser(userDTO);
        user = userRepository.save(user);
        return userConverter.userToUserDTO(user);
    }



}
