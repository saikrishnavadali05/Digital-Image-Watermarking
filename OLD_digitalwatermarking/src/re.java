import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class re extends hash{
   BufferedImage image;
   int width;
   int height;
   
   public re() {
      try {
         File input = new File("Sample.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();
         
         int count = 0;
         
         for(int i=0; i<height/50; i++){
         
            for(int j=0; j<width/70; j++){
            
               count++;
               Color c = new Color(image.getRGB(j, i));
               System.out.println(("S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue()));
               
            }
         }
         
      } catch (Exception e) {}
   }
   
   static public void main(String args[]) throws Exception 
   {
      re obj = new re();
      obj.display();
   }
}
class hash 
{
   public void display()
   {
   String str="Welcome";
   int key=str.hashCode();
   BufferedImage image;
   try{
   File input=new File("Sample.jpg");
   image = ImageIO.read(input);
   int width = image.getWidth();
   int height = image.getHeight();

         int count = 0;

         for(int i=0; i<height/50; i++){

            for(int j=0; j<width/70; j++){

               count++;
               Color c = new Color(image.getRGB(j, i));
               System.out.println((" Red: " +c.getRed()+ "  Green: " +c.getGreen() +" Blue: " +c.getBlue())+key);
               
 
              // System.out.println("Hidden data"+key);
             }                            
            } 
         }catch(Exception e) {}                            
   }
}
