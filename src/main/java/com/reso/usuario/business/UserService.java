package com.reso.usuario.business;

import com.reso.usuario.business.converter.UserConverter;
import com.reso.usuario.business.dto.AddressDTO;
import com.reso.usuario.business.dto.PhoneDTO;
import com.reso.usuario.business.dto.UserDTO;
import com.reso.usuario.infrascture.entity.Address;
import com.reso.usuario.infrascture.entity.Phone;
import com.reso.usuario.infrascture.entity.User;
import com.reso.usuario.infrascture.exceptions.ConflictException;
import com.reso.usuario.infrascture.exceptions.ResourceNotFoundException;
import com.reso.usuario.infrascture.repository.AddressRepository;
import com.reso.usuario.infrascture.repository.PhoneRepository;
import com.reso.usuario.infrascture.repository.UserRepository;
import com.reso.usuario.infrascture.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;

    public UserDTO insertUser(UserDTO userDTO){
        emailExists(userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userConverter.userDTOToUser(userDTO);
        user = userRepository.save(user);
        return userConverter.userToUserDTO(user);
    }

    public void emailExists(String email){
        try{
            if(userRepository.existsByEmail(email)){
                throw new ConflictException("Email ja cadastrado!" + email);
            }
        }catch(Exception e){
            throw new ConflictException("Email ja cadastrado!" + e.getCause());
        }
    }

    public UserDTO findUserByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado " + email));
        return userConverter.userToUserDTO(user);
    }

    public void deleteUserByEmail(String email){
        userRepository.deleteUserByEmail(email);
    }

    public UserDTO updateDataUser(String token, UserDTO userDTO){
        String email = jwtUtil.extractUsername(token.substring(7));
        userDTO.setPassword(userDTO.getPassword() != null ? passwordEncoder.encode(userDTO.getPassword()) : null);
        User userEntity = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email nao localizado!"));
        User user = userConverter.updateUser(userDTO, userEntity);
        return userConverter.userToUserDTO(userRepository.save(user));
    }

    public AddressDTO updateAddress(Long idAddress, AddressDTO addressDTO){
        Address entity = addressRepository.findById(idAddress).orElseThrow(()-> new ResourceNotFoundException("Id do Endereco nao encontrado " + idAddress));
        Address address = userConverter.updateAddress(addressDTO, entity);
        return userConverter.toAddressDTO(addressRepository.save(address));
    }

    public PhoneDTO updatePhone(Long idPhone, PhoneDTO phoneDTO){
        Phone entity = phoneRepository.findById(idPhone).orElseThrow(()-> new ResourceNotFoundException("Id do Telefone nao encontrado " + idPhone));
        Phone phone = userConverter.updatePhone(phoneDTO, entity);
        return userConverter.toPhonesDTO(phoneRepository.save(phone));
    }

    public UserDTO insertAddress(String token,AddressDTO addressDTO){
        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado! " + email));
        Address entity = userConverter.toAddressWithUserId(addressDTO,user.getId());
        entity = addressRepository.save(entity);
        return userConverter.userToUserDTO(user);
    }

    public UserDTO insertPhone(String token, PhoneDTO phoneDTO){
        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado! " + email));
        Phone entity = userConverter.toPhoneWithUSerId(phoneDTO,user.getId());
        entity = phoneRepository.save(entity);
        return userConverter.userToUserDTO(user);
    }
}
