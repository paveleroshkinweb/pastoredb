if [ $# -ne 1 ]; then
    echo "Please run build script with a folder path as an argument, for example\nsudo ./rebuild.sh /home/pavel/pastore"
    exit 1
fi

if [ ! -d $1 ]; then
    echo "Please provide correct folder path, such directory does not exist!"
    exit 1
fi

cd ../pastore

mvn clean package
if test $? -ne 0; then
    echo "can't create a build, aborting..."
    exit 1
fi

if [ -f "$1/pastore-1.0.jar" ]; then
    rm "$1/pastore-1.0.jar"
fi

cp target/pastore-1.0-jar-with-dependencies.jar "$1/pastore-1.0.jar"
if test $? -ne 0; then
    echo "can't move pastore-1.0.jar into $1, check permissions"
    exit 1
fi

chown -R pastore:pastore "$1/"

echo "rebuild funished successfully, pastore-1.0.jar moved to $1"