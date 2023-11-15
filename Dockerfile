FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
EXPOSE 8080
# ENTRYPOINT ["mvn", "spring-boot:run"]
RUN chmod +x ./entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]

# Se der pau no .pgdata por exemplo utilizar comando: 
# sudo chown -R pole:pole .pgdata -> Esse comando está usando de base o usuário pole e o grupo pole
# substitutir pelo seu usuário e grupo, normalmente é o mesmo nome do usuário
# para descobrir o nome de seu usuário e grupo, digite o comando: id


# Se der problema de permissão também algo do tipo no entrypoint utilizar o comando:
# sudo chmod +x ./entrypoint.sh
# Isso vai dar permissão de execução para o arquivo entrypoint.sh