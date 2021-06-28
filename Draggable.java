import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class Draggable extends JPanel{

    int mouse_x;
    int mouse_y;
    int package_x;
    JPanel panel_content;

    public Draggable(Package p){
        setBounds(30,30,p.getWidth(), p.getLength());
        setBackground(Color.decode("#007ACC"));
        setLayout(new GridLayout(1,1));
        
        

        panel_content = new JPanel();
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

        ButtonSmall rotate = new ButtonSmall("->", "#0065A8", "#005D9C", "#00568F");
        rotate.setMaximumSize(new Dimension(30,30));
        rotate.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                rotate();
            }
        });

        panel_content.add(Box.createRigidArea(new Dimension(p.getWidth()/4,0)));
        panel_content.add(shorter);
        panel_content.add(Box.createRigidArea(new Dimension(10,0)));
        panel_content.add(cancel);
        panel_content.add(Box.createRigidArea(new Dimension(5,0)));
        panel_content.add(rotate);
        add(panel_content);

        //Drag-Funktion:
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //PointerInfo a = MouseInfo.getPointerInfo();
                //Point b = a.getLocation();
                mouse_x = e.getX();
                mouse_y = e.getY();
            }
         });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                    setLocation(e.getX() - mouse_x + getLocation().x,e.getY() - mouse_y + getLocation().y);
            }
        });


        setBorder(BorderFactory.createLineBorder(Color.decode("#282829"), 2));
        

        setVisible(true);
    }
    
    /* Plan für Einzelgewicht-Methode
        Rechteck, Koordinatenursprung oben links
        
        double Gewicht = 0

        X-Koordinate / (1000/6)

        0-1 3er Zone
        1-2 2er Zone
        2-3 1er Zone
        3-4 1er Zone
        4-5 2er Zone
        5-6 3er Zone

        int Package-Rest = länge des Packages

        while (Package-Rest > (1000/6))
            -> Fläche bis zu erster Begränzung
                ( (package.getX()/(1000/6) -> auf nächstgrößere Ganzzahl Runden * 1000/6) - package.getX() ) * getLength = (Fläche im JPanel)

                int faktor;

                switch ((package.getX()/(1000/6) -> auf nächstgrößere Ganzzahl Runden))
                case 1: Faktor 3
                case 2: Faktor 2
                case 3: Faktor 1
                case 4: Faktor 1
                case 5: Faktor 2
                case 6: Faktor 3

                Fläche im JPanel * Faktor = Gewicht im einzelnen Abschnitt

                Gewicht = Gewicht + Gewicht im einzelnen Abschnitt

                Package-Rest = Package-rest - ( (package.getX()/(1000/6) -> auf nächstgrößere Ganzzahl Runden * 1000/6) - package.getX() )

    */

        public int getWeightInContainer(){

            //int gewicht = 0
            //Distanz Zwischen Beginn des Draggables und 1. grenze -> Anteil an Gesamtlänge:  Gesamtlänge / Distanz Beginn 1. Grenze
            //Gewicht = p.getWeight() * Anteil 



            for (int i = getX(); i <= getX()+getWidth(); i += (Math.ceil(1.0 * i/(1000/6))*1000/6) - i) {
                System.out.println(i);
            }

            return 0;            
            
        }
        
        public void rotate(){
            setSize(new Dimension(getHeight(),getWidth()));
            SwingUtilities.updateComponentTreeUI(panel_content);
        }
}
