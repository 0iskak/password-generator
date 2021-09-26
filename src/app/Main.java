package app;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JFrame {
    private static final List<Character> symbol = new ArrayList<>();
    private static final List<Character> number = new ArrayList<>();
    private static final List<Character> lowercase = new ArrayList<>();
    private static final List<Character> uppercase = new ArrayList<>();
    private static final List<Character> similar = new ArrayList<>();
    private static final List<Character> ambiguous = new ArrayList<>();

    Password password;

    public static void main(String[] args) {
        FlatLightLaf.setup();

        characters();

        new Main();
    }

    public Main() {
        password = new Password();

        setTitle("Password Generator");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(password.getPanel());
        setVisible(true);

        for (int i = 8; i <= 16; i++)
            password.getLength().addItem(i);
        password.getGenerate().addActionListener(e -> generate());
    }

    private void generate() {
        List<Character> list = new ArrayList<>();

        if (password.getSymbols().isSelected())
            list.addAll(symbol);
        if (password.getNumbers().isSelected())
            list.addAll(number);
        if (password.getLowercase().isSelected())
            list.addAll(lowercase);
        if (password.getUppercase().isSelected())
            list.addAll(uppercase);

        if (password.getSimilar().isSelected())
            list.removeAll(similar);
        if (password.getAmbiguous().isSelected())
            list.removeAll(ambiguous);

        if (password.getLength().getSelectedItem() instanceof Integer i) {
            Random random = new Random();
            StringBuilder passwordString = new StringBuilder();
            int length = list.size();

            for (int integer = 0; integer < i; integer++) {
                passwordString.append(list.get(random.nextInt(length)));
            }

            password.getPassword().setText(passwordString.toString());
        }
    }

    private static void characters() {
        for (int i = 33; i <= 126; i++) {
            char c = (char) i;

            if (Character.isDigit(c))
                number.add(c);
            else if (Character.isLetter(c)) {
                if (Character.isLowerCase(c))
                    lowercase.add(c);
                else
                    uppercase.add(c);
            }
            else
                symbol.add(c);
        }
        similar.addAll(List.of('i', 'l', '1', 'L', 'o', '0', 'O'));
        for (char c : "{}[]()/\\'\"`~,;:.<>)".toCharArray())
            ambiguous.add(c);
    }
}
