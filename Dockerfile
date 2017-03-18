FROM openjdk:8-jre
WORKDIR /usr/src/
COPY knowledge-spider/target/knowledge-spider-0.0.1.jar /usr/src/
CMD ["java", "-jar", "knowledge-spider-0.0.1.jar", "--spring.cloud.config.profile=dev",">>", "/usr/src/columbia.log"]
RUN ln -sf /usr/data /etc/localtime
EXPOSE 9595