FROM resin/rpi-raspbian:jessie
FROM hypriot/rpi-golang:1.4.2
MAINTAINER Franklin Dattein <franklin@dattein.com>

LABEL "com.centurylinklabs.watchtower"="true"

WORKDIR /
COPY watchtower /
RUN chmod +x watchtower

ENTRYPOINT ["/watchtower"]
