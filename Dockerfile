FROM java:8
EXPOSE 8080

WORKDIR /workspace
ADD sunjietestwarehouse.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
