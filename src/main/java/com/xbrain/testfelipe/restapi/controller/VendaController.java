package com.xbrain.testfelipe.restapi.controller;

import com.xbrain.testfelipe.restapi.models.Ranking;
import com.xbrain.testfelipe.restapi.models.Venda;
import com.xbrain.testfelipe.restapi.projection.RakingProject;
import com.xbrain.testfelipe.restapi.repository.RepositoryVenda;
import com.xbrain.testfelipe.restapi.repository.RepositoryVendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
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
        rakingProjects.forEach(obj -> createListCustomRanking(obj, listRaking));
        return listRaking;
    }

    @GetMapping("/ranking")
    public @ResponseBody List<Ranking> getRanking() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        List<Ranking> listRaking = new ArrayList<>();
        /** Adicionando dia **/
        Date dateFinal = calcDaysOfWeek(calendar, 7 - calendar.get(Calendar.DAY_OF_WEEK));
        /** Removendo dia **/
        Date dateInitial = calcDaysOfWeek(calendar, -7);
        List<RakingProject> rakingProjects = repositoryVenda.findByRankingTop10(sdf.format(dateInitial), sdf.format(dateFinal));
        rakingProjects.forEach(obj -> createListCustomRanking(obj, listRaking));
        return listRaking;
    }

    /***
     *
     * @return Date
     */
    private Date calcDaysOfWeek(Calendar calendar, Integer days) {
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    private void createListCustomRanking(RakingProject rakingProject, List<Ranking> listRaking) {
        Ranking ranking = new Ranking();
        ranking.setId(rakingProject.getId());
        ranking.setNomeVendedor(rakingProject.getNome_Vendedor());
        ranking.setRanking(rakingProject.getRanking());
        ranking.setMedia(Double.toString(Double.parseDouble(rakingProject.getRanking()) / 7));
        listRaking.add(ranking);
    }
}
