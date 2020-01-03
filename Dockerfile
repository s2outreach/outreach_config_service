FROM openjdk:8
COPY /target/outreach_config_service-0.0.1-SNAPSHOT.jar /
EXPOSE 8888
CMD ["java","-jar","-Deureka.datacenter=cloud","-Dspring.profiles.active=aws","outreach_config_service-0.0.1-SNAPSHOT.jar"]