FROM openjdk:11.0.8-slim-buster

ENV USERNAME=molly
RUN useradd -m $USERNAME && adduser $USERNAME sudo
USER $USERNAME

WORKDIR /home/$USERNAME/app
COPY --chown=$USERNAME . .
RUN ./gradlew tasks

ENTRYPOINT ["./script.sh"]
