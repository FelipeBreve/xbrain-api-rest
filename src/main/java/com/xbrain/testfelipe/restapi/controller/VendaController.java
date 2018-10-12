package com.xbrain.testfelipe.restapi.controller;

import com.xbrain.testfelipe.restapi.models.Venda;
import com.xbrain.testfelipe.restapi.repository.RepositoryVenda;
import com.xbrain.testfelipe.restapi.repository.RepositoryVendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

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
    public @ResponseBody Iterable<Object> getRankingParam(@RequestBody Map<String, Object> payload) {
        return repositoryVenda.findByRankingTop10((String) payload.get("initialDate"), (String) payload.get("finalDate"));
    }

    @PostMapping("/ranking")
    public @ResponseBody Iterable<Object> getRanking(@RequestBody Map<String, Object> payload) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        /** Adicionando dia **/
        Date dateFinal = calcDaysOfWeek(calendar, 7 - calendar.get(Calendar.DAY_OF_WEEK));
        /** Removendo dia **/
        Date dateInitial = calcDaysOfWeek(calendar, -7);
        return repositoryVenda.findByRankingTop10(sdf.format(dateInitial), sdf.format(dateFinal));
    }

    /***
     *
     * @return Date
     */
    private Date calcDaysOfWeek(Calendar calendar, Integer days) {
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
