package com.facturation.factureservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/eteindre")
public class EteindreController {

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> corsHeaders() {
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public void shutdown() {
        System.exit(0);
    }
}
