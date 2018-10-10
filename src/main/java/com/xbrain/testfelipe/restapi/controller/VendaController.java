package com.xbrain.testfelipe.restapi.controller;

import com.xbrain.testfelipe.restapi.models.Venda;
import com.xbrain.testfelipe.restapi.repository.RepositoryVenda;
import com.xbrain.testfelipe.restapi.repository.RepositoryVendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/venda")
@RestController
public class VendaController {

    @Autowired
    private RepositoryVenda repositoryVenda;
    @Autowired
    private RepositoryVendedor repositoryVendedor;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Iterable<Venda> getAll() {
        return repositoryVenda.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Venda payload) {
        System.out.print(payload);
        repositoryVendedor.findById(1).map(vendedor -> {
           payload.setVendedor(vendedor);
           return repositoryVenda.save(payload);
        });
    }
}
