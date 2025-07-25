package com.reso.usuario.infrascture.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "zip_code", length = 5)
    private int zipCode;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "state", length = 2)
    private String state;
    @Column(name = "number", length = 10)
    private int number;
    @Column(name = "complement", length = 100)
    private String complement;
    @Column(name = "users_id")
    private Long user_id;
}
