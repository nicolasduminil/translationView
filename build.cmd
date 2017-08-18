call mvn clean install -Dnpm.skip=true
cd ear
call mvn com.oracle.weblogic:weblogic-maven-plugin:redeploy
cd ..\translationView-tests
mvn failsafe:integration-test