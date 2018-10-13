package com.xbrain.testfelipe.restapi.repository;

import com.xbrain.testfelipe.restapi.models.Vendedor;
import com.xbrain.testfelipe.restapi.repository.RepositoryVendedor;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VendedorRepositoryTest {

    @Autowired
    private RepositoryVendedor repositoryVendedor;

    @Test
    public void createVendedor() {
        Vendedor vendedor = new Vendedor();
        vendedor.setNomeVendedor("Usuario de teste");
        this.repositoryVendedor.save(vendedor);
        Assertions.assertThat(vendedor.getId()).isNotNull();
        Assertions.assertThat(vendedor.getNomeVendedor()).isEqualTo("Usuario de teste");
    }
}
