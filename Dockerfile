FROM maven:3.6-jdk-8 as build
COPY src /src
COPY pom.xml /
COPY parking_lot.sh /
COPY setup.sh /
RUN chmod +x ./parking_lot.sh
RUN chmod +x ./setup.sh
#CMD ["bash ./setup.sh", "bash ./parking_lot src/main/resources/file_input.txt"]
