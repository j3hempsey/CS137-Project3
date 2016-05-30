rm -rf webapps/ROOT/WEB-INF/classes/*.class


#javac -cp lib/servlet-api.jar webapps/ROOT/WEB-INF/classes/Models/*.java
javac -cp "lib/servlet-api.jar:lib/gson-2.2.2.jar" -sourcepath "webapps/ROOT/WEB-INF/classes/" -Xlint:unchecked webapps/ROOT/WEB-INF/classes/*.java


if [[ "$?" -eq 0 ]]; then 
	$CATALINA_HOME/bin/shutdown.sh > /dev/null
    $CATALINA_HOME/bin/startup.sh > /dev/null
fi
