./setup.sh

sed "s/{WorkingDirectory}/${1}/" ../pastore.service.template >> /etc/systemd/system/pastore.service

# Create and move maven build

# chown -R pastore:pastore $1