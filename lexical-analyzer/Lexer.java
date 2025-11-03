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
                int start = i;
                i += 2;
                while (i < code.length() && code.charAt(i) != '\n' && code.charAt(i) != '\r') {
                    i++;
                }
                String lexeme = code.substring(start, i);
                tokens.add(new Tokenizer("SINGLE_LINE_COMMENT", lexeme).toString());
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
                int commentStart = i;
                i++;
                boolean foundClosing = false;
                while (i < code.length()) {
                    if (code.charAt(i) == '~') {
                        foundClosing = true;
                        i++;
                        break;
                    }
                    i++;
                }
                if (foundClosing) {
                    String lexeme = code.substring(commentStart, i);
                    String displayLexeme = lexeme.replace("\r", "\\r").replace("\n", "\\n");
                    tokens.add(new Tokenizer("MULTILINE_COMMENT", displayLexeme).toString());
                } else {
                    String invalidLexeme = code.substring(commentStart, i);
                    String displayLexeme = invalidLexeme.replace("\r", "\\r").replace("\n", "\\n");
                    tokens.add(new Tokenizer("INVALID_COMMENT", displayLexeme).toString());
                }
                continue;
            }

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