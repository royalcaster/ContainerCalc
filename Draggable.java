import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import jdk.tools.jlink.internal.SymLinkResourcePoolEntry;

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
    int package_width;
    int package_height;
    String package_name;
    int package_length;
    String package_shorter;

    public Draggable(Package p){
        setBounds(30,30,p.getWidth(), p.getLength());
        setBackground(Color.decode("#007ACC"));
        setLayout(new GridLayout(1,1));
        
        package_weight = p.getWeight();
        package_shorter = p.getShorter();
        package_name = p.getName();
        package_weight = p.getWeight();
        package_width = p.getWidth();
        package_height = p.getHeight();
        package_length = p.getLength();

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
    
    /* Plan f??r Einzelgewicht-Methode
        Rechteck, Koordinatenursprung oben links
        
        double Gewicht = 0

        X-Koordinate / (1000/6)

        0-1 3er Zone
        1-2 2er Zone
        2-3 1er Zone
        3-4 1er Zone
        4-5 2er Zone
        5-6 3er Zone

        int Package-Rest = l??nge des Packages

        while (Package-Rest > (1000/6))
            -> Fl??che bis zu erster Begr??nzung
                ( (package.getX()/(1000/6) -> auf n??chstgr????ere Ganzzahl Runden * 1000/6) - package.getX() ) * getLength = (Fl??che im JPanel)

                int faktor;

                switch ((package.getX()/(1000/6) -> auf n??chstgr????ere Ganzzahl Runden))
                case 1: Faktor 3
                case 2: Faktor 2
                case 3: Faktor 1
                case 4: Faktor 1
                case 5: Faktor 2
                case 6: Faktor 3

                Fl??che im JPanel * Faktor = Gewicht im einzelnen Abschnitt

                Gewicht = Gewicht + Gewicht im einzelnen Abschnitt

                Package-Rest = Package-rest - ( (package.getX()/(1000/6) -> auf n??chstgr????ere Ganzzahl Runden * 1000/6) - package.getX() )

                */

        public double getWeightInContainer(){

            double weight = 0;

            //Wenn package nur in einer zone liegt, liefere das gewicht * faktor
            if (isBetweenBorders()) {
                return package_weight * (getFactor(getNextBorder(getX()+getWidth()) / (1000/6)));
            }
            //wenn das package sich ??ber mehrere zonen streckt
            else {

                //Beginn des Pakets bis erste Grenze
                weight = ((double) getNextBorder(getX()) - getX()) / (double) getWidth() * package_weight * getFactor(Math.ceil(1.0 * getX()/(1000/6)));
            

                if (getNextBorder(getX()) != getPrevBorder(getX()+getWidth())) {
                    //Schleife f??r den Fall, dass mehr als 2 Zonen beansprucht werden

                    for (int i = getNextBorder(getX()); i < getPrevBorder(getX()+getWidth()) - 5; i += 1000/6) {
                        weight += (1000.0/6.0) / getWidth() * package_weight * getFactor(Math.ceil(i / (1000/6))+1);
                        
                        //System.out.println("Faktor: " + (Math.ceil(i / (1000/6))+1));
                        //System.out.println("i: " + i);
                    }

                    //System.out.println(getNextBorder(getX()) + " | " + getPrevBorder(getX()+getWidth()));
                 }

                //Letzte Grenze bis Ende Packst??ck
                weight += (double) (getX()+getWidth() - getPrevBorder(getX()+getWidth())) / (double) getWidth() * package_weight * (getFactor(getNextBorder(getX()+getWidth()) / (1000/6)));

                 

                return  weight;    
            
            }
        }

        public double getWeightInLeft(){
            //Idee: gleicher ABlauf wie in getWeightInContainer() aber i kann nicht gr????er als 500 werden
            /*Struktur:
            if (isOnlyInLeft()) {
                getWeightInContainer
            }
            else (!isOnlyInLeft()) {
                getWeightInContainer, aber i nicht gr????er 500 und wenn getX gr????er 500, gib 0 zur??ck
            }
            */ 

            if (isOnlyInLeft()) {
                return getWeightInContainer();
            }
            else {
                        if (getX()<499) {
                            double weight = 0;

                            //Beginn des Pakets bis erste Grenze
                            weight = ((double) getNextBorder(getX()) - getX()) / (double) getWidth() * package_weight * getFactor(Math.ceil(1.0 * getX()/(1000/6)));
                        

                            if (getNextBorder(getX()) != getPrevBorder(getX()+getWidth())) {
                                //Schleife f??r den Fall, dass mehr als 2 Zonen beansprucht werden

                                for (int i = getNextBorder(getX()); i < 490; i += 1000/6) {
                                    weight += (1000.0/6.0) / getWidth() * package_weight * getFactor(Math.ceil(i / (1000/6))+1);
                                    
                                    //System.out.println("Faktor: " + (Math.ceil(i / (1000/6))+1));
                                    //System.out.println("i: " + i);
                                }

                                //System.out.println(getNextBorder(getX()) + " | " + getPrevBorder(getX()+getWidth()));
                            }

                            //Letzte Grenze bis Ende Packst??ck
                            //weight += (double) (getX()+getWidth() - getPrevBorder(getX()+getWidth())) / (double) getWidth() * package_weight * (getFactor(getNextBorder(getX()+getWidth()) / (1000/6)));

                            

                            return  weight;   
                        }
                        else {return 0;}
            }
        }

        public double getWeightInRight(){
            //Idee: gleicher ABlauf wie in getWeightInContainer() aber i kann nicht gr????er als 500 werden
            /*Struktur:
            if (isOnlyInLeft()) {
                getWeightInContainer
            }
            else (!isOnlyInLeft()) {
                getWeightInContainer, aber i nicht gr????er 500 und wenn getX gr????er 500, gib 0 zur??ck
            }
            */ 

            if (isOnlyInRight()) {
                return getWeightInContainer();
            }
            else {
                        if (getX()+getWidth()>499) {
                            double weight = 0;

                            //Beginn des Pakets bis erste Grenze
                            //weight = ((double) getNextBorder(getX()) - getX()) / (double) getWidth() * package_weight * getFactor(Math.ceil(1.0 * getX()/(1000/6)));
                        

                            if (getNextBorder(getX()) != getPrevBorder(getX()+getWidth())) {
                                //Schleife f??r den Fall, dass mehr als 2 Zonen beansprucht werden

                                for (int i = 499; i < getPrevBorder(getX()+getWidth()) - 5; i += 1000/6) {
                                    weight += (1000.0/6.0) / getWidth() * package_weight * getFactor(Math.ceil(i / (1000/6))+1);
                                    
                                    //System.out.println("Faktor: " + (Math.ceil(i / (1000/6))+1));
                                    //System.out.println("i: " + i);
                                }

                                //System.out.println(getNextBorder(getX()) + " | " + getPrevBorder(getX()+getWidth()));
                            }

                            //Letzte Grenze bis Ende Packst??ck
                            weight += (double) (getX()+getWidth() - getPrevBorder(getX()+getWidth())) / (double) getWidth() * package_weight * (getFactor(getNextBorder(getX()+getWidth()) / (1000/6)));

                            

                            return  weight;   
                        }
                        else {return 0;}
            }
        }
        
        public boolean isOnlyInLeft() {
            if (getX()+getWidth() < 501) {return true;}
            else {return false;}
        }

        public boolean isOnlyInRight() {
            if (getX() > 500) {return true;}
            else {return false;}
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

        public String getName() {
            return package_name;
        }

        public String getShorter() {
            return package_shorter;
        }

        public int getWidth() {
            return package_width;
        }

        public int getLength() {
            return package_length;
        }

        public int getHeight() {
            return package_height;
        }

        public double getWeight() {
            return (double) package_weight;
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

        public boolean isBetweenBorders() {
            if ( Math.ceil(getX() / (1000/6) ) == Math.ceil( ( getX() + getWidth() ) / (1000/6) ) ) {
                return true;
            }
            else {
                return false;
            }
        }

        public void mark(){
            panel_content.setBackground(Color.decode("#DB4437"));
        }

        public void validate(){
            panel_content.setBackground(Color.decode("#007ACC"));
        }
}