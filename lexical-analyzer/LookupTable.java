import java.util.HashMap;

public class LookupTable {
    public static final HashMap<String, String> lookupTable = new HashMap<>();

    static {
        // Arithmetic Operators
        lookupTable.put("+", "ADD_OP");
        lookupTable.put("-", "SUBTRACT_OP");
        lookupTable.put("*", "MULTIPLY_OP");
        lookupTable.put("/", "DIVIDE_OP");
        lookupTable.put("%", "MODULO_OP");
        lookupTable.put("**", "EXPONENT_OP");
        lookupTable.put("!", "NOT_OP");

        // Relational Operators
        lookupTable.put("==", "EQUALITY_OP");
        lookupTable.put("!=", "INEQUALITY_OP");
        lookupTable.put(">", "GREATER_THAN_OP");
        lookupTable.put("<", "LESS_THAN_OP");
        lookupTable.put(">=", "GREATER_EQUAL_OP");
        lookupTable.put("<=", "LESS_EQUAL_OP");

        // Logical Operators / Keywords
        lookupTable.put("and", "AND_OP");
        lookupTable.put("or", "OR_OP");
        lookupTable.put("not", "NOT_OP");

        // Keyword
        lookupTable.put("if", "IF_STMT");
        lookupTable.put("elif", "ELIF_STMT");
        lookupTable.put("else", "ELSE_STMT");
        lookupTable.put("repeat", "WHILE_LOOP");
        lookupTable.put("repeatfor", "FOR_LOOP");
        lookupTable.put("stop", "BREAK_STMT");
        lookupTable.put("display", "DISPLAY_FUNC");
        lookupTable.put("number", "NUMBER_TYPE");
        lookupTable.put("decimal", "DECIMAL_TYPE");
        lookupTable.put("symbol", "SYMBOL_TYPE");
        lookupTable.put("truefalse", "BOOLEAN_TYPE");
        lookupTable.put("text", "STRING_TYPE");
        lookupTable.put("list", "LIST_TYPE");

        // noise words
        String[] noiseWords = {"then", "start", "end", "so"};
        for (String noiseWord: noiseWords) {
            lookupTable.put(noiseWord, "NOISE_WORD");
        }

        // reserved words
        lookupTable.put("nothing", "NOTHING_TYPE");

        // comments
        lookupTable.put("~~", "SINGLE_LINE_COMMENT");
        lookupTable.put("~", "MULTILINE_COMMENT");

    }

<<<<<<< Updated upstream
        public static String getTokenType(String symbol) {
            if (lookupTable.containsKey(symbol)) {
                return lookupTable.get(symbol);
            }

            // Valid identifier: starts with letter, may include digits or single underscores, up to 64 chars
            if (symbol.matches("^[A-Za-z](?:_?[A-Za-z0-9]){0,63}$")) {
                return "IDENTIFIER";
            }


            return "INVALID_TOKEN";
        }
=======
    public static String getTokenType(String symbol){
        String result = lookupTable.get(symbol);
        return result != null ? result : "INVALID";
    }
>>>>>>> Stashed changes
}
