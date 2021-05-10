FROM jenkins/jenkins:lts

USER root

RUN apt update && apt install ruby -y && gem install dip \
    && rm -rf /var/lib/apt/lists/*

RUN groupadd --gid 1001 docker && usermod -a -G docker jenkins

USER jenkins
