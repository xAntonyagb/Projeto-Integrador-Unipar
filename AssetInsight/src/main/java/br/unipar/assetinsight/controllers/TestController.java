package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.repositories.BlocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    private BlocoRepository repo;


    @GetMapping("/test")
    public String test() {
        return "Teste";
    }
}
