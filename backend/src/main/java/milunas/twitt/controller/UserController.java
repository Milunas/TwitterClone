package milunas.twitt.controller;

import milunas.twitt.command.TwittCreate;
import milunas.twitt.model.Twitt;
import milunas.twitt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<Twitt>> getAllTwitts(){
        return service.getAllTwitts();
    }

    @GetMapping("/{userId}/twitt")
    public ResponseEntity<List<Twitt>> getAllUserTwitts(@PathVariable String userId){
        return service.getAllUserTwitts(userId);
    }

    @PostMapping("/{userId}/twitt")
    public ResponseEntity<Void> createNewTwitt(@PathVariable String userId, @RequestBody TwittCreate twitt){
        return service.createNewTwitt(twitt);
    }

}
