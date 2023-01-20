package ru.netology.dao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.dao.repository.MyRepository;

import java.util.List;

@RestController
public class Controller {
    private final MyRepository repository;


    public Controller(MyRepository repository){
        this.repository = repository;
    }

    @GetMapping("/products/fetch-product")
    public List<String> getListProductName(String name) {
        return repository.getProductName(name);
    }
}