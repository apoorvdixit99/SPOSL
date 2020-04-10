javac TestCal.java
javac -h -jni TestCal.java
gcc -I/usr/lib/jvm/java-11-openjdk-amd64/include -I/usr/lib/jvm/java-11-openjdk-amd64/include/linux -o libTestCal.so -shared -fPIC TestCal.c
java -Djava.library.path=$(pwd) TestCal
