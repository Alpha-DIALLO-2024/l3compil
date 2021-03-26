

#Compiler.jar : Compiler
#	jar cmf Compiler.mf Compiler.jar *.class sc sa ts nasm util c3a fg ig

Compiler: Compiler.java #sa/*.java c3a/*.java ts/*.java nasm/*.java fg/*.java util/intset/*.java  util/graph/*.java ig/*.java
	javac sa/*.java
	javac c3a/*.java
	javac ts/*.java
#	javac nasm/*.java
#	javac fg/*.java
#	javac util/intset/*.java
#	javac util/graph/*.java
#	javac ig/*.java
	javac Compiler.java

parser: grammaireL.sablecc
	java -jar ../sablecc/sablecc.jar grammaireL.sablecc

clean:
	find . -name "*.class" -delete
#	-rm sa/*.class
#	-rm c3a/*.class
#	-rm ts/*.class
#	-rm nasm/*.class
#	-rm fg/*.class
#	-rm ig/*.class
#	-rm util/intset/*.class
#	-rm util/graph/*.class

re: parser Compiler

test:
	./input_test
