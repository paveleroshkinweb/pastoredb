# Main build file

if [ $# -ne 1 ]; then
    echo "Please run build script with a folder path as an argument, for example\nsudo ./build.sh /home/pavel/pastore"
    exit 1
fi

if [ ! -d $1 ]; then
    echo "Please provide correct folder path, such directory does not exist!"
    exit 1
fi

./setup.sh
if test $? -ne 0; then
    echo "setup failed, aborting ..."
    exit 1
fi

./rebuild.sh $1
if test $? -ne 0; then
    echo "rebuild failed, aborting ..."
    exit 1
fi

sed --expression "s@{WorkingDirectory}@${1}@" ../pastore.service.template >> /etc/systemd/system/pastore.service
if test $? -eq 0; then
    echo "pastore.service successfully updated"
else
    echo "can't update pastore.service, check permissions"
    exit 1
fi

echo "build successfully passed!!!"