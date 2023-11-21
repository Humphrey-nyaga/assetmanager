#COPY  target/assetmanager.war /opt/jboss/wildfly/standalone/deployments/


#Use the official Maven image as the build stage
FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
LABEL authors="Humphrey Nyaga"
WORKDIR /app
COPY pom.xml .
COPY . .
RUN mvn clean package

FROM quay.io/wildfly/wildfly:28.0.0.Final-jdk17 AS deploy
#WORKDIR /app
COPY --from=build /app/target/assetmanager.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080 9990
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b","0.0.0.0"]
