#
# define compile and compiler flag variables
#

JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		AcceptanceTestsTest.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
