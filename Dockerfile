FROM maven:3.6-jdk-8 as build
COPY src home/usr/bin/src
COPY pom.xml home/usr/bin
RUN mvn home/usr/bin/pom.xml clean install

FROM openjdk:8-jre
COPY --from=build  usr/bin/target/gojek-1.0-SNAPSHOT.jar home/usr/bin/plotslot.jar
ENTRYPOINT ["java","-jar","home/usr/bin/plotslot.jar"]
