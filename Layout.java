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
        panel_content.setLayout(new BorderLayout(20,20));
        panel_content.setBounds(35,10,1300,650);

        layered_pane.add(panel_content, Integer.valueOf(0));
        
        
        //Panel für Packstück-Liste
        JPanel panel_packagelist = new JPanel();
        panel_packagelist.setBackground(Color.decode("#282829"));
        panel_packagelist.setLayout(new FlowLayout());

        panel_content.add(panel_packagelist, BorderLayout.WEST);

        
        //Packstück hinzufügen Button -> Erben
        Button button_add = new Button("Erfassen", "#007ACC", "#0070BA", "#0065A8");

        panel_packagelist.add(button_add);
        
        //Panel für Container-Objekt -> Erben
        JPanel panel_container = new JPanel();
        panel_container.setBackground(Color.decode("#333333"));
        panel_container.setPreferredSize(new Dimension(200,200));
        panel_content.add(panel_container, BorderLayout.CENTER);

        //Panel für Informationen
        JPanel panel_info = new JPanel();
        panel_info.setBackground(Color.decode("#1E1E1E"));
        panel_info.setLayout(new BoxLayout(panel_info, BoxLayout.Y_AXIS));
        
        panel_content.add(panel_info, BorderLayout.PAGE_START);

        //Panel für Informationen INNEN
        JPanel panel_info_inner1 = new JPanel();
        panel_info_inner1.setBackground(Color.decode("#1E1E1E"));
        panel_info_inner1.setLayout(new BoxLayout(panel_info_inner1, BoxLayout.X_AXIS));

        JPanel panel_info_inner2 = new JPanel();
        panel_info_inner2.setBackground(Color.decode("#1E1E1E"));
        panel_info_inner2.setLayout(new BoxLayout(panel_info_inner2, BoxLayout.X_AXIS));

        panel_info.add(Box.createRigidArea(new Dimension(0,40)));
        panel_info.add(panel_info_inner1);
        panel_info.add(Box.createRigidArea(new Dimension(0,10)));
        panel_info.add(panel_info_inner2);
        panel_info.add(Box.createRigidArea(new Dimension(0,40)));

        //Panel für 3 Buttons
        JPanel panel_buttons = new JPanel();
        //panel_buttons.setBackground(Color.decode("#252526"));
        panel_buttons.setBackground(Color.decode("#1E1E1E"));
        panel_buttons.setLayout(new BoxLayout(panel_buttons, BoxLayout.Y_AXIS));

        //Knöpfe zum Testen
        Button button_load = new Button("Laden", "#007ACC", "#0070BA", "#0065A8");
        Button button_save = new Button("Speichern", "#007ACC", "#0070BA", "#0065A8");
        Button button_export = new Button("Exportieren","#DB4437","#CC4033","#BF3C30");

        panel_buttons.add(button_load);
        panel_buttons.add(Box.createRigidArea(new Dimension(0,10)));
        panel_buttons.add(button_save);
        panel_buttons.add(Box.createRigidArea(new Dimension(0,10)));
        panel_buttons.add(button_export);

        Info info_left = new Info("Linke Hälfte",1234);
        Info info_right = new Info("Rechte Hälfte",1234);
        Info info_dif = new Info("Differenz",1234);
        Info info_both = new Info("Gesamt",1234);

        panel_info_inner1.add(Box.createRigidArea(new Dimension(80,0)));
        panel_info_inner1.add(info_left);
        panel_info_inner1.add(Box.createRigidArea(new Dimension(200,0)));
        panel_info_inner1.add(info_right);
        panel_info_inner1.add(Box.createRigidArea(new Dimension(200,0)));
        panel_info_inner1.add(info_dif);
        panel_info_inner1.add(Box.createRigidArea(new Dimension(200,0)));
        panel_info_inner1.add(info_both);
        panel_info_inner1.add(Box.createRigidArea(new Dimension(200,0)));
        panel_info_inner1.add(panel_buttons);
        panel_info_inner1.add(Box.createRigidArea(new Dimension(200,0)));

        //Komponenten sichtbar machen
        panel_info.setVisible(true);
        panel_container.setVisible(true);
        panel_packagelist.setVisible(true);
        panel_content.setVisible(true);
        layered_pane.setVisible(true);
        frame_main.setVisible(true);
    }
}