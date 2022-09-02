FROM tomcat
  
COPY /home/runner/work/axonapi/axonapi/target/axonapi-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

RUN ["apt-get", "update"]
RUN ["apt-get", "-y", "install", "nano"]

CMD ["catalina.sh","run"]
