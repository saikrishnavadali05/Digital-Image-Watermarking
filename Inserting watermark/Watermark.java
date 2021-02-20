import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;


public class Watermark {
	
	// 1.inputting one image as the main image
	 
	 BufferedImage input_image;
	 int input_width;
	 int input_height;
	 
	 BufferedImage input_logo;
	 int logo_width;
	 int logo_height;
	 
	 public Watermark(){
	 try
	 {
		 File input = new File("Tulips.jpg"); //considering image as just a file
		 input_image = ImageIO.read(input); // accessing (reading) the file as an image using imageio
		 
		 File logo = new File("logo.jpg");
		 input_logo = ImageIO.read(logo);
		 
		 //input_image is the buffer of pixels of Tulips.jpg
		 //input_logo is the buffer of pixels of logo.jpg
		 
		 
		 input_width = input_image.getWidth(); // extracting width of image
		 input_height = input_image.getHeight(); // extracting height of image
		 
		 logo_width = input_logo.getWidth();//logo width
		 logo_height = input_logo.getHeight();//logo height
		 
		 for(int i=0 ; i<input_height ; i++) // loop for y axis
		 {
			 for(int j=0 ; j<input_width ; j++) // loop for x axis
			 {
				Color c = new Color(input_image.getRGB(j,i));
               int red = (int)(c.getRed() * 0.299);
               int green = (int)(c.getGreen() * 0.587);
               int blue = (int)(c.getBlue() *0.114);
               Color newColor = new Color(red+green+blue , red+green+blue , red+green+blue);
               
               input_image.setRGB(j,i,newColor.getRGB()); 
			 }
		  }
	 
	     for(int i=100 ; i<logo_height ; i++) // loop for y axis
		 {
			 for(int j=100 ; j<logo_width ; j++) // loop for x axis
			 {
				Color c = new Color(input_logo.getRGB(j,i));
               int red = (int)(c.getRed());
               int green = (int)(c.getGreen());
               int blue = (int)(c.getBlue());
               Color newColor = new Color(red,green,blue);
               
               input_logo.setRGB(j,i,newColor.getRGB()); 
			 }
		  
	      }
		
		
		catch(Exception e){}
		 File output = new File("logo_grayscale.jpg");
		 //ImageIO.write(input_image, "jpg", output);
		 ImageIO.write(input_logo,"jpg",output);
	 }	 
	 }

	  static public void main(String args[]) throws Exception 
   {
      Watermark obj = new Watermark();
   }
	 
	 
}
	 
	 
	 
	 
	 
	
	
	
	
	
	
	
	
	
}