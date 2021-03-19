#!/bin/bash

systemctl daemon-reload
systemctl start pastore.service
systemctl status pastore.service