package DingoBetBackend.controllers;

import DingoBetBackend.models.match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import DingoBetBackend.services.matchService;
@Controller
@RequestMapping("/match")
public class matchController {

    @Autowired
    private matchService matchService;

    @PostMapping("/create")
    public ResponseEntity<String> createMatch(@RequestBody match match) {

        match savedmatch = matchService.saveMatch(match);

        return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(savedmatch));
    }
}
