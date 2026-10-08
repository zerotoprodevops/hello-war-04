#stage: compile
FROM maven:3.10.0-eclipse-temurin-17-alpine AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package

#stage: run

FROM tomcat:10.1.60-jre17-temurin-resolute
RUN sudo apt update & apt  upgrade -y

WORKDIR /usr/local/tomcat
COPY --from=build /build/target/hello.war ./webapps/hello.war

EXPOSE 8080

CMD ["run"]
ENTRYPOINT ["catalina.sh"]
