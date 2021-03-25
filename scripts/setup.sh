#!/bin/bash

# Create pastore group
if getent group "pastore" > /dev/null 2>&1; then
    echo "pastore group exists!"
else
    groupadd -r pastore
    if test $? -eq 0; then
        echo "group pastore created!"
    else
        echo "can't create group pastore, check permissions"
        exit 1
    fi
fi

# Create pastore user
if id -u "pastore" > /dev/null 2>&1; then
    echo "pastore user exists!"
else
    useradd -r -s /sbin/nologin -g pastore pastore
    if test $? -eq 0; then
        echo "user pastore created!"
    else
        echo "can't create user pastore, check permissions"
        exit 1
    fi
fi

# Create pastore.service
if [ -f "/etc/systemd/system/pastore.service" ]; then
    echo "pastore.service exists, removing it and creating new"
    rm /etc/systemd/system/pastore.service
fi
touch /etc/systemd/system/pastore.service
if test $? -eq 0; then
    echo "pastore.service file created!"
else
    echo "can't create or change mode /etc/systemd/system/pastore.service, check permissions"
    exit 1
fi

# Create /etc/pastore
if [ -d "/etc/pastore" ]; then
    echo "/etc/pastore already exists!"
else
    mkdir /etc/pastore
    if test $? -eq 0; then
        echo "/etc/pastore directore created!"
    else
        echo "can't create /etc/pastore, check permissions"
        exit 1
    fi
fi

# Move pastore.properties to /etc/pastore
cp ../pastore.properties /etc/pastore/pastore.properties
if test $? -eq 0; then
    echo "pastore.properties successfully moved to /etc/pastore/pastore.properties"
else
    echo "can't move pastore.properties to /etc/pastore/, check permissions"
    exit 1
fi

# Create secrets.env in /etc/pastore
if [ -f "/etc/pastore/secrets.env" ]; then
    echo "/etc/pastore/secrets.env already exists, clearing it"
    echo -n "" > /etc/systemd/system/secrets.env
else
    touch /etc/pastore/secrets.env
    if test $? -eq 0; then
        echo "/etc/pastore/secrets.env successfully created!"
    else
        echo "cant create /etc/pastore/secrets.env, check permissions"
        exit 1
    fi
fi

# Change rights for pastore folder 
chown -R pastore:pastore /etc/pastore
if test $? -eq 0; then
    echo "owner for /etc/pastore successfully changed on pastore"
else
    echo "can't change rights for /etc/pastore, check permissions"
    exit 1
fi

# Create /var/lib/pastore
if [ ! -d "/var/lib/pastore" ]; then
    mkdir /var/lib/pastore
    touch /var/lib/pastore/pastore-dump.pdb
    touch /var/lib/pastore/pastore-history.phist
    chown -R pastore:pastore /var/lib/pastore
    if test $? -eq 0; then
        echo "/var/lib/pastore created!"
    else
        echo "can't create /var/lib/pastore"
        exit 1
    fi
fi

# Create /var/log/pastore
if [ ! -d "/var/log/pastore" ]; then
    mkdir /var/log/pastore
    touch /var/log/pastore/pastore.log
    chown -R pastore:pastore /var/log/pastore
    if test $? -eq 0; then
        echo "/var/log/pastore created!"
    else
        echo "can't create /var/log/pastore"
        exit 1
    fi
fi

echo "setup successfully passed!!"