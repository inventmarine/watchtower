FROM resin/rpi-raspbian:jessie
FROM hypriot/rpi-golang:1.4.2
MAINTAINER Franklin Dattein <franklin@dattein.com>

LABEL "com.centurylinklabs.watchtower"="true"

WORKDIR /

COPY ./bin/watchtower-linux-arm /usr/local/bin/
RUN mv /usr/local/bin/watchtower-linux-arm /usr/local/bin/watchtower
RUN chmod +x /usr/local/bin/watchtower

ENTRYPOINT ["/usr/local/bin/watchtower", "cleanup=true", "debug=true", "i=3"]
