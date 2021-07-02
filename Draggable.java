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
    int package_weight;

    public Draggable(Package p){
        setBounds(30,30,p.getWidth(), p.getLength());
        setBackground(Color.decode("#007ACC"));
        setLayout(new GridLayout(1,1));
        
        package_weight = p.getWeight();

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

        public double getWeightInContainer(){

            double weight = 0;

            // IF statement muss überarbeitet werden!!! Wenn kleiner als (1000/6), kann es trotzdem auf einer Grenze liegen!

            //Wenn package nur in einer zone liegt, liefere das gewicht * faktor
            if (getWidth() <= 1000/6) {
                return package_weight * (getFactor(getNextBorder(getX()+getWidth()) / (1000/6)));
            }
            //wenn das package sich über mehrere zonen streckt
            else {

                //Beginn des Pakets bis erste Grenze
                weight = ((double) getNextBorder(getX()) - getX()) / (double) getWidth() * package_weight * getFactor(Math.ceil(1.0 * getX()/(1000/6)));
            

                if (getNextBorder(getX()) != getPrevBorder(getX()+getWidth())) {
                    //Schleife für den Fall, dass mehr als 2 Zonen beansprucht werden

                    for (int i = getNextBorder(getX()); i < getPrevBorder(getX()+getWidth()) - 5; i += 1000/6) {
                        weight += (1000.0/6.0) / getWidth() * package_weight * getFactor(Math.ceil(i / (1000/6))+1);
                        
                        //System.out.println("Faktor: " + (Math.ceil(i / (1000/6))+1));
                        //System.out.println("i: " + i);
                    }

                    //System.out.println(getNextBorder(getX()) + " | " + getPrevBorder(getX()+getWidth()));
                 }

                //Letzte Grenze bis Ende Packstück
                weight += (double) (getX()+getWidth() - getPrevBorder(getX()+getWidth())) / (double) getWidth() * package_weight * (getFactor(getNextBorder(getX()+getWidth()) / (1000/6)));

                 

                return  weight;    
            
            }
        }

        public void getWeightInBorders(int b){
            //Idee: gleicher ABlauf wie in getWeightInContainer() aber i kann nicht größer als 500 werden
        }
        
        public void rotate(){
            setSize(new Dimension(getHeight(),getWidth()));
            SwingUtilities.updateComponentTreeUI(panel_content);
        }

        public int getNextBorder(int n){
            return (int) (Math.ceil(1.0 * n / (1000/6)) * 1000/6);
        }

        public int getPrevBorder(int n) {
            return (int) (Math.floor(1.0 * n / (1000/6)) * 1000/6);
        }

        public int getFactor(double n) {
            switch((int) n) {
                case 1: return 3;
                case 2: return 2;
                case 3: return 1;
                case 4: return 1;
                case 5: return 2;
                case 6: return 3;
                default: return 0;
            }
        }
}
