#!/bin/bash
mvn clean install
mvn test
if [ $? -eq 0 ]
then
  mvn spring-boot:run
else
  echo "Os testes falharam."
  exit 1
fi