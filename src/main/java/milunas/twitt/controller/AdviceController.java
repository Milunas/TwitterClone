package milunas.twitt.controller;

import milunas.twitt.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

public class AdviceController {

    @GetMapping("/advice/{userId}")
    List<User> recommendedFriends(@PathVariable String userId){
        return Collections.emptyList();
    }

}
