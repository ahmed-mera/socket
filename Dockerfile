FROM java:8
COPY src/Server/Server.java   /

#COPY src/Client/Client.java    /

EXPOSE 2020

#ENTRYPOINT ["java","/Client.java"]

ENTRYPOINT ["java","/Server.java"]
