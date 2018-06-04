FROM 10.118.169.49:5000/demo-base-image

RUN echo localhost 10.118.169.49 >> /etc/hosts ; cat /etc/hosts
RUN echo 10.118.169.48 jenkins.ci-server.com >> /etc/hosts ; cat /etc/hosts
RUN echo 10.118.169.48 sonar.ci-server.com >> /etc/hosts ; cat /etc/hosts

VOLUME /tmp
ADD target/devops-metrics-jenkins-0.1.0.jar app.jar
RUN touch /app.jar

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
