FROM jenkins/jenkins:lts-jdk11

USER root

# JMeter Version
ARG JMETER_VERSION="5.4.3"

# Install Docker
RUN apt-get update

RUN apt-get install -y \
        apt-transport-https \
        ca-certificates \
        curl \
        gnupg2 \
        software-properties-common

RUN curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o/usr/share/keyrings/docker-archive-keyring.gpg

#Install sudo
RUN apt-get update && apt-get install -y sudo

#Set jenkins user to not ask for password
RUN echo "jenkins ALL=(ALL) NOPASSWD: ALL" >> /etc/sudoers

# Install gosu
RUN apt-get update && apt-get install -y gosu

RUN echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/debian $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list

RUN apt update

RUN apt-get install -y docker.io

# Compose

RUN curl -L "https://github.com/docker/compose/releases/download/v2.17.3/docker-compose-linux-aarch64" -o /usr/local/bin/docker-compose && chmod +x /usr/local/bin/docker-compose

#USER jenkins

# Download and unpack the JMeter tar file
RUN cd /opt \
 && apt-get install -y wget \
 && wget https://mirrors.estointernet.in/apache/jmeter/binaries/apache-jmeter-${JMETER_VERSION}.tgz \
 && tar xzf apache-jmeter-${JMETER_VERSION}.tgz \
 && rm apache-jmeter-${JMETER_VERSION}.tgz

# Create a symlink to the jmeter process in a normal bin directory
RUN ln -s /opt/apache-jmeter-${JMETER_VERSION}/bin/jmeter /usr/local/bin

# Copying custom property file
#COPY user.properties /opt/apache-jmeter-${JMETER_VERSION}/bin/user.properties

CMD ["sh", "-c", "sudo chown jenkins /var/run/docker.sock && gosu jenkins /usr/local/bin/jenkins.sh; jmeter"]

