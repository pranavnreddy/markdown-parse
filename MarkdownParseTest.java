import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

// To compile/run:
// javac -cp ".;lib\junit-4.13.2.jar;lib\hamcrest-core-1.3.jar" MarkdownParseTest.java
// java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore
// MarkdownParseTest

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testGetLinks1() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();
        linkTester.add("https://something.com");
        linkTester.add("some-page.html");

        Path fileName = Path.of("test-file.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }

    @Test
    public void testGetLinks2() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();
        linkTester.add("https://something.com");
        linkTester.add("https://something.com");

        Path fileName = Path.of("test-file2.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }

    @Test
    public void testGetLinks3() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();
        linkTester.add("https://youtube.com");

        Path fileName = Path.of("test-file3.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }

    @Test
    public void testGetLinks4() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();
        linkTester.add("https://random.com");

        Path fileName = Path.of("test-file4.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }

    @Test
    public void testGetLinks5() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();

        Path fileName = Path.of("test-file5.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }

    @Test
    public void testGetLinks6() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();
        linkTester.add("something.com");

        Path fileName = Path.of("test-file6.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }

    @Test
    public void testGetLinks7() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();
        // linkTester.add("something.com");

        Path fileName = Path.of("test-file7.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }

    @Test
    public void testSnippet1() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();
        linkTester.add("`google.com");
        linkTester.add("google.com");
        linkTester.add("ucsd.edu");

        Path fileName = Path.of("snippet1.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }

    @Test
    public void testSnippet2() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();
        linkTester.add("a.com");
        linkTester.add("a.com(())");
        linkTester.add("example.com");

        Path fileName = Path.of("snippet2.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }

    @Test
    public void testSnippet3() throws IOException {
        ArrayList<String> linkTester = new ArrayList<>();
        linkTester.add("https://www.twitter.com");
        linkTester.add("https://ucsd-cse15l-w22.github.io/");
        linkTester.add("https://cse.ucsd.edu/");

        Path fileName = Path.of("snippet3.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(linkTester, links);
    }
}
