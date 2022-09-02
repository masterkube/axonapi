FROM tomcat:8.5.47-jdk8-openjdk
  
COPY /target/axonapi-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

RUN ["apt-get", "update"]
RUN ["apt-get", "-y", "install", "nano"]

CMD ["catalina.sh","run"]
