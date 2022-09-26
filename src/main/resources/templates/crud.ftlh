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
        <form>
            <div class="form-group">
                <label for="nome">Cidade:</label>
                <input type="text" class="form-control" placeholder="Informe o nome da cidade" id="nome">
            </div>
            <div class="form-group">
                <label for="estado">Estado:</label>
                <input type="text" class="form-control" placeholder="Informe o estado ao qual a cidade pertence" id="estado">
            </div>
            <button type="submit" class="btn btn-primary">Criar</button>
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
                            <a class="btn btn-warning mr-3" href="">Alterar</a>
                            <a class="btn btn-danger" href="">Excluir</a>
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