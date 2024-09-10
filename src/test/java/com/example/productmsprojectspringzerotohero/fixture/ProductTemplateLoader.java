package com.example.productmsprojectspringzerotohero.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.productmsprojectspringzerotohero.dto.ProductDTO;
import com.example.productmsprojectspringzerotohero.model.Product;

public class ProductTemplateLoader implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(ProductDTO.class).addTemplate("valid", new Rule() {{
            add("name", random("Iphone 14 Pro Max", "Iphone 13 Pro Max", "Iphone 12 Pro Max"));
            add("description", "Smarthphone asdasdasdasdasd asdasd asda sd asda sdasdasdasdas asdasdasda");
            add("price", random(Double.class, range(0.01, 7999.48)));
        }});
    }
}
