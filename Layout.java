import java.awt.Color;
import java.awt.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

public class Layout {

    public Layout(){
        //Fenster
        JFrame frame_main = new JFrame("ContainerCalc");
        frame_main.setSize(1280,720);
        frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame_main.getContentPane().setBackground(Color.decode("#1E1E1E"));

        //Layered Pane
        JLayeredPane layered_pane = new JLayeredPane();
        
        frame_main.add(layered_pane);
        
        //erstes Content-Panel
        JPanel panel_content = new JPanel();
        panel_content.setBackground(Color.decode("#1E1E1E"));
        panel_content.setLayout(new BorderLayout(50,50));
        panel_content.setBounds(35,10,1850,1000);

        layered_pane.add(panel_content, Integer.valueOf(0));
        
        
        //Panel für Packstück-Liste
        JPanel panel_packagelist = new JPanel();
        panel_packagelist.setBackground(Color.decode("#282829"));
        panel_packagelist.setLayout(new FlowLayout());

        panel_content.add(panel_packagelist, BorderLayout.WEST);

        
        //Packstück hinzufügen Button -> Erben
        JButton button_add = new JButton("Erfassen");

        panel_packagelist.add(button_add);
        
        //Panel für Container-Objekt -> Erben
        JPanel panel_container = new JPanel();
        panel_container.setBackground(Color.decode("#333333"));
        
        panel_content.add(panel_container, BorderLayout.CENTER);

        //Panel für Informationen
        JPanel panel_info = new JPanel();
        panel_info.setBackground(Color.decode("#1E1E1E"));
        panel_info.setLayout(new BoxLayout(panel_info, BoxLayout.Y_AXIS));
        
        panel_content.add(panel_info, BorderLayout.PAGE_START);

        //Panel für Informationen INNEN
        JPanel panel_info_inner = new JPanel();
        panel_info_inner.setBackground(Color.green);
        panel_info_inner.setLayout(new BoxLayout(panel_info_inner, BoxLayout.X_AXIS));

        panel_info.add(Box.createRigidArea(new Dimension(0,100)));
        panel_info.add(panel_info_inner);
        panel_info.add(Box.createRigidArea(new Dimension(0,100)));

        //Panel für 3 Buttons
        JPanel panel_buttons = new JPanel();
        //panel_buttons.setBackground(Color.decode("#252526"));
        panel_buttons.setBackground(Color.green);
        panel_buttons.setLayout(new BoxLayout(panel_buttons, BoxLayout.Y_AXIS));

        //Knöpfe zum Testen
        JButton button1 = new JButton("Laden");
        JButton button2 = new JButton("Speichern");
        JButton button3 = new JButton("Exportieren");

        panel_buttons.add(button1);
        panel_buttons.add(button2);
        panel_buttons.add(button3);
        

        //Jlabel zum test
        JLabel test = new JLabel("Dies ist ein Test");
        test.setForeground(Color.white);

        //Jlabel zum test
        JLabel test2 = new JLabel("test");
         test2.setForeground(Color.white);

        //Jlabel zum test
        JLabel test3 = new JLabel("blablabla");
        test3.setForeground(Color.white);

        //Jlabel zum test
        JLabel test4 = new JLabel("sssssss");
        test4.setForeground(Color.white);

        panel_info_inner.add(Box.createRigidArea(new Dimension(200,0)));
        panel_info_inner.add(test);
        panel_info_inner.add(Box.createRigidArea(new Dimension(200,0)));
        panel_info_inner.add(test2);
        panel_info_inner.add(Box.createRigidArea(new Dimension(200,0)));
        panel_info_inner.add(test3);
        panel_info_inner.add(Box.createRigidArea(new Dimension(200,0)));
        panel_info_inner.add(test4);
        panel_info_inner.add(Box.createRigidArea(new Dimension(200,0)));
        panel_info_inner.add(panel_buttons);
        panel_info_inner.add(Box.createRigidArea(new Dimension(200,0)));

        //Komponenten sichtbar machen
        panel_info.setVisible(true);
        panel_container.setVisible(true);
        panel_packagelist.setVisible(true);
        panel_content.setVisible(true);
        layered_pane.setVisible(true);
        frame_main.setVisible(true);
    }
}