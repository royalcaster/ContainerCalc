import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ButtonUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args){

        //Fenster
        JFrame frame_main = new JFrame("ContainerCalc");
        frame_main.setSize(1280,720);
        frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame_main.getContentPane().setBackground(Color.decode("#1E1E1E"));

        //Logo einlesen
        try {
            BufferedImage img = ImageIO.read(new File("main_logo.png"));
            frame_main.setIconImage(img);
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        ImageIcon img_intro = new ImageIcon("main_brand.png");

        JLabel img_label = new JLabel();
        img_label.setIcon(img_intro);

        JPanel grid_panel = new JPanel();
        grid_panel.setLayout(new BoxLayout(grid_panel, BoxLayout.X_AXIS));
        grid_panel.setBackground(Color.decode("#1E1E1E"));

        JPanel intro_panel = new JPanel();
        intro_panel.setBackground(Color.decode("#1E1E1E"));
        intro_panel.setLayout(new BoxLayout(intro_panel, BoxLayout.Y_AXIS));

        JPanel intro_panel_2 = new JPanel();
        intro_panel_2.setBackground(Color.decode("#1E1E1E"));
        intro_panel_2.setLayout(new BoxLayout(intro_panel_2, BoxLayout.Y_AXIS));

        JLabel title_label = new JLabel("Arbeitstitel eingeben:");
        title_label.setFont(new Font("Arial", Font.PLAIN, 15));
        title_label.setForeground(Color.WHITE);
        title_label.setHorizontalAlignment(SwingConstants.RIGHT);

        JTextField workplace_title = new JTextField();
        workplace_title.setBackground(Color.decode("#282829"));
        workplace_title.setMaximumSize(new Dimension(400,60));
        workplace_title.setBorder(null);
        workplace_title.setForeground(Color.white);
        workplace_title.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        workplace_title.setCaretColor(Color.white);
        workplace_title.setFont(new Font("Arial", Font.PLAIN, 15));

        Button button_commit = new Button("Bestätigen", "#282829","#232324","#1E1E1F");
        button_commit.setMaximumSize(new Dimension(400,40));

        //Layout initialisieren und benennen
        button_commit.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Layout main_window = new Layout();
                frame_main.remove(grid_panel);
                frame_main.add(main_window);

                frame_main.setTitle(workplace_title.getText());

                SwingUtilities.updateComponentTreeUI(frame_main);
            }
        });

        intro_panel.add(img_label);
        intro_panel.add(Box.createRigidArea(new Dimension(0,20)));
        intro_panel.add(title_label);

        intro_panel.add(Box.createRigidArea(new Dimension(0,20)));
        intro_panel_2.add(workplace_title);
        intro_panel_2.add(Box.createRigidArea(new Dimension(0,20)));
        intro_panel_2.add(button_commit);

        grid_panel.add(Box.createRigidArea(new Dimension(150,0)));
        grid_panel.add(intro_panel);
        grid_panel.add(Box.createRigidArea(new Dimension(30,0)));
        grid_panel.add(intro_panel_2);

        frame_main.add(grid_panel);
        frame_main.setVisible(true);
    }
}

//BAUSTELLEN

//Filechoser hat altes windows Look and Feel
//wenn draggable removed wird, dann wieder als representer in die liste!


//NÄCHSTE SCHRITTE

//Icons auf Buttons


