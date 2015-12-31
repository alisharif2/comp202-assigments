JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES =  $(wildcard *.java)

default: $(CLASSES)

$(CLASSES): $(CLASSES:.java=.class)

clean:
	del $(CLASSES:.java=.class)

rebuild: clean default
