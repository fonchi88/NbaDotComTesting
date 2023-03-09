**Project Setup**

Links:
- https://medium.com/swlh/a-java-project-with-cucumber-6-testng-and-maven-c58990d2bcff
- https://toolsqa.com/cucumber-tutorial/
- https://medium.com/@jitendra.pisal44/cucumber-pico-container-use-in-automation-79c597d0ef04
- https://intellij-support.jetbrains.com/hc/en-us/community/posts/360010047820/comments/360002477659
- https://www.jetbrains.com/help/idea/run-debug-configuration-cucumber-java.html
- https://intellij-support.jetbrains.com/hc/en-us/community/posts/360010047820-zsh-command-not-found-mvn

Pending Items Before the Demo:

- ~~Config files~~
- ~~Driver Manager~~
- ~~Remote Driver~~
- ~~Reports~~
- ~~Log Manager~~
- ~~Jenkins and Docker containers~~

Docker Setup:

1. docker network create -d bridge --subnet 172.124.10.0/24 --gateway 172.124.10.1  selenium-network
2. docker run --network selenium-network  --name seleniumgridchrome  --rm -it -p 4444:4444 -p 5900:5900 -p 7900:7900 --shm-size 2g seleniarm/standalone-chromium:latest
3. docker run --name jenkins --network selenium-network  -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts-jdk11

Execution:

- Open you CLi and navigate to the project´s root folder
- Run "docker-compose up" command in your CLI
- Log into jenkins UI and build the gorilla-demo job