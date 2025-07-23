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
                .number(phone.getNumber())
                .build();
    }
}
