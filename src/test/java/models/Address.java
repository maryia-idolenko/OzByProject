package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Address {
    String postCode;
    String street;
    String houseNumber;
    String apartmentNumber;
    String entrance;
    String floor;
}