package crudcidades.controller;

import crudcidades.model.Cidade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class CidadeController {

    private Set<Cidade> cidades;

    public CidadeController() {
        this.cidades = new HashSet<>();
    }

    @GetMapping("/")
    public String listar(Model memoria) {

        memoria.addAttribute("listaCidades", cidades);

        return "/crud";
    }

    @PostMapping("/criar")
    public String criar(Cidade cidade) {

        cidades.add(cidade);

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(@RequestParam String nome,
                          @RequestParam String estado) {

        cidades.removeIf(cidadeAtual ->
                    cidadeAtual.getNome().equals(nome) &&
                    cidadeAtual.getEstado().equals(estado));

        return "redirect:/";

    }
    @GetMapping("/prepararAlterar")
    public String prepararAlterar(@RequestParam String nome,
                                  @RequestParam String estado,
                                  Model memoria) {

        var cidadeAtual = cidades
                                            .stream()
                                            .filter(cidade -> cidade.getNome().equals(nome) &&
                                                    cidade.getEstado().equals(estado))
                                            .findAny();

        if(cidadeAtual.isPresent()) {
            memoria.addAttribute("cidadeAtual", cidadeAtual.get());
            memoria.addAttribute("listaCidades", cidades);
        }
        return "/crud";
    }

    @PostMapping("/alterar")
    public String alterar(@RequestParam String nomeAtual,
                          @RequestParam String estadoAtual,
                          Cidade cidade) {

        cidades.removeIf(cidadeAtual ->
                cidadeAtual.getNome().equals(nomeAtual) &&
                cidadeAtual.getEstado().equals(estadoAtual));

        criar(cidade);

        return "redirect:/";

    }
}