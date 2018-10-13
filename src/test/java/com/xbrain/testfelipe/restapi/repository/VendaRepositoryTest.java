package com.xbrain.testfelipe.restapi.repository;

import com.xbrain.testfelipe.restapi.models.Venda;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VendaRepositoryTest {

    @Autowired
    private RepositoryVendedor repositoryVendedor;

    @Autowired
    private RepositoryVenda repositoryVenda;

    @Test
    public void createVenda() throws ParseException {
        Venda venda = new Venda();
        venda.setDataVenda(new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-13"));
        venda.setValor(120.00);
        repositoryVendedor.findById(1).map(vendedor -> {
            venda.setVendedor(vendedor);
            return repositoryVenda.save(venda);
        });
        Assertions.assertThat(venda.getVendedor()).isNotNull();

    }
}
