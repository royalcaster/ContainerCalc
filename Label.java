import java.awt.Color;
import java.awt.Font;
import java.awt.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Label extends JPanel{

    JTextField textfield;

    public Label(String text){
        //setBackground(Color.decode("#282829"));
        setBackground(Color.green);

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(1,2));
        content.setBackground(Color.decode("#282829"));

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setForeground(Color.white);

        textfield = new JTextField();

        JPanel filler = new JPanel();
        //filler.setBackground(Color.decode("#282829"));
        filler.setBackground(Color.blue);

        content.add(filler);
        content.add(label);
        content.add(textfield);
        content.add(filler);
        add(content);
    }

    public Boolean isEmpty(){
        if (textfield.getText().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public String getContent(){
        String buffer = textfield.getText();
        return buffer;
    }

    public int getNumber(){
        int buffer = Integer.parseInt(textfield.getText());
        return buffer;
    }
}
