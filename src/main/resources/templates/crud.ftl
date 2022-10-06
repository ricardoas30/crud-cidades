<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>CRUD Cidades</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
    <body>
        <div class="container">
            <!-- Cabeçalho -->
            <div class="container-fluid">
                <div class="jumbotron mt-5">
                    <h1>Gerenciamneto de Cidades</h1>
                    <p>Um CRUD para criar, alterar, excluir e listar cidades</p>
                </div>
            </div>
            <!-- Fim Cabeçalho -->

            <!-- Formulário -->
            <#if cidadeAtual??>
            <form action="/alterar" method="POST" class="needs-validation" novalidate>
                <input type="hidden" name="nomeAtual" value="${(cidadeAtual.nome)!}"/>
                <input type="hidden" name="estadoAtual" value="${(cidadeAtual.estado)!}"/>
            <#else>
                <form action="/criar" method="POST" class="needs-validation" novalidate>
            </#if>

                    <div class="form-group">
                        <label for="nome">Cidade:</label>
                        <input required maxlength="20" value="${(cidadeAtual.nome)!}${nomeInformado!}" type="text"
                               class="form-control ${(nome??)?then('is-invalid','')}" placeholder="Informe o nome da cidade"
                               id="nome" name="nome">
                        <div class="invalid-feedback">
                            ${nome!}
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="estado">Estado:</label>
                        <input required maxlength="2" value="${(cidadeAtual.estado)!}${estadoInformado!}" type="text"
                               class="form-control ${(estado??)?then('is-invalid','')}"
                               placeholder="Informe o estado ao qual a cidade pertence" id="estado" name="estado">
                        <div class="invalid-feedback">
                            ${estado!}
                        </div>
                    </div>
                    <#if cidadeAtual??>
                        <button type="submit" class="btn btn-warning">Alterar</button>
                    <#else>
                        <button type="submit" class="btn btn-primary">Criar</button>
                    </#if>
                </form>
                <!-- Fim Formulário -->

                <!-- Tabela -->
                <table class="table table-striped table-hover mt-5">
                    <thead class="thead-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Estado</th>
                        <th>Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list listaCidades as cidade>
                        <tr>
                            <td>${cidade.nome}</td>
                            <td>${cidade.estado}</td>
                            <td>
                                <div class="d-flex d-justify-content-center">
                                    <a class="btn btn-warning mr-3"
                                       href="/prepararAlterar?nome=${cidade.nome}&estado=${cidade.estado}">Alterar</a>
                                    <a class="btn btn-danger" href="/excluir?nome=${cidade.nome}&estado=${cidade.estado}">Excluir</a>
                                </div>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
                <!-- Fim Tabela -->
        </div>
    </body>
</html>