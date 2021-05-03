import java.awt.Color;
import java.awt.event.*;
import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Button extends JPanel{
    public Button(String title, String color_bg, String color_hover, String color_clicked){
        setBackground(Color.decode(color_bg));
        setSize(60,20);
        setLayout(new FlowLayout());

        JLabel label_title = new JLabel(title);
        label_title.setForeground(Color.white);

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
