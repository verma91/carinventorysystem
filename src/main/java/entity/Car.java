package entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {
    private String make;

    private String model;

    private int year;

    private float salesPrice;

    private int id;
}
