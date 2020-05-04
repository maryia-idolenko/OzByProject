package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Birthday {
    String name;
    String telephone;
    String day;
    String month;
    String year;
    String gender;
    String comment;
}