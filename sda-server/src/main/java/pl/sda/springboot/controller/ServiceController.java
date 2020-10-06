package pl.sda.springboot.controller;

import pl.sda.springboot.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping("/service")
    public User serv() {
        return new User("firstname", "lastname");
    }

}
