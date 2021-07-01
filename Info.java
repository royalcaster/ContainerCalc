import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Info extends JPanel{

JLabel number;

    public Info(String title_text, int number_text){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#1E1E1E"));

        JLabel title = new JLabel(title_text);
        title.setForeground(Color.decode("#A3A3A3"));
        title.setFont(new Font("Arial", Font.PLAIN,12));

        JLabel number = new JLabel(Integer.toString(number_text)+" kg");
        number.setForeground(Color.WHITE);
        number.setFont(new Font("Arial", Font.BOLD,35));

        add(title);
        add(number);
    }

    public void setValue(double n) {
        number.setText(Double.toString(n));
    }
}
