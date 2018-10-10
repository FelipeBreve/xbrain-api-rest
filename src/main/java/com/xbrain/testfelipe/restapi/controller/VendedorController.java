package com.xbrain.testfelipe.restapi.controller;

import com.xbrain.testfelipe.restapi.models.Vendedor;
import com.xbrain.testfelipe.restapi.repository.RepositoryVendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/vendedor")
@RestController
public class VendedorController {

    @Autowired
    private RepositoryVendedor repository;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Vendedor> getAll() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Vendedor payload) {
        repository.save(payload);
    }
}
