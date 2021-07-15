import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseMotionListener;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.synth.SynthSplitPaneUI;
import javax.swing.filechooser.*;
import javax.imageio.*;

//import jdk.internal.module.SystemModuleFinders;

//import org.graalvm.compiler.hotspot.nodes.DimensionsNode;

import jdk.swing.interop.SwingInterOpUtils;
//import jdk.tools.jlink.internal.ResourcePrevisitor;

public class Layout{

    Package pack;
    Draggable drag;
    Representer representer;
    double max_dif;
    File fileToSave;
    File fileToSave2;

    ArrayList<Package> packages;
    ArrayList<Representer> representers;
    ArrayList<Draggable> draggables;

    JPanel panel_packagelist;
    JPanel panel_container;
    JPanel panel_info;

    JFrame frame_main;

    Info info_left;
    Info info_right;
    Info info_dif;
    Info info_both;

    Double weight_both;
    Double weight_left;
    Double weight_right;
    Double weight_dif;

    JTable table;

    public Layout(){

        //Arary für Packages:
        packages = new ArrayList<>();

        representers = new ArrayList<>();

        draggables = new ArrayList<>();

        weight_both = 0.0;

        //Filechoser kurz anzeigen lassen, damit der Bug mit LookAndFeel von Win11 verschwindet

        //Fenster
        frame_main = new JFrame("ContainerCalc");
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
                JFrame frame_input = new JFrame("Packstück erfassen");
                frame_input.setSize(new Dimension(650,450));
                
                //Content-Panel
                JPanel input_panel = new JPanel();
                input_panel.setBackground(Color.decode("#282829"));
                input_panel.setLayout(new BoxLayout(input_panel, BoxLayout.Y_AXIS));
                input_panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                JPanel top_panel = new JPanel();
                top_panel.setLayout(new GridLayout(6,4,5,5));
                top_panel.setBackground(Color.decode("#282829"));

                JPanel bottom_panel = new JPanel();
                bottom_panel.setLayout(new GridLayout(2,2,5,5));
                bottom_panel.setBackground(Color.decode("#282829"));

                Label label_name = new Label("Name:");
                Label label_shorter = new Label("Abkürzung:");
                Label label_width = new Label("Breite:");
                Label label_length = new Label("Länge:");
                Label label_height = new Label("Höhe:");
                Label label_weight = new Label("Gewicht:");

                Input input_name = new Input();
                Input input_shorter = new Input();
                Input input_width = new Input();
                Input input_length = new Input();
                Input input_height = new Input();
                Input input_weight = new Input();

                //layout.putConstraint(SpringLayout.WEST, label_name,5);
                //layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, contentPane);

                Button button_submit = new Button("Ok", "#007ACC", "#0070BA", "#0065A8");
                Button button_cancel = new Button("Abbrechen","#DB4437","#CC4033","#BF3C30");

                //Text wenn nicht alles ausgefüllt ist
                JLabel warning = new JLabel("Bitte alle Maße angeben.");
                warning.setFont(new Font("Arial", Font.PLAIN, 15));
                //warning.setForeground(Color.decode("#282829"));
                warning.setForeground(Color.decode("#282829"));

                top_panel.add(new Filler());
                top_panel.add(label_name);
                top_panel.add(input_name);
                top_panel.add(new Filler());
                top_panel.add(new Filler());
                top_panel.add(label_shorter);
                top_panel.add(input_shorter);
                top_panel.add(new Filler());
                top_panel.add(new Filler());
                top_panel.add(label_width);
                top_panel.add(input_width);
                top_panel.add(new Filler());
                top_panel.add(new Filler());
                top_panel.add(label_length);
                top_panel.add(input_length);
                top_panel.add(new Filler());
                top_panel.add(new Filler());
                top_panel.add(label_height);
                top_panel.add(input_height);
                top_panel.add(new Filler());
                top_panel.add(new Filler());
                top_panel.add(label_weight);
                top_panel.add(input_weight);
                top_panel.add(new Filler());

                bottom_panel.add(button_cancel);
                bottom_panel.add(button_submit);
                bottom_panel.add(warning);

                input_panel.add(top_panel);
                input_panel.add(Box.createRigidArea(new Dimension(0,30)));
                input_panel.add(bottom_panel);

                button_submit.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
                        //pack = new Package(label_shorter.getContent(),label_name.getContent(),Integer.parseInt(label_width.getContent()),Integer.parseInt(label_length.getContent()),Integer.parseInt(label_height.getContent()),Integer.parseInt(label_weight.getContent()));
                        
                        //checken, ob alle ausgefüllt sind
                        if( input_name.isEmpty() || 
                        input_shorter.isEmpty() ||
                        input_width.isEmpty() ||
                        input_length.isEmpty() ||
                        input_height.isEmpty() ||
                        input_weight.isEmpty()) {
                                warning.setForeground(Color.decode("#DB4437"));
                                SwingUtilities.updateComponentTreeUI(input_panel);
                            }
                        else {
                            pack = new Package(input_shorter.getContent(),input_name.getContent(),input_width.getNumber(),input_length.getNumber(),input_height.getNumber(),input_weight.getNumber());
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

                button_cancel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        frame_input.setVisible(false);
                    }
                });

                frame_input.add(input_panel);
                frame_input.setLocationRelativeTo(null);
                frame_input.setVisible(true);
            }
        });


        //Panel für Container-aussen -> Erben
        JPanel panel_container_outer = new JPanel();
        panel_container_outer.setBackground(Color.decode("#1E1E1E"));
        panel_container_outer.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel_content.add(panel_container_outer, BorderLayout.CENTER);
        

        //Panel für Container-innen -> Erben
        panel_container = new JPanel();
        panel_container.setBackground(Color.decode("#282829"));
        panel_container.setPreferredSize(new Dimension(1000,400));
        panel_container.setLayout(null);
        panel_container.setBorder(BorderFactory.createLineBorder(Color.decode("#BFBFBF"), 1));
        panel_container_outer.add(panel_container);

        //Achsen für Einteilung
        Axis axis_1 = new Axis(167);
        Axis axis_2 = new Axis(333);
        Axis axis_3 = new Axis(666);
        Axis axis_4 = new Axis(833);
        Axis axis_5 = new Axis(500);

        panel_container.add(axis_1);
        panel_container.add(axis_2);
        panel_container.add(axis_3);
        panel_container.add(axis_4);
        panel_container.add(axis_5);

        ButtonSmall button_draw = new ButtonSmall("Zeichnen", "#007ACC", "#0070BA", "#0065A8");
        button_draw.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                if (!packages.isEmpty()) {  
                    drag = new Draggable(packages.get(packages.size()-1));
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
        panel_info = new JPanel();
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

        button_export.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                try {
                    export();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        panel_buttons.add(button_load);
        panel_buttons.add(Box.createRigidArea(new Dimension(0,10)));
        panel_buttons.add(button_save);
        panel_buttons.add(Box.createRigidArea(new Dimension(0,10)));
        panel_buttons.add(button_export);

        info_left = new Info("Linke Hälfte");
        info_right = new Info("Rechte Hälfte");
        info_dif = new Info("Differenz");
        info_both = new Info("Gesamt");

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
        panel_footer.setBackground(Color.decode("#1E1E1E"));
        panel_footer.setLayout(new FlowLayout(SwingConstants.RIGHT));
        panel_content.add(panel_footer, BorderLayout.PAGE_END);

        Button button_calc = new Button("Berechnen", "#0F9D58", "#0F9152" , "#0D854B");
        panel_footer.add(button_calc);
        button_calc.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                calculate();
            }
        });

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
    
    public void updateInfoPanel(){

        info_both.setValue(weight_both);
        SwingUtilities.updateComponentTreeUI(panel_info);

    }

    public void calculate() {
                weight_both = 0.0;
                weight_left = 0.0;
                weight_right = 0.0;
                weight_dif = 0.0;

                for (int i = 0; i < draggables.size(); i++) {
                    //String s = Double.toString(draggables.get(i).getWeightInContainer());
                    //System.out.println(s);

                    weight_both += draggables.get(i).getWeight();
                    weight_left += draggables.get(i).getWeightInLeft();
                    weight_right += draggables.get(i).getWeightInRight();
                    weight_dif = Math.abs(weight_left - weight_right);

                    //updateInfoPanel();
                    //Einzelne Gewichte irgendwo anzeigen lassen! (keine ahnung wo und wie...)
                }
                    
                    info_both.setValue(weight_both);
                    info_left.setValue(weight_left);
                    info_right.setValue(weight_right);
                    info_dif.setValue(weight_dif);

                    if (weight_left > weight_right) {
                        max_dif = weight_right / 10;
                    }
                    else {max_dif = weight_left / 10;}

                    if (weight_dif < max_dif) {
                        info_dif.setGreen();
                        
                    }
                    else {info_dif.setRed();}
                    
                    System.out.println(bordersClean());

                    SwingUtilities.updateComponentTreeUI(panel_info);
    }

    public boolean bordersClean() {
        /*prüfen:
        x und y größer 0 (keine negativen werte)
        x + breite kleiner 1000 und y + höhe kleiner 400
        wenn beide erfüllt return true
        */
        for (int i = 0; i < draggables.size(); i++) {
            if (!draggableInBorders(draggables.get(i))) {
                draggables.get(i).mark();
                SwingUtilities.updateComponentTreeUI(panel_container);
                return false;
         
            }
        }

        for (int i = 0; i < draggables.size(); i++) {draggables.get(i).validate();}
        return true;
    }
    
    public boolean draggableInBorders(Draggable d) {
        if ((d.getX() < 1)
            || (d.getY() < 1)
            || (d.getX()+d.getWidth() > 1000)
            || (d.getY()+d.getHeight() > 400)
            ) {
                return false;
        }
        else {
            return true;
        }
    }

    public static BufferedImage printContainer(JPanel j) throws AWTException {     
        Point p = j.getLocationOnScreen();
        Dimension dim = j.getSize();
        Rectangle rect = new Rectangle(p, dim);

        Robot robot = new Robot();  
        return robot.createScreenCapture(rect);
    }

    public void export() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        //Filechoser für PDF des Containers
        // parent component of the dialog
        JFrame parentFrame = new JFrame();
        
        JFileChooser fileChooser = new JFileChooser();

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        fileChooser.setDialogTitle("Speicherort auswählen");   
 
        int userSelection = fileChooser.showSaveDialog(frame_main);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToSave = new File(fileChooser.getSelectedFile() + ".jpg");
            fileToSave2 = new File(fileChooser.getSelectedFile() + "_Daten.pdf");

        }

        try {
            ImageIO.write(printContainer(panel_container), "jpg", fileToSave);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public JTable wrapPackagelist() {
        String[][] data = null; 
        for (int i = 0; i < packages.size(); i++) {
            data[i][0] = packages.get(i).getShorter();
            data[i][1] = packages.get(i).getName();
            data[i][2] = Integer.toString(packages.get(i).getWidth());
            data[i][3] = Integer.toString(packages.get(i).getLength());
            data[i][4] = Integer.toString(packages.get(i).getHeight());
            data[i][5] = Integer.toString(packages.get(i).getWeight());
        }

        String[] column = {"Abkürzung", "Name", "Breite", "Länge", "Höhe", "Gewicht"};

        table = new JTable(data, column);
        return table;
    }

/*
    public double getWeightInLeft(){
        double weight = 0.0;
        
        for (int i = 0; i < draggables.size(); i++) {
            //Wenn Draggable in Linker hälfte ohne überschneidung
            if (draggables.get(i).getX()+draggables.get(i).getWidth() < 501) {
                weight += draggables.get(i).getWeightInContainer();
            }
            else if (draggables.get(i).getX() < 501 && draggables.get(i).getX()+draggables.get(i).getWidth() > 500) {
                weight += draggables.get(i).getWeightInBorders(501);
            }
        }

        return weight;

    }
*/

}