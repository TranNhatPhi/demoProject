package com.r2sCompany.exampleProject.dto;

import lombok.Getter;

@Getter
public class AddressDTO {
    private String apartmentNumber;
    private String floor;
    private String building;
    private String streetNumber;
    private String street;
    private String city;
    private String country;
    private Integer addressType;
}