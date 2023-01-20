package pl.sda.springboot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final String firstname;
    private final String lastname;
}
