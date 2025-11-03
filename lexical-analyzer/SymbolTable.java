import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {
    public static class Symbol {
        public String name;
        public String type;
        public int lineNumber;

        public Symbol(String name, String type, int lineNumber) {
            this.name = name;
            this.type = type;
            this.lineNumber = lineNumber;
        }

        @Override
        public String toString() {
            return name + ", " + type + ", " + lineNumber;
        }
    }

    public static Map<String, Symbol> symbols = new LinkedHashMap<>();
    public static int currentLine = 1;

    public static void addSymbol(String name, String type, int line) {
        if (!symbols.containsKey(name)) {
            symbols.put(name, new Symbol(name, type, line));
        }
    }

    public static void addIdentifier(String name) {
        if (!symbols.containsKey(name)) {
            symbols.put(name, new Symbol(name, "IDENTIFIER", currentLine));
        }
    }

    public static void addKeyword(String keyword) {
        if (!symbols.containsKey(keyword)) {
            symbols.put(keyword, new Symbol(keyword, "KEYWORD", currentLine));
        }
    }

    public static void clear() {
        symbols.clear();
        currentLine = 1;
    }

    public static void updateLineNumber(String code) {
        currentLine = 1;
        for (char ch : code.toCharArray()) {
            if (ch == '\n') {
                currentLine++;
            }
        }
    }
}
