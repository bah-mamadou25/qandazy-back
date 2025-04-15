package com.gn.cky.qandazyback.poc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PocController {
     @GetMapping("/poc")
     public String pocEndpoint() {
         return "Hello, this is a proof of concept!";
     }
}
