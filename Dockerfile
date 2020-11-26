FROM java:8
COPY out/production/Socket/Server   /Server

#COPY out/production/Socket/Client    /Client

EXPOSE 2020

#ENTRYPOINT ["java","Client/Client"]

ENTRYPOINT ["java","Server/Server"]
