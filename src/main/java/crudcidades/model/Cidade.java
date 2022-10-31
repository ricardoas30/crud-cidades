package crudcidades.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Cidade {
    @NotBlank(message = "{app.cidade.blank}")
    @Size(min = 5, max = 60, message = "{app.cidade.size}")
    private final String nome;

    @NotBlank(message = "{app.estado.blank}")
    @Size(min = 2, max = 2, message = "{app.estado.size}")
    private final String estado;

    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    /**
     * Semana 05 - Integração Banco de Dados
     * @return cidadeEntidade
     */
    public CidadeEntidade clonar() {
        var cidadeEntidade = new CidadeEntidade();

        cidadeEntidade.setNome(this.getNome());
        cidadeEntidade.setEstado(this.getEstado());

        return cidadeEntidade;
    }

    /**
     * Semana 05 - Integração Banco de Dados
     * @param cidade
     * @return
     */
    public Cidade clonar(CidadeEntidade cidade) {

        return new Cidade(cidade.getNome(), cidade.getEstado());
    }

    public String getNome() {
        return nome;
    }

    public String getEstado() {
        return estado;
    }
}
