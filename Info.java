import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Info extends JPanel{
    public Info(String title_text, int number_text){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(title_text);
        JLabel number = new JLabel(Integer.toString(number_text));

        add(title);
        add(number);
    }
}
