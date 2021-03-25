#!/bin/bash

# Stop pastore service if it's running
systemctl stop pastore.service >> /dev/null 2>&1

# Remove pastore user
if id -u "pastore" > /dev/null 2>&1; then
    userdel pastore
    if test $? -eq 0; then
        echo "pastore user successfully removed"
    else
        echo "can't delete pastore user, check permissions"
        exit 1
    fi
else
    echo "pastore user is no longer exist"
    # Remove pastore group
    if getent group "pastore" > /dev/null 2>&1; then
        groupdel pastore
        if test $? -eq 0; then
            echo "pastore group successfully removed"
        else
            echo "can't delete pastore group, check permissions"
            exit 1
        fi
    else
        echo "pastore group is no longer exist"
    fi
fi

# Remove service file from systemd
if [ -f "/etc/systemd/system/pastore.service" ]; then
    rm /etc/systemd/system/pastore.service
    if test $? -eq 0; then
        echo "/etc/systemd/system/pastore.service successfully removed"
    else
        echo "can't delete /etc/systemd/system/pastore.service, check permissions"
        exit 1
    fi
fi

# Remove /etc/pastore
if [ -d "/etc/pastore" ]; then
    rm -r /etc/pastore
    if test $? -eq 0; then
        echo "/etc/pastore successfully removed"
    else
        echo "can't delete /etc/pastore, check permissions"
        exit 1
    fi
fi

# Remove /var/lib/pastore
if [ -d "/var/lib/pastore" ]; then
    rm -r /var/lib/pastore
    if test $? -eq 0; then
        echo "/var/lib/pastore successfully removed"
    else
        echo "can't delete /var/lib/pastore, check permissions"
        exit 1
    fi
fi

# Remove /var/log/pastore
if [ -d "/var/log/pastore" ]; then
    rm -r /var/log/pastore
    if test $? -eq 0; then
        echo "/var/log/pastore successfully removed"
    else
        echo "can't delete /var/log/pastore, check permissions"
        exit 1
    fi
fi

echo "pastore successfully uninstalled!!!"