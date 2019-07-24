FROM maven:3.6-jdk-8 as build
COPY src /bin/src
COPY pom.xml /bin
RUN ls -l
RUN mvn -f bin/pom.xml clean package

FROM openjdk:8-jre-alpine
RUN ls -l
COPY --from=build  bin/target/goJek-1.0-SNAPSHOT.jar bin/plotslot.jar
ENTRYPOINT ["java","-jar","bin/plotslot.jar"]
