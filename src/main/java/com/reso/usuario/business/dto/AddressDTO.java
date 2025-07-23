package com.reso.usuario.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private String street;
    private int zipCode;
    private String city;
    private String state;
    private int number;
    private String complement;


}
