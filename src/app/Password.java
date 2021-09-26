package app;

import lombok.Getter;

import javax.swing.*;

@Getter
public class Password {
    private JPanel panel;
    private JComboBox<Integer> length;
    private JCheckBox symbols;
    private JCheckBox numbers;
    private JCheckBox lowercase;
    private JCheckBox uppercase;
    private JCheckBox similar;
    private JCheckBox ambiguous;
    private JButton generate;
    private JTextField password;
}
