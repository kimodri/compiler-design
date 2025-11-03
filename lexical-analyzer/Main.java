import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("../sample_operation.stn"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert to a single string
        String code = content.toString();

        // Call the lexer
        Lexer.lex(code);
        System.out.println(code);

        for (String token : Lexer.tokens) {
            System.out.println(token);
        }
    }
}