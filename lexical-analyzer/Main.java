import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Main extends JFrame {

    private JTextArea textArea;
    private JTextArea tokenArea;
    private JButton openButton;
    private JFileChooser fileChooser;

    public Main() {
        super("Simple Lexer GUI");

        // Components
        textArea = new JTextArea(15, 40);
        tokenArea = new JTextArea(10, 40);
        openButton = new JButton("Open .lexc File");
        fileChooser = new JFileChooser();

        // Layout
        textArea.setEditable(false);
        tokenArea.setEditable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(openButton, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(tokenArea), BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Button action
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(Main.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    // 🔒 Check file extension
                    if (!file.getName().toLowerCase().endsWith(".lexc")) {
                        JOptionPane.showMessageDialog(Main.this,
                                "Invalid file type. Please select a .lexc file.",
                                "Invalid File",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    readAndLexFile(file);
                }
            }
        });

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void readAndLexFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Error reading file: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String code = content.toString();
        textArea.setText(code);

        // Call your lexer
        Lexer.lex(code);

        // Display tokens
        StringBuilder tokensText = new StringBuilder();
        for (String token : Lexer.tokens) {
            tokensText.append(token).append(System.lineSeparator());
        }
        tokenArea.setText(tokensText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
