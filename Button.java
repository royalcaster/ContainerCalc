import java.awt.event.*;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Button extends JPanel{

    JLabel label_title;

    public Button(String title, String color_bg, String color_hover, String color_clicked){
        setBackground(Color.decode(color_bg));
        setLayout(new FlowLayout());

        label_title = new JLabel(title);
        label_title.setForeground(Color.white);
        label_title.setFont(new Font("Arial", Font.PLAIN, 15));
        label_title.setBorder(BorderFactory.createEmptyBorder(5,60,5,60));

        add(label_title);

        addMouseListener(new MouseAdapter(){
            public void mouseEntered(MouseEvent e){
                setBackground(Color.decode(color_hover));
            }
        });

        addMouseListener(new MouseAdapter(){
            public void mouseExited(MouseEvent e){
                setBackground(Color.decode(color_bg));
            }
        });

        addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                setBackground(Color.decode(color_clicked));
            }
        });

        addMouseListener(new MouseAdapter(){
            public void mouseReleased(MouseEvent e){
                setBackground(Color.decode(color_bg));
            }
        });

        setVisible(true);
    }
}
