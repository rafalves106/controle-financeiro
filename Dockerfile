# Estágio 1: Build (Compilação)
FROM maven:3.8.8-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Run (Execução)
FROM eclipse-temurin:17-jdk-alpine
# O Maven gera o jar dentro da pasta target.
# Substitua 'seu-projeto.jar' pelo nome real do arquivo gerado ou use o wildcard abaixo:
COPY --from=build /target/*.jar app.jar

# Porta padrão que o Render espera (ou a que sua aplicação usa)
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]