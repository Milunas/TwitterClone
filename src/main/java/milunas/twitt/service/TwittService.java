package milunas.twitt.service;

import milunas.twitt.command.TwittCreate;
import milunas.twitt.model.Twitt;
import milunas.twitt.repository.TwittRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TwittService {

    @Autowired
    private TwittRepository repository;

    public ResponseEntity<List<Twitt>> getAllTwitts(){
       List<Twitt> allTwitts = repository.findAll();
        return new ResponseEntity<List<Twitt>>(allTwitts, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Twitt>> getAllUserTwitts(String author){
        List<Twitt> userTwitts = repository.findByAuthor(author);
        return new ResponseEntity<List<Twitt>>(userTwitts, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Void> createNewTwitt(TwittCreate twittCreate){
        Twitt twitt = new Twitt(twittCreate.getAuthor(), new Date(), twittCreate.getMessage());
        repository.save(twitt);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Location", twitt.getId()+"");
        return new ResponseEntity<Void>(responseHeaders, HttpStatus.CREATED);
    }

}
