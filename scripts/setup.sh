#!/bin/bash

groupadd -r pastore
useradd -r -s /sbin/nologin -g pastore pastore

touch /etc/systemd/system/pastore.service
chown pastore:pastore /etc/systemd/system/pastore.service

mkdir /etc/pastore
cp ../pastore.properties /etc/pastore/pastore.properties
cp ../secrets.env /etc/pastore/secrets.env
chown -R pastore:pastore /etc/pastore