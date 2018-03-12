package milunas.twitt.controller;

import milunas.twitt.command.TwittCreate;
import milunas.twitt.model.Account;
import milunas.twitt.model.Twitt;
import milunas.twitt.service.TwittService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TwittController {

    @Autowired
    private TwittService service;

    @GetMapping("/user")
    public ResponseEntity<List<Twitt>> getAllTwitts(){
        return service.getAllTwitts();
    }

    @GetMapping("/user/{username}/twitt")
    public ResponseEntity<List<Twitt>> getAllUserTwitts(@PathVariable String username){
        return service.getAllUserTwitts(username);
    }

    @PostMapping("/user/{username}/twitt")
    public ResponseEntity<Void> createNewTwitt(@RequestBody TwittCreate twitt){
        return service.createNewTwitt(twitt);
    }

}
