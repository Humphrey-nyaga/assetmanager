#FROM quay.io/wildfly/wildfly:28.0.0.Final-jdk17
#LABEL authors="hum"
#ADD  ./target/assetmanager.war /opt/jboss/wildfly/standalone/deployments/
# Use the official Maven image as the build stage
FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn clean compile package

FROM quay.io/wildfly/wildfly:28.0.0.Final-jdk17

COPY --from=build /app/target/assetmanager.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080 9090

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b","0.0.0.0"]