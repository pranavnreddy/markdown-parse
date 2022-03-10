CLASSPATH := lib/*:.  
RUNNER := org.junit.runner.JUnitCore

test : MarkdownParse.class MarkdownParseTest.class
	java -cp $(CLASSPATH) $(RUNNER) MarkdownParseTest

MarkdownParse.class : MarkdownParse.java
	javac MarkdownParse.java

MarkdownParseTest.class : MarkdownParseTest.java
	javac -cp $(CLASSPATH) MarkdownParseTest.java

TryCommonMark.class : TryCommonMark.java
	javac -g -cp $(CLASSPATH) TryCommonMark.java

TryCommonMark : TryCommonMark.class
	java -cp $(CLASSPATH) TryCommonMark