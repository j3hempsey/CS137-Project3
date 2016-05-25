rm -rf webapps/ROOT/WEB-INF/classes/*.class
javac -cp lib/servlet-api.jar webapps/ROOT/WEB-INF/classes/*
$CATALINA_HOME/bin/shutdown.sh > /dev/null
$CATALINA_HOME/bin/startup.sh > /dev/null
