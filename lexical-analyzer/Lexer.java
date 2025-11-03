import java.util.List; 
import java.util.ArrayList;

public class Lexer{
    public static List<String> tokens = new ArrayList<>();

    public static void lex(String code) {

        int i = 0;
        while (i < code.length()) {
            char ch = code.charAt(i);

            // Skip whitespace
            if (Character.isWhitespace(ch)) {
                i++;
                continue;
            }

            // Single-line comment
            if (ch == '~' && i + 1 < code.length() && code.charAt(i + 1) == '~') {
                String lexeme = "~~";
                String token = LookupTable.getTokenType(lexeme);
                tokens.add(new Tokenizer(token, lexeme).toString());
                
                i += 2;
                // Skip until end of line
                while (i < code.length() && code.charAt(i) != '\n' && code.charAt(i) != '\r') {
                    i++;
                }
                
                if (i < code.length() && code.charAt(i) == '\r') {
                    i++;
                }
                if (i < code.length() && code.charAt(i) == '\n') {
                    i++;
                }
                continue;
            }

            // Multi-line comment
            if (ch == '~') {
                int commentStart = i; // Track where comment started for error reporting
                String lexeme = "~";
                String token = LookupTable.getTokenType(lexeme);
                tokens.add(new Tokenizer(token, lexeme).toString());
                
                i++;
                boolean foundClosing = false;
                // Skip until closing '~'
                while (i < code.length()) {
                    if (code.charAt(i) == '~') {
                        foundClosing = true;
                        i++;
                        break;
                    }
                    i++;
                }
                if (!foundClosing) {
                    int lineNumber = 1;
                    int colNumber = 1;
                    for (int j = 0; j < commentStart; j++) {
                        if (code.charAt(j) == '\n') {
                            lineNumber++;
                            colNumber = 1;
                        } else if (code.charAt(j) != '\r') {
                            colNumber++;
                        }
                    }
                    throw new RuntimeException("Syntax Error: Unclosed multi-line comment starting at line " + lineNumber + ", column " + colNumber + ". Expected closing '~'.");
                }
                continue;
            }

            // Detect operators
            String operatorChars = "+-*/%=<>!";
            if (operatorChars.indexOf(ch) != -1) {

                StringBuilder operator = new StringBuilder();
                operator.append(ch);

            // Detect operators
            String operatorChars = "+-*/%=<>!";
            if (operatorChars.indexOf(ch) != -1) {

                StringBuilder operator = new StringBuilder();
                operator.append(ch);

                // Check for two-character operators
                if (i + 1 < code.length()) {
                    char next = code.charAt(i + 1);
                    if ((ch == '=' && next == '=') ||
                        (ch == '!' && next == '=') ||
                        (ch == '<' && next == '=') ||
                        (ch == '>' && next == '=') ||
                        (ch == '*' && next == '*')) {
                        operator.append(next);
                        i++;
                    }
                }

                String lexeme = operator.toString();
                String token = LookupTable.getTokenType(lexeme);

                tokens.add(new Tokenizer(token, lexeme).toString());
                i++;

                // System.out.print("Lexeme: " + lexeme);
                // System.out.print("Token: " + token);

                continue;
            }
            
            // TODO: handle identifiers, numbers, etc.
            i++;
        }
    }
}