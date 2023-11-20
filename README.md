# Shop Application

Este é um projeto de aplicação de loja desenvolvido por Mateus Poletto.
O projeto é referente ao trabalho final da disciplina Fundamentos Desenvolvimento de Software
PUCRS - Profº Bernardo Copstein

## Como subir o container utilizando Docker:

1. Certifique-se de que o Docker esteja instalado e funcionando corretamente em sua máquina. Você pode verificar isso executando o comando `docker -v` no terminal. Se o Docker estiver instalado, você verá a versão do Docker impressa no terminal.

2. Navegue até o diretório raiz do projeto (o diretório que contém o arquivo Dockerfile e docker-compose.yml).

3. Execute o seguinte comando para construir e iniciar o container Docker:

    ```
    docker-compose up --build
    ```

    Este comando irá construir a imagem Docker a partir do Dockerfile e, em seguida, iniciar um container a partir dessa imagem. O flag `--build` instrui o Docker a construir a imagem antes de iniciar o container.

## Recomendações:

#### Subir primeiro apenas o Postgres e utilizar os scripts sql de criação de tabelas e inserção de dados antes de subir a aplicação por inteira.

## Solução de problemas:

### Se você encontrar um problema de permissão ao tentar executar o script `entrypoint.sh`, você pode resolver isso dando ao arquivo permissões de execução. Execute o seguinte comando para fazer isso:

    
    sudo chmod +x ./entrypoint.sh
    

Este comando dá ao arquivo entrypoint.sh permissões de execução, permitindo que ele seja executado como um script.


### Problemas de permissão com o diretório .pgdata

Se você encontrar problemas de permissão com o diretório .pgdata, você pode alterar o proprietário do diretório usando o seguinte comando:

    sudo chown -R <nome-de-usuario>:<nome-do-grupo> .pgdata