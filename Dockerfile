FROM tomcat:9.0.48-jdk11-openjdk-slim
  
COPY /target/axonapi-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/axonapi.war

RUN ["apt-get", "update"]
RUN ["apt-get", "-y", "install", "nano"]

CMD ["catalina.sh","run"]
