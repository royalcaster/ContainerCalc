import java.awt.Color;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.security.spec.RSAPrivateCrtKeySpec;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.BorderUIResource;

//import org.graalvm.compiler.hotspot.nodes.DimensionsNode;

import jdk.swing.interop.SwingInterOpUtils;
//import jdk.tools.jlink.internal.ResourcePrevisitor;

public class Layout {

    Package pack;
    Representer representer;
    int selection_index;
    ArrayList<Package> packages;
    ArrayList<Representer> representers;
    ArrayList<Draggable> draggables; 
    JPanel panel_packagelist;

    public Layout(){

        //Arary für Packages:
        packages = new ArrayList<>();

        representers = new ArrayList<>();

        draggables = new ArrayList<>();

        //selectable index für package-liste auf 0 setzen
        selection_index = 0;

        //Fenster
        JFrame frame_main = new JFrame("ContainerCalc");
        frame_main.setSize(1280,720);
        frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame_main.getContentPane().setBackground(Color.decode("#1E1E1E"));

        //Layered Pane
        JLayeredPane layered_pane = new JLayeredPane();
        layered_pane.setLayout(new FlowLayout());
        
        frame_main.add(layered_pane);
        
        //erstes Content-Panel
        JPanel panel_content = new JPanel();
        panel_content.setBackground(Color.decode("#1E1E1E"));
        panel_content.setLayout(new BorderLayout(20,20));

        layered_pane.add(panel_content, Integer.valueOf(0));

        //Panel-Links
        JPanel panel_left = new JPanel();
        panel_left.setBackground(Color.decode("#282829"));
        panel_left.setLayout(new BoxLayout(panel_left, BoxLayout.Y_AXIS));
        
        //Panel für Packstück-Liste
        panel_packagelist = new JPanel();
        panel_packagelist.setBackground(Color.decode("#282829"));
        panel_packagelist.setLayout(new GridLayout(8,1));

        //Panel für Operations
        JPanel panel_operations = new JPanel();
        panel_operations.setBackground(Color.decode("#282829"));
        panel_operations.setMaximumSize(new Dimension(400,60));;
        //panel_operations.setLayout();


        panel_left.add(panel_packagelist);
        panel_left.add(panel_operations);
        panel_content.add(panel_left, BorderLayout.WEST);

        //Packstück hinzufügen Button -> Erben
        Button button_add = new Button("Erfassen", "#007ACC", "#0070BA", "#0065A8");
        panel_operations.add(button_add);
        //Fenster für Informationen über Panel bei Knopfdruck erstellen
        button_add.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                JFrame frame_input = new JFrame();
                frame_input.setSize(new Dimension(550,350));
                
                //SPRING-LAYOUT!!!
                SpringLayout layout = new SpringLayout();

                //Content-Panel
                JPanel input_panel = new JPanel();
                input_panel.setBackground(Color.decode("#282829"));
                input_panel.setLayout(new GridLayout(9,1));

                Label label_name = new Label("Name:");
                Label label_shorter = new Label("Abkürzung:");
                Label label_width = new Label("Breite:");
                Label label_length = new Label("Länge");
                Label label_height = new Label("Höhe:");
                Label label_weight = new Label("Gewicht");

                //layout.putConstraint(SpringLayout.WEST, label_name,5);
                //layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, contentPane);

                Button button_submit = new Button("Ok", "#007ACC", "#0070BA", "#0065A8");
                Button button_cancel = new Button("Abbrechen", "#4D4D4D", "#474747", "#424242");

                //Text wenn nicht alles ausgefüllt ist
                JLabel warning = new JLabel("Bitte alle Textfelder ausfüllen.");
                warning.setFont(new Font("Arial", Font.PLAIN, 15));
                //warning.setForeground(Color.decode("#282829"));
                warning.setForeground(Color.decode("#1E1E1E"));

                input_panel.add(label_name);
                input_panel.add(label_shorter);
                input_panel.add(label_width);
                input_panel.add(label_length);
                input_panel.add(label_height);
                input_panel.add(label_weight);
                input_panel.add(warning);
                input_panel.add(button_submit);
                input_panel.add(button_cancel);
                

                button_submit.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        //pack = new Package(label_shorter.getContent(),label_name.getContent(),Integer.parseInt(label_width.getContent()),Integer.parseInt(label_length.getContent()),Integer.parseInt(label_height.getContent()),Integer.parseInt(label_weight.getContent()));
                        
                        //checken, ob alle ausgefüllt sind
                        if( label_name.isEmpty() || 
                        label_shorter.isEmpty() ||
                        label_width.isEmpty() ||
                        label_length.isEmpty() ||
                        label_height.isEmpty() ||
                        label_weight.isEmpty()) {
                                warning.setForeground(Color.decode("#DB4437"));
                                SwingUtilities.updateComponentTreeUI(input_panel);
                            }
                        else {
                            pack = new Package(label_shorter.getContent(),label_name.getContent(),label_width.getNumber(),label_length.getNumber(),label_height.getNumber(),label_weight.getNumber());
                             //zu ArrayList aus Packages hinzufügen
                            packages.add(pack);

                            System.out.println("Packages:" + packages.indexOf(pack));

                            representers.clear(); //Liste der Representer leeren
                            panel_packagelist.removeAll();  //Panel für Packet-Liste leeren
                            
                            for (int i = 0; i<packages.size();i++) {
                                representer = new Representer(packages.get(i));

                                representers.add(representer);
                                
                                panel_packagelist.add(representer);
                            }

                            System.out.println("Representer:" + representers.indexOf(representer));

                            SwingUtilities.updateComponentTreeUI(panel_packagelist);

                            frame_input.setVisible(false);
                            
                        }    
                    }  
                });

                frame_input.add(input_panel);
                frame_input.setVisible(true);
            }
        });


        //Panel für Container-aussen -> Erben
        JPanel panel_container_outer = new JPanel();
        panel_container_outer.setBackground(Color.decode("#1E1E1E"));
        panel_container_outer.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel_content.add(panel_container_outer, BorderLayout.CENTER);

        //Panel für Container-innen -> Erben
        JPanel panel_container = new JPanel();
        panel_container.setBackground(Color.decode("#282829"));
        panel_container.setPreferredSize(new Dimension(1000,400));
        panel_container.setLayout(null);
        panel_container_outer.add(panel_container);


        ButtonSmall button_draw = new ButtonSmall("Zeichnen", "#007ACC", "#0070BA", "#0065A8");
        button_draw.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                if (!packages.isEmpty()) {  
                    Draggable drag = new Draggable(packages.get(packages.size()-1));
                    draggables.add(drag);
                    panel_container.add(drag);
                    packages.remove(packages.get(packages.size()-1));
                    representers.clear();
                    panel_packagelist.removeAll();

                    //Liste neu Laden
                    for (int i = 0; i<packages.size();i++) {
                        representer = new Representer(packages.get(i));

                        representers.add(representer);
                        
                        panel_packagelist.add(representer);
                    }

                    SwingUtilities.updateComponentTreeUI(panel_packagelist);
                    SwingUtilities.updateComponentTreeUI(panel_container);
            }
        }
        });


        //Entfernen-Knopf
        ButtonSmall button_delete = new ButtonSmall("Entfernen","#DB4437","#CC4033","#BF3C30");

        button_delete.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                //wenn Liste leer, nichts machen oder Fehlermeldung
                //wenn liste hat N elemente, auf klick n-tes element entfernen und liste neu laden

                if (!packages.isEmpty()) {  
                    packages.remove(packages.get(packages.size()-1));
                    representers.clear();
                    panel_packagelist.removeAll();

                    //Liste neu Laden
                    for (int i = 0; i<packages.size();i++) {
                        representer = new Representer(packages.get(i));

                        representers.add(representer);
                        
                        panel_packagelist.add(representer);
                    }

                    SwingUtilities.updateComponentTreeUI(panel_packagelist);
                }
            }
        });

        panel_operations.add(button_draw);
        panel_operations.add(button_delete);

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
        panel_info_inner1.add(Box.createRigidArea(new Dimension(100,0)));
        panel_info_inner1.add(info_right);
        panel_info_inner1.add(Box.createRigidArea(new Dimension(100,0)));
        panel_info_inner1.add(info_dif);
        panel_info_inner1.add(Box.createRigidArea(new Dimension(100,0)));
        panel_info_inner1.add(info_both);
        panel_info_inner1.add(Box.createRigidArea(new Dimension(100,0)));
        panel_info_inner1.add(panel_buttons);
        panel_info_inner1.add(Box.createRigidArea(new Dimension(100,0)));

        JPanel panel_footer = new JPanel();
        panel_footer.setBackground(Color.decode("#282829"));
        panel_content.add(panel_footer, BorderLayout.PAGE_END);

        //Komponenten sichtbar machen
        panel_info.setVisible(true);
        panel_container.setVisible(true);
        panel_packagelist.setVisible(true);
        panel_content.setVisible(true);
        layered_pane.setVisible(true);
        frame_main.setVisible(true);
    }

    //Konzept für Select-Funktion:
    // Bei Klick representer mit selection_index deselecten
    // dann selection_index wird zu index des geklickten representers
    // representer mit selection_index selecten

    //Beispiel: arraylist mit 5 packages (0-4) sel_index_neu = 0 und sel_index_alt = 0
    // 

    public void reloadPackagelist(){
    }

        public void setSelection(){

                    
                    // representers.get(selection_index).deselect();

                    // System.out.println(selection_index);
                    
                    // selection_index = representers.indexOf(representer);
                    // representers.get(selection_index);

                    // System.out.println(selection_index);
        }
}