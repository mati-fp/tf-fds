FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
EXPOSE 8080
ENTRYPOINT ["mvn", "spring-boot:run"]
