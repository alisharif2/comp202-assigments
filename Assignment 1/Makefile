JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = MakingChange.java LeapYearCalculator.java DnaUtilities.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	del *.class
