#/bin/bash
mkdir bin
javac -cp ".:lib/*" -d bin $(find src/ | grep .java)
