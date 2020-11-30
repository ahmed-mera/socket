FROM java:8
COPY out/production/Socket/Server   /Server

#COPY out/production/Socket/Client    /Client

COPY out/production/Socket/Utils   /Utils

EXPOSE 2020

#ENTRYPOINT ["java","Client.Client"]

ENTRYPOINT ["java","Server.Server"]
