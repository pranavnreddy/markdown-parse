import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class TryCommonMark {
    public static void main(String[] args) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse("This is *Sparta*");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        System.out.println(renderer.render(document));

        Node node = parser.parse("Example\n=======\n\nSome more text");
WordCountVisitor visitor = new WordCountVisitor();
node.accept(visitor);
System.out.println(visitor.wordCount);  // 4
    }
}

class WordCountVisitor extends AbstractVisitor {
    int wordCount = 0;
  
    @Override  
    public void visit(Text text) {
        wordCount += text.getLiteral().split("\\W+").length;
        visitChildren(text);
    }
}
