FROM openjdk:8-jdk-alpine
MAINTAINER gumoises@live.com
COPY target/stock-quote-manager.jar /stock-quote-manager.jar
ENTRYPOINT ["java","-jar","/stock-quote-manager.jar"]