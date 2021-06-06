FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/python/tier_bronze.json /json/tier_bronze.json
COPY src/main/resources/python/tier_silver.json /json/tier_silver.json
COPY src/main/resources/python/tier_gold.json /json/tier_gold.json
COPY src/main/resources/python/tier_platinum.json /json/tier_platinum.json
COPY src/main/resources/python/tier_diamond.json /json/tier_diamond.json
COPY src/main/resources/python/tier_ruby.json /json/tier_ruby.json
ENTRYPOINT ["java","-jar","/app.jar"]