# !bin/bash

echo "building..."
# compile classes
javac src/**/*.java

# make jar
jar cfe  PrimeSieve.jar src.java.PrimeSieve src/java/*.class

echo "done building :)\n"

# run
java -jar ./PrimeSieve.jar