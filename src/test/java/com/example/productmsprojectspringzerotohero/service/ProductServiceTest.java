package com.example.productmsprojectspringzerotohero.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.productmsprojectspringzerotohero.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = {"classpath:clear-database.sql"})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @BeforeAll
    public static void setUp(){
      FixtureFactoryLoader.loadTemplates("com.example.productmsprojectspringzerotohero.fixture");
    }

    @Test
    public void shouldCreateProduct(){
        ProductDTO request =  Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = productService.create(request);
        Assertions.assertNotNull(response.get());
        Assertions.assertEquals(response.get().getName(), request.getName());
        Assertions.assertEquals(response.get().getDescription(), request.getDescription());
        Assertions.assertEquals(response.get().getPrice(), request.getPrice());
    }

    @Test
    public void shouldGetAllProducts(){
        ProductDTO request =  Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = productService.create(request);
        List<ProductDTO> responses = productService.getAll();

        Assertions.assertNotNull(responses);
        Assertions.assertEquals(responses.size(), 1);
        Assertions.assertEquals(responses.get(0).getName(),             response.get().getName());
        Assertions.assertEquals(responses.get(0).getDescription(),      response.get().getDescription());
        Assertions.assertEquals(responses.get(0).getPrice(),            response.get().getPrice());
    }

    @Test
    public void shouldGetProductById(){
        ProductDTO request =  Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = productService.create(request);

        Long id = response.get().getId();
        Optional<ProductDTO> responseById = productService.getById(id);

        Assertions.assertNotNull(responseById.get());
        Assertions.assertEquals(responseById.get().getName(),             request.getName());
        Assertions.assertEquals(responseById.get().getDescription(),     request.getDescription());
        Assertions.assertEquals(responseById.get().getPrice(),            request.getPrice());
        Assertions.assertTrue(responseById.get().getAvailable());
    }

    @Test
    public void shouldUpdateProduct(){
        ProductDTO request =  Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = productService.create(request);
        Long id = response.get().getId();

        String newDescription = "update update update update update update update update update update update";
        request.setDescription(newDescription);

        double newPrice = 789.58;
        request.setPrice(newPrice);

        Optional<ProductDTO> updatedProductDTO = productService.update(id, request);

        Assertions.assertNotNull(updatedProductDTO.get());
        Assertions.assertEquals(updatedProductDTO.get().getDescription(), newDescription);
        Assertions.assertEquals(updatedProductDTO.get().getPrice(), newPrice);
    }

    @Test
    public void shouldInactiveProduct(){
        ProductDTO request =  Fixture.from(ProductDTO.class).gimme("valid");
        Optional<ProductDTO> response = productService.create(request);
        Long id = response.get().getId();

        boolean inactive = productService.inactive(id);

        Assertions.assertTrue(inactive);
    }
}
