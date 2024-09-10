package com.example.productmsprojectspringzerotohero.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.productmsprojectspringzerotohero.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = {"classpath:clear-database.sql"})
public class ProductServiceTest {
    @Autowired
    private ProductService service;

    @BeforeAll
    public static void setUp(){
      FixtureFactoryLoader.loadTemplates("com.example.productmsprojectspringzerotohero.fixture");
    }

    @Test
    public void shouldCreateProduct(){
        ProductDTO request =  Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = service.create(request);
        Assertions.assertNotNull(response.get());
        Assertions.assertEquals(response.get().getName(), request.getName());
        Assertions.assertEquals(response.get().getDescription(), request.getDescription());
        Assertions.assertEquals(response.get().getPrice(), request.getPrice());
    }

    @Test
    public void shouldGetAllProducts(){
         List<ProductDTO> response = service.getAll();
        System.out.println(response.size());
    }
}
