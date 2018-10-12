package com.xbrain.testfelipe.restapi.services;

import com.xbrain.testfelipe.restapi.models.Venda;
import com.xbrain.testfelipe.restapi.repository.RepositoryVenda;

public class VendaService {

    private RepositoryVenda repositoryVenda;

    public Iterable<Venda> getAll() {
        return repositoryVenda.findAll();
    }
}
