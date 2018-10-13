package com.xbrain.testfelipe.restapi.controller;

import com.xbrain.testfelipe.restapi.models.Ranking;
import com.xbrain.testfelipe.restapi.models.Venda;
import com.xbrain.testfelipe.restapi.projection.RakingProject;
import com.xbrain.testfelipe.restapi.repository.RepositoryVenda;
import com.xbrain.testfelipe.restapi.repository.RepositoryVendedor;
import com.xbrain.testfelipe.restapi.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/api/v1/venda")
@RestController
public class VendaController {

    @Autowired
    private RepositoryVenda repositoryVenda;
    @Autowired
    private RepositoryVendedor repositoryVendedor;


    @GetMapping
    public @ResponseBody Iterable<Venda> getAll() {
        return repositoryVenda.findAll();
    }

    @PostMapping(value = "/{vendedor_id}")
    public void save(@PathVariable("vendedor_id") Integer vendedorId, @RequestBody Venda payload) {
        repositoryVendedor.findById(vendedorId).map(vendedor -> {
           payload.setVendedor(vendedor);
           return repositoryVenda.save(payload);
        });
    }

    @PostMapping("/ranking-with-param")
    public @ResponseBody List<Ranking> getRankingParam(@RequestBody Map<String, Object> payload) {
        List<Ranking> listRaking = new ArrayList<>();
        List<RakingProject> rakingProjects = repositoryVenda.findByRankingTop10((String) payload.get("initialDate"), (String) payload.get("finalDate"));
        rakingProjects.forEach(obj -> VendaService.createListCustomRanking(obj, listRaking));
        return listRaking;
    }

    @GetMapping("/ranking")
    public @ResponseBody List<Ranking> getRanking() {
        List<Ranking> listRaking = new ArrayList<>();
        List<RakingProject> rakingProjects = repositoryVenda.findByRankingTop10(VendaService.createDateInitialAndFinal(false), VendaService.createDateInitialAndFinal(true));
        rakingProjects.forEach(obj -> VendaService.createListCustomRanking(obj, listRaking));
        return listRaking;
    }
}
