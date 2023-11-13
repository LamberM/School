FROM maven:3.9.5 AS build
WORKDIR /home/java/school
COPY src /home/java/school/src
COPY pom.xml /home/java/school
RUN mvn -f /home/java/school/pom.xml clean package

FROM openjdk:20-jdk-slim
WORKDIR /home/java/school
COPY --from=build /home/java/school/target/school-0.0.1-SNAPSHOT.jar /home/school.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/school.jar"]
