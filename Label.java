import java.awt.Color;
import java.awt.Font;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Label extends JLabel{

    public Label(String text){
        setText(text);
        setFont(new Font("Arial", Font.PLAIN, 15));
        setForeground(Color.white);
        setHorizontalAlignment(SwingConstants.RIGHT);
        setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
    }
}
