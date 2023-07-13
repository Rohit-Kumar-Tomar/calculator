
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;



public class Calculator implements ActionListener {

    JFrame jFrame;

    JTextField jTextField;

    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[8];

    JButton addButton;
    JButton divButton;
    JButton dotButton;
    JButton subButton;
    JButton mulButton;
    JButton eqButton;
    JButton negButton;
    JButton clearButton;

    //logic
    double accumulator = 0.0;
    String selectOperation = ""; // +, - , *,/

    public Calculator() {
        //frame
        jFrame = new JFrame("Simple Calculator");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(460, 460);
        jFrame.setLayout(null);
        jFrame.setResizable(false);

        //main txt field
        Font textFieldFont = new Font("Cambria Math", Font.BOLD, 40);
        this.jTextField = new JTextField();
        jTextField.setBounds(40, 40, 370, 60);
        jTextField.setFont(textFieldFont);
        jTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        jTextField.setEnabled(false);
        LineBorder border = new LineBorder(new Color(157, 159, 170, 2));
        jTextField.setBorder(border);
        jFrame.add(jTextField);

        //buttons instants
        Font buttonsfont = new Font("Cambria Math", Font.PLAIN, 25);
        for (int i = 0; i < numButtons.length; i++) {
            String buttonName = String.valueOf(i);
            JButton newButton = new JButton(buttonName);
            newButton.setFont(buttonsfont);
            newButton.addActionListener(this);
            newButton.setFocusable(false);
            numButtons[i] = newButton;

        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");

        dotButton = new JButton(".");
        eqButton = new JButton("=");
        negButton = new JButton("+/-");
        clearButton = new JButton("C");

        funcButtons[0] = addButton;
        funcButtons[1] = subButton;
        funcButtons[2] = mulButton;
        funcButtons[3] = divButton;

        funcButtons[4] = dotButton;
        funcButtons[5] = eqButton;
        funcButtons[6] = negButton;
        funcButtons[7] = clearButton;

        for (int i = 0; i < funcButtons.length; i++) {
            funcButtons[i].setFont(buttonsfont);
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFocusable(false);
        }

        //Button placement
        JPanel jPanel = new JPanel();
        jPanel.setBounds(40, 120, 370, 210);
        jPanel.setLayout(new GridLayout(4, 4, 10, 10));

        jPanel.add(numButtons[7]);
        jPanel.add(numButtons[8]);
        jPanel.add(numButtons[9]);
        jPanel.add(divButton);
        jPanel.add(numButtons[4]);
        jPanel.add(numButtons[5]);
        jPanel.add(numButtons[6]);
        jPanel.add(mulButton);
        jPanel.add(numButtons[1]);
        jPanel.add(numButtons[2]);
        jPanel.add(numButtons[3]);
        jPanel.add(subButton);
        jPanel.add(numButtons[0]);
        jPanel.add(dotButton);
        jPanel.add(eqButton);
        jPanel.add(clearButton);

        //jPanel.setBackground(Color.black);
        jFrame.add(jPanel);

        eqButton.setBounds(40, 340, 275, 50);
        jFrame.add(eqButton);

        //interface
        jFrame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e){
        JButton source = (JButton) e.getSource();
        //numeric button
        for (int i = 0; i < numButtons.length; i++){
            if (source == numButtons[i]){
                String number = source.getText();

                jTextField.setText(jTextField.getText() + number);
            }
        }

        //dot button
        if(source == dotButton && !jTextField.getText().contains(".")){
            jTextField.setText(jTextField.getText()+".");
        }

        //4 operations
        if(source == subButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectOperation = "-";
            jTextField.setText("");
        }
        if(source == mulButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectOperation = "*";
            jTextField.setText("");
        }
        if(source == divButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectOperation = "/";
            jTextField.setText("");
        }
        if(source == addButton && !jTextField.getText().equals("")){
            accumulator = Double.parseDouble(jTextField.getText());
            selectOperation = "+";
            jTextField.setText("");
        }
        // -ve value button
        if (source == negButton && !jTextField.getText().equals("")){
            double newValue = -Double.parseDouble(jTextField.getText());
            String newText = String.valueOf(newValue);

            jTextField.setText(newText );

        }
        // clear button
        if (source == clearButton) {
            accumulator = 0.0;
            selectOperation = "";
            jTextField.setText("");
        }
        // equals button
        if (source == eqButton
        && !selectOperation.equals("")
                && !jTextField.getText().equals("")){
            double textFieldValue = Double.parseDouble(jTextField.getText());

            double newValue = 0.0;
            switch (selectOperation){
                case "+": newValue = accumulator + textFieldValue; break;
                case "-": newValue = accumulator - textFieldValue; break;
                case "*": newValue = accumulator * textFieldValue; break;
                case "/": newValue = accumulator / textFieldValue; break;
            }
            accumulator = 0.0;
            selectOperation = "";
            jTextField.setText(String.valueOf(newValue));
        }






    }
}

