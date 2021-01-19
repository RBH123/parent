FROM java:8
MAINTAINER rocky
WORKDIR /tmp
ARG JAR_FILE
ADD target/${JAR_FILE} /tmp/provider.jar
ENTRYPOINT ["java","-jar","./provider.jar"]