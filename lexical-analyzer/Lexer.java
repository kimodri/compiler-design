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