// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        int pastCloseParen = 0;
        while (currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);

            // System.out.println("Value of current index before loop: " + currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            // Check link format
            Boolean validFormat = false;
            if (pastCloseParen == closeParen || nextOpenBracket < 0 || openParen < 0
                    || closeParen < 0) {
                break;
            }
            if (markdown.substring(nextCloseBracket, nextCloseBracket + 2).equals("]]")) {
                break;
            }
            String tempString = markdown.substring(openParen + 1, closeParen);
            if (!tempString.contains("[") && !tempString.contains("]") && !tempString.contains("(")
                    && !tempString.contains(")")) {
                validFormat = true;
            }
            if (validFormat == false) {
                nextOpenBracket = markdown.indexOf("[", openParen);
                nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
                openParen = markdown.indexOf("(", nextCloseBracket);
                closeParen = markdown.indexOf(")", openParen);
            }
            // end
            if (pastCloseParen == closeParen || nextOpenBracket < 0 || openParen < 0
                    || closeParen < 0) {
                break;
            }
            pastCloseParen = closeParen;

            // System.out.println("Index of next open bracket: " + nextOpenBracket);
            // System.out.println("Index of next open bracket - 1: " + (nextOpenBracket - 1));
            if (nextOpenBracket > 0) {
                if (!markdown.substring(nextOpenBracket - 1, nextOpenBracket).equals("!")
                        && markdown.substring(openParen - 1, openParen).equals("]")) {
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
            } else {
                if (markdown.substring(openParen - 1, openParen).equals("]")) {
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
            }
            currentIndex = closeParen + 1;
            // System.out.println("Value of current index after loop: " + currentIndex);
        }
        return toReturn;
    }

    public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if (dirOrFile.isDirectory()) {
            for (File f : dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
            }
            return result;
        } else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if (lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        Map<String, List<String>> links = getLinks(new File(args[0]));
        System.out.println(links);
    }
}
