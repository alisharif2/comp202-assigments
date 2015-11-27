JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

# List all .java files to be compiled
CLASSES =  CardGame.java UtilityCode.java Suit.java Value.java Card.java CardPile.java Sudoku.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	del $(CLASSES:.java=.class)

rebuild: clean default
