package Form;

import javax.swing.*;
import java.awt.*;

public class ImageJPanel extends JPanel
{
    private ImageIcon ii;
    public ImageJPanel()
    {
        ii=new ImageIcon("image/backGround.bmp");
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(ii.getImage(),0,0,this);
    }
}