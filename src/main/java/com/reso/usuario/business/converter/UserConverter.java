package com.reso.usuario.business.converter;

import com.reso.usuario.business.dto.AddressDTO;
import com.reso.usuario.business.dto.PhoneDTO;
import com.reso.usuario.business.dto.UserDTO;
import com.reso.usuario.infrascture.entity.Address;
import com.reso.usuario.infrascture.entity.Phone;
import com.reso.usuario.infrascture.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

    public User userDTOToUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .addresses(addressDTOToAddress(userDTO.getAddresses()))
                .phones(phoneDTOToPhones(userDTO.getPhones()))
                .build();
    }

    public UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addresses(addressToAddressDTO(user.getAddresses()))
                .phones(phonesToPhonesDTO(user.getPhones()))
                .build();
    }

    public List<Address> addressDTOToAddress(List<AddressDTO> addressDTO) {
         return addressDTO.stream().map(this::toAddress).toList();
    }

    public List<AddressDTO> addressToAddressDTO(List<Address> address) {
        return address.stream().map(this::toAddressDTO).toList();
    }

    public List<Phone> phoneDTOToPhones(List<PhoneDTO> phonesDTO) {
        return phonesDTO.stream().map(this::toPhone).toList();
    }

    public List<PhoneDTO> phonesToPhonesDTO(List<Phone> phones) {
        return phones.stream().map(this::toPhonesDTO).toList();
    }

    public Address toAddress(AddressDTO addressDTO) {
        return Address.builder()
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .zipCode(addressDTO.getZipCode())
                .state(addressDTO.getState())
                .number(addressDTO.getNumber())
                .complement(addressDTO.getComplement())
                .build();
    }

    public AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .zipCode(address.getZipCode())
                .state(address.getState())
                .number(address.getNumber())
                .complement(address.getComplement())
                .build();
    }

    public Phone toPhone(PhoneDTO phonesDTO) {
        return Phone.builder()
                .number(phonesDTO.getNumber())
                .build();
    }

    public PhoneDTO toPhonesDTO(Phone phone) {
        return PhoneDTO.builder()
                .id(phone.getId())
                .number(phone.getNumber())
                .build();
    }

    public User updateUser(UserDTO userDTO, User entity){
        return User.builder()
                .id(entity.getId())
                .name(userDTO.getName() != null ?  userDTO.getName() : entity.getName())
                .email(userDTO.getEmail() != null ?  userDTO.getEmail() : entity.getEmail())
                .password(userDTO.getPassword() != null ?  userDTO.getPassword() : entity.getPassword())
                .addresses(entity.getAddresses())
                .phones(entity.getPhones())
                .build();
    }

    public Address updateAddress(AddressDTO addressDTO, Address entity){
        return Address.builder()
                .id(entity.getId())
                .street(addressDTO.getStreet() != null ?   addressDTO.getStreet() : entity.getStreet())
                .city(addressDTO.getCity() != null ?  addressDTO.getCity() : entity.getCity())
                .zipCode(addressDTO.getZipCode() != 0 ? addressDTO.getZipCode() : entity.getZipCode())
                .state(addressDTO.getState() != null ? addressDTO.getState() : entity.getState())
                .number(addressDTO.getNumber() != 0 ? addressDTO.getNumber() : entity.getNumber())
                .complement(addressDTO.getComplement() != null ? addressDTO.getComplement() : entity.getComplement())
                .build();
    }

    public Phone updatePhone(PhoneDTO phoneDTO, Phone entity){
        return Phone.builder()
                .id(entity.getId())
                .number(phoneDTO.getNumber() != 0 ? phoneDTO.getNumber() : entity.getNumber())
                .build();
    }

    public Address toAddressWithUserId(AddressDTO addressDTO, Long idUser ){
        return Address.builder()
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .zipCode(addressDTO.getZipCode())
                .state(addressDTO.getState())
                .number(addressDTO.getNumber())
                .complement(addressDTO.getComplement())
                .user_id(idUser)
                .build();
    }

    public Phone toPhoneWithUSerId(PhoneDTO phoneDTO, Long idUser){
        return Phone.builder()
                .number(phoneDTO.getNumber())
                .user_id(idUser)
                .build();
    }
}
