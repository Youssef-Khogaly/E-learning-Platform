FROM eclipse-temurin:25

COPY ./target/E-learning-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

EXPOSE 8080
EXPOSE 3306

ENTRYPOINT ["java" , "-jar" , "E-learning-0.0.1-SNAPSHOT.jar"]