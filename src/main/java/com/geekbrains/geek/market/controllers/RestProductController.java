package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping // /api/v1/products
    public Page<Product> getProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                     @RequestParam(defaultValue = "5", name = "s") Integer size,
                                     @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        return productService.findAll(productFilter.getSpec(), page - 1, size);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
