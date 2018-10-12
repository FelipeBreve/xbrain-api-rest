package com.xbrain.testfelipe.restapi.repository;

import com.xbrain.testfelipe.restapi.models.Venda;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.Map;

/***
 * @description = Este método é auto-implementado pelo Spring, com as operações CRUD.
 */
public interface RepositoryVenda extends CrudRepository<Venda, Integer> {

    /***
     * nativeQuery: Para consultas nativas do SQL você deve deixar como 'true' para funcionar.
     *
     * @return
     */
    @Query(nativeQuery = true, value = "SELECT vdd.*, (SELECT COUNT(*) FROM venda as vd WHERE vendedor_id = vdd.id AND vd.data_venda BETWEEN :initialDate AND :finalDate) as ranking FROM vendedor vdd ORDER BY ranking DESC LIMIT 10")
    Iterable<Object> findByRankingTop10(@Param("initialDate") String initialDate, @Param("finalDate") String finalDate);
}
