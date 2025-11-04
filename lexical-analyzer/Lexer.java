import java.util.ArrayList;
import java.util.List;

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

            // Check for invalid characters
            if (ch > 127){
                tokens.add(new Tokenizer("INVALID_CHAR", String.valueOf(ch)).toString());
                i++;

                continue;
            }
            // Handle Identifiers
            if (Character.isLetter(ch)) {
                    StringBuilder lexemeBuilder = new StringBuilder();
                    // Collect possible identifier characters
                    while (i < code.length()) {
                        char curr = code.charAt(i);
                        if (Character.isLetterOrDigit(curr) || curr == '_') {
                            lexemeBuilder.append(curr);
                            i++;
                        } else {
                            break;
                        }
                    }
                    String lexeme = lexemeBuilder.toString();
                    // Apply identifier validation rules
                    String identifierPattern = "^[A-Za-z](?:_?[A-Za-z0-9]){0,63}$";
                        if (!lexeme.matches(identifierPattern)) {
                            throw new RuntimeException("Invalid identifier: '" + lexeme + "'");
                        }
                    // Check against reserved keywords
                    String token = LookupTable.getTokenType(lexeme);
                    if (token != null && !token.equals("INVALID") && !token.equals("INVALID_DELIMITER")) {  // Added check for INVALID_DELIMITER
                        tokens.add(new Tokenizer(token, lexeme).toString());
                    } else {
                        // Default to IDENTIFIER for valid, unmapped lexemes
                        tokens.add(new Tokenizer("IDENTIFIER", lexeme).toString());
                    }
                    continue;
                }

           // Check for special characters
            if (!Character.isLetterOrDigit(ch) && !Character.isWhitespace(ch)) {
                String symbol = String.valueOf(ch);

                // Check if '.' is used in a float (digit before and after)
                if (ch == '.') {
                    boolean hasDigitBefore = (i > 0) && Character.isDigit(code.charAt(i - 1));
                    boolean hasDigitAfter = (i + 1 < code.length()) && Character.isDigit(code.charAt(i + 1));

                    if (hasDigitBefore && hasDigitAfter) {
                        // Part of a float number → skip classification here
                        i++;
                        continue;
                    }
                }

                // Check if '_' is part of an identifier (letter/digit before or after)
                if (ch == '_') {
                    boolean hasLetterOrDigitBefore = (i > 0) && Character.isLetterOrDigit(code.charAt(i - 1));
                    boolean hasLetterOrDigitAfter = (i + 1 < code.length()) && Character.isLetterOrDigit(code.charAt(i + 1));

                    if (hasLetterOrDigitBefore || hasLetterOrDigitAfter) {
                        // Part of an identifier → skip classification here
                        i++;
                        continue;
                    }
                }

                String tokenType = LookupTable.getTokenType(symbol);

                if (tokenType == null) {
                    tokens.add(new Tokenizer("SPECIAL_CHAR", symbol).toString());
                    i++;
                    continue;
                } 
            }


            // Detect operators
            String operatorChars = "+-*/%=<^>!";
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
                        (ch == '/' && next == '/') ||
                        (ch == '+' && next == '+') ||
                        (ch == '-' && next == '-')) {
                        operator.append(next);
                        i++;
                    }
                }

                String lexeme = operator.toString();
                String token = LookupTable.getTokenType(lexeme);

                //pre/post increment and decrement handling
                if (lexeme.equals("++") || lexeme.equals("--")) {
                    boolean isPre = false;
                    int peekIndex = i + 1;
                    while (peekIndex < code.length() && Character.isWhitespace(code.charAt(peekIndex))) {
                        peekIndex++;
                    }
                    if (peekIndex < code.length() && Character.isLetter(code.charAt(peekIndex))) {
                        isPre = true;
                    }
                    
                    boolean isPost = false;
                    if (!tokens.isEmpty()) {
                        String lastToken = tokens.get(tokens.size() - 1);
                        if (lastToken.startsWith("IDENTIFIER,")) {
                            isPost = true;
                        }
                    }
                    
                    if (isPost) {
                        if (lexeme.equals("++")) {
                            token = "POST_INCREMENT_OP";
                        } else {
                            token = "POST_DECREMENT_OP";
                        }
                    } else if (isPre) {
                        if (lexeme.equals("++")) {
                            token = "PRE_INCREMENT_OP";
                        } else {
                            token = "PRE_DECREMENT_OP";
                        }
                    }
                }
                
                //unary + and - handling
                if (lexeme.equals("+") || lexeme.equals("-")) {
                    boolean isUnary = false;
                    
                    if (tokens.isEmpty()) {
                        isUnary = true;
                    } else {
                        String lastToken = tokens.get(tokens.size() - 1);
                        boolean isBinary = lastToken.startsWith("IDENTIFIER,") ||
                            lastToken.startsWith("NUM_LITERAL,") ||
                            lastToken.startsWith("DECIMAL_LITERAL,") ||
                            lastToken.startsWith("RIGHT_PARENTHESIS,") ||
                            lastToken.startsWith("RIGHT_SQUARE_BRACKET,") ||
                            lastToken.startsWith("RIGHT_CURLY_BRACE,") ||
                            lastToken.startsWith("POST_INCREMENT_OP,") ||
                            lastToken.startsWith("POST_DECREMENT_OP,");
                        
                        if (!isBinary) {
                            isUnary = true;
                        }
                    }
                    
                    if (isUnary) {
                        if (lexeme.equals("+")) {
                            token = "POSITIVE_OP";
                        } else {
                            token = "NEGATIVE_OP";
                        }
                    }
                }

                tokens.add(new Tokenizer(token, lexeme).toString());
                i++;

                continue;
            }
            
            if (Character.isLetter(ch)) {
                StringBuilder identifier = new StringBuilder();

                while (i < code.length() && 
                    (Character.isLetterOrDigit(code.charAt(i)) || code.charAt(i) == '_')) {
                    identifier.append(code.charAt(i));
                    i++;
                }

                String word = identifier.toString();

                if (LookupTable.getTokenType(word) != null) {
                    tokens.add(new Tokenizer(LookupTable.getTokenType(word), word).toString());
                } else {
                    // Check first if valid
                    String identifierPattern = "^[A-Za-z](?:_?[A-Za-z0-9]){0,63}$";
                    if (!word.matches(identifierPattern)) {
                        tokens.add(new Tokenizer("Invalid identifier", word).toString());
                    }
                    tokens.add(new Tokenizer("IDENTIFIER", word).toString());
                }
                continue;
        }

            // Strings (double-quoted)
            if (ch == '"') {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(ch);

                // lagay muna yung quoute 
                // tokens.add(new Tokenizer(LookupTable.getTokenType(String.valueOf(ch)), String.valueOf(ch)).toString());

                i++;
                boolean foundClosing = false;
                
                while (i < code.length()) {
                    char curr = code.charAt(i);
                    if (curr == '\\' && i + 1 < code.length()) {
                        stringBuilder.append(curr);
                        stringBuilder.append(code.charAt(i + 1));
                        i += 2;
                    } else if (curr == '"') {
                        stringBuilder.append(curr);

                        // lagay yung quote
                        // String lexeme = stringBuilder.toString();
                        // tokens.add(new Tokenizer("STRING_LITERAL", lexeme).toString()); 
                        // tokens.add(new Tokenizer(LookupTable.getTokenType(String.valueOf(curr)), String.valueOf(curr)).toString());

                        foundClosing = true;
                        i++;
                        break;
                    } else {
                        stringBuilder.append(curr);
                        i++;
                    }
                }
                
                String lexeme = stringBuilder.toString();
                if (!foundClosing) {
                    tokens.add(new Tokenizer("INVALID_TEXT", lexeme).toString());
                } else {
                    tokens.add(new Tokenizer("TEXT_LITERAL", lexeme).toString());
                }
                continue;
            }
            
            // Chars/Symbols (single-quoted)
            if (ch == '\'') {
                StringBuilder charBuilder = new StringBuilder();
                charBuilder.append(ch);
                i++;
                boolean foundClosing = false;
                
                if (i < code.length()) {
                    char curr = code.charAt(i);
                    if (curr == '\\' && i + 1 < code.length()) {
                        charBuilder.append(curr);
                        charBuilder.append(code.charAt(i + 1));
                        i += 2;
                    } else {
                        charBuilder.append(curr);
                        i++;
                    }
                }
                
                if (i < code.length() && code.charAt(i) == '\'') {
                    charBuilder.append(code.charAt(i));
                    foundClosing = true;
                    i++;
                }
                
                String lexeme = charBuilder.toString();
                if (!foundClosing) {
                    tokens.add(new Tokenizer("INVALID_LETTER", lexeme).toString());
                } else {
                    tokens.add(new Tokenizer("LETTER_LITERAL", lexeme).toString());
                }
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
            
            // Detect numbers (int and float)
            if (Character.isDigit(ch)) {
                StringBuilder numberBuilder = new StringBuilder();
                boolean isFloat = false;

                while (i < code.length()) {
                    char curr = code.charAt(i);
                    if (Character.isDigit(curr)) {
                        numberBuilder.append(curr);
                        i++;
                    } else if (curr == '.' && !isFloat && i + 1 < code.length() && Character.isDigit(code.charAt(i + 1))) {
                        isFloat = true;
                        numberBuilder.append(curr);
                        i++;
                    } else {
                        break;
                    }
                }

                String lexeme = numberBuilder.toString();
                String token = isFloat ? "DECIMAL_LITERAL" : "NUM_LITERAL";
                tokens.add(new Tokenizer(token, lexeme).toString());
                continue;
            }
            
            // Delimiters (brackets, separators)
            String delimiterChars = "()[]{},;:";
            if (delimiterChars.indexOf(ch) != -1) {
                
                String lexeme = String.valueOf(ch);
                
                String token = LookupTable.getTokenType(lexeme);

                tokens.add(new Tokenizer(token, lexeme).toString());
                
                i++;

                continue;
                
            }
            
            i++;
        }
    }
}