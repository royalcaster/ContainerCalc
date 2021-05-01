import java.awt.Color;

import javax.swing.JPanel;

import org.graalvm.compiler.lir.CompositeValue.Component;

public class Package{

String p_shorter, p_name;
int p_width, p_length, p_height, p_weight; 


    public Package(String shorter, String name, int p_width, int p_length, int p_height, int p_weight){
        this.p_shorter = shorter;
        this.p_name = name;
        this.p_width = p_width;
        this.p_length = p_length;
        this.p_height = p_height;
        this.p_weight = p_weight;
    }

    public String getShorter(){
        return p_shorter;
    }

    public String getName(){
        return p_name;
    }

    public int getWidth(){
        return p_width;
    }

    public int getLength(){
        return p_length;
    }

    public int getHeight(){
        return p_height;
    }

    public int getWeight(){
        return p_weight;
    }

    public void drawPackage(JPanel panel){
        JPanel package_panel = new JPanel();
        package_panel.setBounds(20,20,getWidth(),getHeight());
        package_panel.setBackground(Color.blue);

        package_panel.setVisible(true);
        panel.add(package_panel);
    }
}
