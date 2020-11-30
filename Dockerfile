FROM java:8
COPY out/production/Socket/Server   /prod/Server

#COPY out/production/Socket/Client    /prod/Client

COPY out/production/Socket/Utils   /prod/Utils

EXPOSE 2020

#ENTRYPOINT ["java","prod/Client/Client"]

ENTRYPOINT ["java","prod/Server/Server"]
