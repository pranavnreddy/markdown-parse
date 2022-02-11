// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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
            if(nextOpenBracket == -1 || nextCloseBracket == -1) {
                break;
            }
            if(nextOpenBracket > 0 && markdown.charAt(nextOpenBracket-1) == '!') {
                currentIndex = nextOpenBracket + 1;
                continue;
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
<<<<<<< HEAD
            int openParenLoop = openParen;
            int closeParen = markdown.indexOf(")", openParen+1);
            int closeParenLoop = closeParen;
            int openParenCounter = 1;
            int closeParenCounter = 0;
            int minParen = openParen+1;
            while(openParenCounter > closeParenCounter) {
                openParenLoop = markdown.indexOf("(", minParen);
                closeParenLoop = markdown.indexOf(")", minParen);
                if(openParenLoop != -1 && openParenLoop < closeParenLoop) {                    
                    openParenCounter++;
                    minParen = openParenLoop+1;
                }
                else {
                    closeParenCounter++;
                    minParen = closeParenLoop+1;
                }
                closeParen = closeParenLoop;
            }
            if(openParen == -1 || closeParen == -1) {
                break;
            }
            if(openParen-1==nextCloseBracket) {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
=======
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
>>>>>>> refs/remotes/origin/main
            }
            currentIndex = closeParen + 1;
            // System.out.println("Value of current index after loop: " + currentIndex);
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
