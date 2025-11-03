import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("../sample_operation.lexc"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert to a single string
        String code = content.toString();

        // Clear symbol table before lexing
        SymbolTable.clear();

        // Call the lexer
        Lexer.lex(code);
        System.out.println(code);

        for (String token : Lexer.tokens) {
            System.out.println(token);
        }

        // Write tokens and symbol table to output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../output.txt"))) {
            writer.write("=== SOURCE CODE ===\n");
            writer.write(code);
            writer.write("\n=== TOKENS ===\n");
            
            for (String token : Lexer.tokens) {
                writer.write(token);
                writer.write("\n");
            }
            
            writer.write("\n=== SYMBOL TABLE ===\n");
            writer.write("NAME, TYPE, LINE_NUMBER\n");
            for (SymbolTable.Symbol symbol : SymbolTable.symbols.values()) {
                writer.write(symbol.toString());
                writer.write("\n");
            }
            
            System.out.println("Successfully written to output.txt!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}