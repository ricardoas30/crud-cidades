package crudcidades.controller;

import crudcidades.model.Cidade;
import crudcidades.model.CidadeEntidade;
import crudcidades.model.CidadeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class CidadeController {

    private final CidadeRepository repository;

    public CidadeController(CidadeRepository repository) {
        this.repository = repository;
//        this.cidades = new HashSet<>();
    }

    @GetMapping("/")
    public String listar(Model memoria) {

        memoria.addAttribute("listaCidades", repository
                        .findAll()
                        .stream()
                        .map(cidade -> new Cidade(cidade.getNome(),
                                                  cidade.getEstado()))
                        .collect(Collectors.toList()));

        return "/crud";
    }

    @PostMapping("/criar")
    public String criar(@Valid Cidade cidade, BindingResult validacao, Model memoria) {

        if(validacao.hasErrors()) {
            validacao
                .getFieldErrors()
                .forEach(error ->
                            memoria.addAttribute(
                                error.getField(),
                                error.getDefaultMessage())
                );

            memoria.addAttribute("nomeInformado", cidade.getNome());
            memoria.addAttribute("estadoInformado", cidade.getEstado());
            return ("/crud");

        } else {
////            cidades.add(cidade);
//            var novaCidade = new CidadeEntidade();
//            novaCidade.setNome(cidade.getNome());
//            novaCidade.setEstado(cidade.getEstado());
//
//            repository.save(novaCidade);
            /**
             * Semana 05 - Integração Banco de Dados
             */
            repository.save(cidade.clonar());
        }

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String excluir(@RequestParam String nome,
                          @RequestParam String estado) {

        var cidadeEstadoEncontrada = repository.findByNomeAndEstado(nome, estado);

        cidadeEstadoEncontrada.ifPresent(repository::delete);

        return "redirect:/";

    }
    @GetMapping("/prepararAlterar")
    public String prepararAlterar(@RequestParam String nome,
                                  @RequestParam String estado,
                                  Model memoria) {

        var cidadeAtual = repository.findByNomeAndEstado(nome,estado);

        cidadeAtual.ifPresent(cidadeEncontrada -> {
            memoria.addAttribute("cidadeAtual", cidadeEncontrada);
            memoria.addAttribute("listaCidades", repository.findAll());
        });

        return "/crud";
    }

    @PostMapping("/alterar")
    public String alterar(@RequestParam String nomeAtual,
                          @RequestParam String estadoAtual,
                          Cidade cidade) {

        var cidadeAtual = repository.findByNomeAndEstado(nomeAtual, estadoAtual);

        if(cidadeAtual.isPresent()) {

            var cidadeEncontrada = cidadeAtual.get();
            cidadeEncontrada.setNome(cidade.getNome());
            cidadeEncontrada.setEstado(cidade.getEstado());

            repository.saveAndFlush(cidadeEncontrada);
        }


        return "redirect:/";

    }
}
