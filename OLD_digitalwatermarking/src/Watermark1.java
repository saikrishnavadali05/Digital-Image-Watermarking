import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Watermark1
{
public static void main(String[] args)
{
try
{
Font font;
File _file = new File("Tulips.jpg");
Image src = ImageIO.read(_file);
int width = src.getWidth(null);
int height = src.getHeight(null);
System.out.println("X = "+width+" and Y = "+height);
BufferedImage image = new BufferedImage(width, height
, BufferedImage.TYPE_INT_RGB);
Graphics g = image.createGraphics();
g.drawImage(src, 0, 0, width, height, null);
//    g.setBackground(Color.white);
g.setColor(Color.red);
g.setFont(new Font("Verdana", Font.BOLD, 20));

g.drawString("image watermarked",  5, height - (new
Font("Arial", Font.BOLD, 20)).getSize() / 2 - 5);
g.dispose();

FileOutputStream out = new FileOutputStream("Watermark2.jpg");
JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
encoder.encode(image);
out.close();
} catch (Exception ee)
{
System.out.println("Error Occurred : "+ee);
}
}
}