# !bin/bash

echo "building..."
# compile classes
javac src/**/*.java

# make jar
jar cfe  PrimeSieve.jar src.java.PrimeSieve src/java/*.class

echo "done building :)\n"

# run
# set JRE heap size to 4g. adjust as needed
java -Xmx4g -jar ./PrimeSieve.jar