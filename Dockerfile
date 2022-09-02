FROM tomcat
  
COPY ./target/*.war /usr/local/tomcat/webapps/

RUN ["apt-get", "update"]
RUN ["apt-get", "-y", "install", "nano"]

CMD ["catalina.sh","run"]
