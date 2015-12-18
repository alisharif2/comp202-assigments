JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

# List all .java files to be compiled
CLASSES =  $(wildcard *.java)

default: classes

classes: $(CLASSES:.java=.class)

clean:
	del $(CLASSES:.java=.class)

rebuild: clean default
