FROM maven:3.9.9-eclipse-temurin-21
WORKDIR /app
COPY ../AssetInsight/pom.xml .
COPY ../AssetInsight/src ./src
LABEL authors="Antony & Wallyson"

CMD ["mvn", "spring-boot:run"]