rm -rf webapps/ROOT/WEB-INF/classes/*.class

#Handle duplicate file names (Needed for my listener)
FILES=`find ${CATALINA_HOME}/webapps/ROOT/WEB-INF/classes -type f -name "*.java"`
for var in $FILES
do
    javac -cp "lib/servlet-api.jar:lib/gson-2.2.2.jar" -sourcepath "webapps/ROOT/WEB-INF/classes/" -Xlint:unchecked $var
done

if [[ "$?" -eq 0 ]]; then 
	$CATALINA_HOME/bin/shutdown.sh > /dev/null
    $CATALINA_HOME/bin/startup.sh > /dev/null
fi
