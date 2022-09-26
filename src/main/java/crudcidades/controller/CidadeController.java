package crudcidades.controller;

import crudcidades.model.Cidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class CidadeController {

    @GetMapping("/")
    public String listar(Model memoria) {

        var cidades = Set.of(
                new Cidade("Florianópolis", "SC"),
                new Cidade("Curitiba", "PR"),
                new Cidade("São Paulo", "SP")
        );

        memoria.addAttribute("listaCidades", cidades);

        return "/crud";
    }
}
