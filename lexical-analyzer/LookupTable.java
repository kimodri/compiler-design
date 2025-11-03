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
        lookupTable.put("^", "EXPONENT_OP");
        lookupTable.put("!", "NOT_OP");
        lookupTable.put("//", "FLOORDIV_OP");
        lookupTable.put("++", "INCREMENT_OP");

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

        // Assignment
        lookupTable.put("=", "ASSIGN_OP");

        // Keyword
        lookupTable.put("if", "IF_STMT");
        lookupTable.put("elif", "ELIF_STMT");
        lookupTable.put("else", "ELSE_STMT");
        lookupTable.put("repeat", "WHILE_LOOP");
        lookupTable.put("repeatfor", "FOR_LOOP");
        lookupTable.put("stop", "LOOP_BREAK_STMT");
        lookupTable.put("display", "DISPLAY_FUNC");
        lookupTable.put("number", "NUMBER_TYPE");
        lookupTable.put("decimal", "DECIMAL_TYPE");
        lookupTable.put("symbol", "SYMBOL_TYPE");
        lookupTable.put("truefalse", "BOOLEAN_TYPE");
        lookupTable.put("text", "STRING_TYPE");
        lookupTable.put("list", "LIST_TYPE");
        lookupTable.put("give", "RETURN_STATEMENT");
        lookupTable.put("true", "BOOL_LITERAL");
        lookupTable.put("false", "BOOL_LITERAL");
        lookupTable.put("nothing", "NULL_LITERAL");
        lookupTable.put("continue", "LOOP_SKIP_STMT");
        lookupTable.put("function", "FUNCTION_DECLR");
        lookupTable.put("give", "RETURN_STMT");
        lookupTable.put("static", "FUNC_MODFR");
        lookupTable.put("get", "GET_FUNC");
        lookupTable.put("include", "INCLUDE_RSRV");
        lookupTable.put("try", "TRY_RSRV");
        lookupTable.put("bundle", "BUNDLE_RSRV");
        lookupTable.put("void", "VOID_RSRV");
        lookupTable.put("in", "IN_RSRV");


        // Delimeters
        lookupTable.put(";", "STATEMENT_TERMINATOR");
        lookupTable.put(",", "LIST_SEPARATOR");
        lookupTable.put("=", "ASSIGNMENT_OP");
        lookupTable.put("(", "LEFT_PARENTHESIS");
        lookupTable.put(")", "RIGHT_PARENTHESIS");
        lookupTable.put("[", "LEFT_SQUARE_BRACKET");
        lookupTable.put("]", "RIGHT_SQUARE_BRACKET");
        lookupTable.put("{", "LEFT_CURLY_BRACE");
        lookupTable.put("}", "RIGHT_CURLY_BRACE");
        lookupTable.put("\"", "DOUBLE_QUOTE");
        lookupTable.put("'", "SINGLE_QUOTE");
        lookupTable.put(":", "KEY_VALUE_SEPARATOR");

        // noise words
        String[] noiseWords = {"then", "start", "end", "so"};
        for (String noiseWord: noiseWords) {
            lookupTable.put(noiseWord, "NOISE_WORD");
        }

        // comments
        lookupTable.put("~~", "SINGLE_LINE_COMMENT");
        lookupTable.put("~", "MULTILINE_COMMENT");
    }

    public static String getTokenType(String symbol){
        String result = lookupTable.get(symbol);
        return result;
    }

    // I really don't get this

    // public static String getTokenType(String symbol) {
    //     if (lookupTable.containsKey(symbol)) {
    //         return lookupTable.get(symbol);
    //     }

    //     // Valid identifier: starts with letter, may include digits or single underscores, up to 64 chars
    //     if (symbol.matches("^[A-Za-z](?:_?[A-Za-z0-9]){0,63}$")) {
    //         return "IDENTIFIER";
    //     }
    // }
}
