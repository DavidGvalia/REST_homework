FROM openjdk:17-jdk-alpine

EXPOSE 8080

ADD target/ROOT.jar ROOT.jar

CMD ["java", "-jar", "ROOT.jar"]