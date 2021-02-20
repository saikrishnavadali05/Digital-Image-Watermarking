import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class WatermarkLogo {
 
      public static void main(String[] args) {
 
            File img = new File("C:/OrignalImage.jpg");
			ImageIcon icon = new ImageIcon(img.getPath());
			
			File logo = new File("D:/logo.jpg");
			ImageIcon logo_icon = new ImageIcon(logo.getPath());
			
			BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			 Graphics graphics = bufferedImage.getGraphics();
            graphics.drawImage(icon.getImage(), 0, 0, null);
			
			graphics.setFont(new Font("Arial", Font.BOLD, 30));
			String title = "MVSR ENGINEERING WATERMARKED IMAGE";
			
			graphics.drawString(title, 0, icon.getIconHeight() / 2);
            
			
			BufferedImage bufferedLogo = new BufferedImage(logo_icon.getIconWidth(),logo_icon.getIconHeight(),BufferedImage.TYPE_INT_RGB);
			Graphics logo_graphics = bufferedLogo.getGraphics();
			graphics.drawImage(logo_icon.getImage(),100,100,null);
			
			graphics.dispose();
			
			File newFile = new File("C:/output_WatermarkedImage.jpg");
            try {
                  ImageIO.write(bufferedImage, "jpg", newFile);
                 } 
			catch (IOException e) {
                  e.printStackTrace();
			}
			
			
			 System.out.println(newFile.getPath() + " WATERMARKED IMAGE created successfully!");
			 
			 
			 
	}