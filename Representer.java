import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Representer extends JPanel{
    public Representer(Package p){
        setLayout(new GridLayout(1,1));
        setBorder(BorderFactory.createLineBorder(Color.decode("#282829"), 3));
        setBackground(Color.decode("#282829"));

        //Panel für gesamten Content, damit BoxLayout anwendbar ist
        JPanel panel_content = new JPanel();
        panel_content.setLayout(new BoxLayout(panel_content, BoxLayout.X_AXIS));
        panel_content.setBackground(Color.decode("#313133"));

        //Grundlegende Struktur (Dreiteilung)
        JPanel panel_shorter = new JPanel();
        panel_shorter.setBackground(Color.green);
        panel_shorter.setLayout(new GridLayout(1,1));
        panel_shorter.setMaximumSize(new Dimension(25,60));
        panel_shorter.setBorder(BorderFactory.createEmptyBorder(0,6,0,10));
        panel_shorter.setBackground(Color.decode("#3E3E40"));

        JPanel panel_info = new JPanel();
        panel_info.setBackground(Color.blue);
        panel_info.setLayout(new GridLayout(2,1));
        panel_info.setBackground(Color.decode("#3E3E40"));
        panel_info.setBorder(BorderFactory.createEmptyBorder(0,0,0,4));
        //panel_info.setMaximumSize(new Dimension(100,45));

        // JPanel panel_buttons = new JPanel();
        // panel_buttons.setBackground(Color.yellow);
        // panel_buttons.setLayout(new GridLayout(2,1));
        // panel_buttons.setMaximumSize(new Dimension(55,60));
        // panel_buttons.setBackground(Color.decode("#3E3E40"));

        //Beschriftung
        JLabel shorter = new JLabel(p.getShorter());
        shorter.setFont(new Font("Arial", Font.BOLD, 19));
        shorter.setForeground(Color.white);
        panel_shorter.add(shorter);
        
        JLabel name = new JLabel(p.getName());
        name.setForeground(Color.white);
        name.setFont(new Font("Arial", Font.PLAIN, 15));
        panel_info.add(name);

        JLabel numbers = new JLabel(p.getWidth() + "x" + p.getLength() + "x" + p.getHeight());
        numbers.setForeground(Color.white);
        numbers.setFont(new Font("Arial", Font.PLAIN, 10));
        panel_info.add(numbers);

        //Buttons
        //ButtonSmall button_draw = new ButtonSmall("Zeichnen", "#007ACC", "#0070BA", "#0065A8");
        //ButtonSmall button_delete = new ButtonSmall("Entfernen","#DB4437","#CC4033","#BF3C30");

        // button_delete.addMouseListener(new MouseAdapter() {
        //     public void mousePressed(MouseEvent e){
        //         setVisible(false);
        //     }
        // });

        // panel_buttons.add(button_draw);
        // panel_buttons.add(button_delete);

        panel_content.add(panel_shorter);
        panel_content.add(panel_info);

        //panel_content.add(panel_buttons);
        add(panel_content);
    }

    public void select(){
        setBorder(BorderFactory.createLineBorder(Color.decode("#007ACC"),3));
    }

    public void deselect(){
        setBorder(BorderFactory.createLineBorder(Color.decode("#282829"),2));
    }
}
