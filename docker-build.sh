docker build --tag=10.118.169.49:5000/devops-metrics-jenkins:0.1.0 --rm=true .
docker push 10.118.169.49:5000/devops-metrics-jenkins:0.1.0
docker run -p 8080:8080 --restart=always 10.118.169.49:5000/devops-metrics-jenkins:0.1.0