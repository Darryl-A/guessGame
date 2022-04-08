package com.sg.mastermind.controller;

import com.sg.mastermind.entity.Game;
import com.sg.mastermind.entity.Round;
import com.sg.mastermind.service.mastermindServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author darrylanthony
 */

@RestController
@RequestMapping("/api")
public class mastermindController {
    @Autowired
    mastermindServiceLayer service;
    
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame() {
        return service.createNewGame();
    }
    
}
