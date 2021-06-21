import java.awt.Color;

import javax.swing.JPanel;

import jdk.tools.jaotc.binformat.pecoff.JPECoffRelocObject;

public class Axis extends JPanel {
    public Axis(int x_position){
        setBounds(x_position, 0, 1, 400);
        setBackground(Color.decode("#BFBFBF"));
        
        
        setVisible(true);
    }
}