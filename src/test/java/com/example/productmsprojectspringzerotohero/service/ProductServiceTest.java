package com.example.productmsprojectspringzerotohero.service;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.productmsprojectspringzerotohero.dto.ProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService service;

        //    private ProductDTO request;
        //
        //    @BeforeEach
        //    public void setUp(){
        //        request = new ProductDTO();
        //        request.setName("Iphone prox max 2");
        //        request.setDescription("Smarthphone asdasdasdasdasd asdasd asda sd asda sdasdasdasdas asdasdasda");
        //        request.setPrice(5000.00);
        //    }
        //

    @BeforeAll
    public static void setUp(){
      FixtureFactoryLoader.loadTemplates("br.com.youtube.productms.fixture");
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
}
