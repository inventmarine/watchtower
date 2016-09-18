FROM resin/rpi-raspbian:jessie
FROM hypriot/rpi-golang:1.4.2
MAINTAINER Franklin Dattein <franklin@dattein.com>

LABEL "com.centurylinklabs.watchtower"="true"

WORKDIR /
COPY watchtower /

ENTRYPOINT ["/watchtower cleanup=true debug=true i=3"]
