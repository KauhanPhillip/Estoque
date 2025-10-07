# Stage de build com Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o pom.xml e a pasta src
COPY pom.xml .
COPY src ./src

# Baixa dependências offline
RUN mvn dependency:go-offline

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# Stage final com JDK para rodar a aplicação
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia o JAR do stage de build
COPY --from=build /app/target/*.jar app.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
