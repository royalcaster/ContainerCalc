import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Alert extends JFrame{
   public Alert (String alert_message, String button_message) {
        super("Warnung");
        setSize(new Dimension(600,250));
        setLocationRelativeTo(null);

        JPanel panel_content = new JPanel();
        panel_content.setLayout(new GridLayout(3,1));
        panel_content.setBackground(Color.decode("#1E1E1E"));
        panel_content.setBorder(BorderFactory.createEmptyBorder(20,20,0,20));

        JLabel label_alert = new JLabel(alert_message);
        label_alert.setFont(new Font("Arial", Font.PLAIN, 15));
        label_alert.setForeground(Color.white);
        label_alert.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        Button button_commit = new Button(button_message,"#282829","#232324","#1E1E1F");
        button_commit.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setVisible(false);
            }
        });
        button_commit.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        Filler fill1 = new Filler();
        Filler fill2 = new Filler();
        Filler fill3 = new Filler();
        Filler fill4 = new Filler();
        Filler fill5 = new Filler();

        //panel_content.add(fill1);
        panel_content.add(label_alert);
        //panel_content.add(fill2);
        panel_content.add(button_commit);
        //panel_content.add(fill3);

        add(panel_content);
        setVisible(true);
   } 
}
