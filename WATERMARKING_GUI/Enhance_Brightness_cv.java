import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class Enhance_Brightness_cv {
   static int width;
   static int height;
   static double alpha = 2;
   static double beta = 50;
   
   public static void main( String[] args ) {
   
      try{
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         Mat source =  Highgui.imread("digital_image_processing.jpg",Highgui.CV_LOAD_IMAGE_COLOR);
         Mat destination = new Mat(source.rows(),source.cols(),
         
         source.type());
         source.convertTo(destination, -1, alpha, beta);
         Highgui.imwrite("brightWithAlpha2Beta50.jpg", destination);
         
      }catch (Exception e) {
         System.out.println("error: " + e.getMessage());
      }
   }
}