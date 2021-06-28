import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

import javax.swing.JTextField;

public class Input extends JTextField{
    public Input(){
        setFont(new Font("Arial", Font.PLAIN, 15));
        setBackground(Color.decode("#282829"));
        setForeground(Color.white);
        setBorder(BorderFactory.createCompoundBorder(
        getBorder(), 
        BorderFactory.createEmptyBorder(5, 7, 5, 5)));
        setCaretColor(Color.white);
    }

    public Boolean isEmpty(){
        if (getText().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getContent(){
        String buffer = getText();
        return buffer;
    }

    public int getNumber(){
        int buffer = Integer.parseInt(getText());
        return buffer;
    }
    
}