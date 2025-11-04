import javax.swing.*;
import java.awt.*;
// import java.awt.event.*;
import java.io.*;

public class Main extends JFrame {

    private JTextArea membersArea;
    private JButton inputButton;

    public Main() {
        setTitle("Lexical Analyzer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Title label
        JLabel titleLabel = new JLabel("<html><b>Lexical Analyzer for LexC<br>Programming Language</b></html>");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));

        // Members area
        JPanel membersPanel = new JPanel(new BorderLayout());
        JLabel membersLabel = new JLabel("Members:");
        membersLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));


        membersArea = new JTextArea(
            "- BUSI, Gabvriel\n" +
            "- ESPIRITU, Anthea Lyn Czeisler P.\n" +
            "- GERODA, Neo Ervine Y.\n" +
            "- MAGAN, Kim Audrey B.\n" +
            "- MARTINEZ, Simoun\n" +
            "- PATACSIL, Harold M.\n" +
            "- SEDIGO, Janelo\n" +
            "- VILLETA, Felicity Faith L."
        );
        membersArea.setEditable(false);
        membersArea.setBackground(getBackground());
        membersArea.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

        membersPanel.add(membersLabel, BorderLayout.NORTH);
        membersPanel.add(membersArea, BorderLayout.CENTER);

        // Input file button
        inputButton = new JButton("Input file");
        inputButton.setFocusPainted(false);
        inputButton.addActionListener(e -> openFileChooser());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(inputButton);

        add(titleLabel, BorderLayout.NORTH);
        add(membersPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            // Check file extension
            if (!selectedFile.getName().endsWith(".lexc")) {
                JOptionPane.showMessageDialog(this, "Error: File must have a .lexc extension!", 
                                              "Invalid File", JOptionPane.ERROR_MESSAGE);
                return;
            }

            processFile(selectedFile);
        }
    }

    private void processFile(File file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + e.getMessage(),
                                          "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String code = content.toString();
        Lexer.lex(code); // Call your existing lexer

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("../tokens_output.txt"))) {
            writer.write("TOKEN, LEXEME\n");
            for (String token : Lexer.tokens) {
                writer.write(token + "\n");
            }
            JOptionPane.showMessageDialog(this, "Tokens successfully written to tokens_output.txt!",
                                          "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing file: " + e.getMessage(),
                                          "Write Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }
}
