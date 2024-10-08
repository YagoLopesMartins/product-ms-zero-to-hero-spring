package com.example.productmsprojectspringzerotohero.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.example.productmsprojectspringzerotohero.dto.ProductDTO;
import com.example.productmsprojectspringzerotohero.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ProductRepository repository;


    @BeforeAll
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("com.example.productmsprojectspringzerotohero.fixture");
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        ProductDTO request = Fixture.from(ProductDTO.class).gimme("valid");
        String content = mapper.writeValueAsString(request);

        mockMvc.perform(post("/products")
                        .header(AUTHORIZATION, "Bearer foo")
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldNotCreateProduct() throws Exception {
        ProductDTO request = new ProductDTO();
        String content = mapper.writeValueAsString(request);

        mockMvc.perform(post("/products")
                        .header(AUTHORIZATION, "Bearer foo")
                        .contentType(APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldGetAllProducts() throws Exception {
        mockMvc.perform(get("/products")
                        .header(AUTHORIZATION, "Bearer foo"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetById() throws Exception {

        Long id = repository.findAll().get(0).getId();

        mockMvc.perform(get("/products/{id}", id)
                        .header(AUTHORIZATION, "Bearer foo"))
                .andExpect(status().isOk());
    }
}
