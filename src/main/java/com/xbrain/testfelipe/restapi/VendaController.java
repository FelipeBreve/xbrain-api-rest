package com.xbrain.testfelipe.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendaController {

    @RequestMapping("/hello")
    public String hello() {
        return "Teste";
    }
}
