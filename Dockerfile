#!/bin/bash
FROM openjdk:8
EXPOSE 8080
ADD ./build/libs/hotdesk-0.0.1.jar ./hotdesk-0.0.1.jar
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+0530","hotdesk-0.0.1.jar"]
