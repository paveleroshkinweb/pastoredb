#!/bin/bash

systemctl stop pastore.service

userdel pastore

rm /etc/systemd/system/pastore.service
rm -r /etc/pastore