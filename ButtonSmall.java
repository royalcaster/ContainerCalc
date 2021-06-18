import java.awt.Font;
import javax.swing.BorderFactory;

public class ButtonSmall extends Button{
    public ButtonSmall(String title, String color_bg, String color_hover, String color_clicked){
        super(title,color_bg,color_hover,color_clicked);

        label_title.setFont(new Font("Arial", Font.PLAIN, 12));
        label_title.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
    }
}
