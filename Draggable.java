import javax.swing.JPanel;
import java.awt.*;

public class Draggable extends JPanel{

    public Draggable(Package p){
        Dimension d = new Dimension(p.getWidth(),p.getHeight());
        setSize(d);
        setBackground(Color.BLACK);

        setVisible(true);
    }
    
}
