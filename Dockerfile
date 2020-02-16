FROM openjdk:8
VOLUME /tmp
ADD build/libs/Examples-1.0-SNAPSHOT.jar Examples-1.0-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","Examples-1.0-SNAPSHOT.jar"]