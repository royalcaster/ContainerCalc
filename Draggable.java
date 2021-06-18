import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class Draggable extends JPanel{

    public Draggable(Package p){
        setBounds(30,30,p.getWidth(), p.getLength());
        setBackground(Color.decode("#007ACC"));
        setLayout(new GridLayout(1,1));
        
        JPanel panel_content = new JPanel();
        panel_content.setLayout(new BoxLayout(panel_content, BoxLayout.X_AXIS));
        panel_content.setBackground(Color.decode("#007ACC"));

        JLabel shorter = new JLabel(p.getShorter());
        shorter.setFont(new Font("Aria", Font.BOLD, 20));
        //-> Allignment Vertikal und horizontal zentriert

        ButtonSmall cancel = new ButtonSmall("x", "#0065A8", "#005D9C", "#00568F");
        cancel.setMaximumSize(new Dimension(30,30));
        cancel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setVisible(false);
            }
        });

        panel_content.add(Box.createRigidArea(new Dimension(p.getWidth()/3,0)));
        panel_content.add(shorter);
        panel_content.add(Box.createRigidArea(new Dimension(15,0)));
        panel_content.add(cancel);
        add(panel_content);

        setVisible(true);
    }
    
}
