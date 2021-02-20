package dyimagefx.math;
import dyimagefx.MyImage;

/**
 * File: Blend.java
 * 
 * Description:
 * To perform blending of image pixels.
 * 
 * @author Yusuf Shakeel
 * @version 1.0
 * Date: 25-04-2014 fri
 * 
 * www.github.com/yusufshakeel/Java-Image-Processing-Project
 * 
 * The MIT License (MIT)
 * Copyright (c) 2014 Yusuf Shakeel
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

public class Blend {
    
    /////////////////////////////// BINARY IMAGE ////////////////////////////////
    
    /**
     * This method will blend the pixels of the binary image.
     * It takes two equal size binary images and blend their pixels.
     * The result of blend is saved in resultImg which is also of the same size.
     * 
     * @param sourceImg1 The first image.
     * @param sourceImg2 The second image.
     * @param resultImg This will hold the resultant image after blend of image1 and image2.
     * @param blendRatio The blend ratio.
     */
    public static void binaryImage(MyImage sourceImg1, MyImage sourceImg2, MyImage resultImg, float blendRatio){
        /**
         * Note grayscale and binary image pixel blend is almost same.
         * So, calling grayscaleImage() method
         */
        grayscaleImage(sourceImg1, sourceImg2, resultImg, blendRatio);
    }
    
    /////////////////////////////// GRAYSCALE IMAGE ////////////////////////////
    
    /**
     * This method will blend the pixels of the grayscale image.
     * It takes two equal size grayscale images and blend their pixels.
     * The result of blend is saved in resultImg which is also of the same size.
     * 
     * @param sourceImg1 The first image.
     * @param sourceImg2 The second image.
     * @param resultImg This will hold the resultant image after blend of image1 and image2.
     * @param blendRatio The blend ratio.
     */
    public static void grayscaleImage(MyImage sourceImg1, MyImage sourceImg2, MyImage resultImg, float blendRatio){
        
        //image dimension - common for all the three images
        int width = sourceImg1.getImageWidth();
        int height = sourceImg1.getImageHeight();
        
        //variable
        int result;
        
        /**
         * blend pixels
         * Grayscale image will have same value for RGB so take any one component.
         */
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                result = (int)((blendRatio*sourceImg1.getBlue(x, y)) + ((1-blendRatio)*sourceImg2.getBlue(x, y)));
                if(result > 255){
                    result = 255;
                }else if(result < 0){
                    result = 0;
                }
                resultImg.setPixel(x, y, 255, result, result, result);
            }
        }
    }
    
    /////////////////////////////// COLOR IMAGE ////////////////////////////////
    
    /**
     * This method will blend the pixels of the color image.
     * It takes two equal size color images and blend their pixels.
     * The result of blend is saved in resultImg which is also of the same size.
     * 
     * @param sourceImg1 The first image.
     * @param sourceImg2 The second image.
     * @param resultImg This will hold the resultant image after blend of image1 and image2.
     * @param blendRatio The blend ratio.
     */
    public static void colorImage(MyImage sourceImg1, MyImage sourceImg2, MyImage resultImg, float blendRatio){
        
        //image dimension - common for all the three images
        int width = sourceImg1.getImageWidth();
        int height = sourceImg1.getImageHeight();
        
        //variable
        int rRED, rGREEN, rBLUE;
        
        /**
         * blend pixels
         */
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                //blend the RGB components of the two source image
                rRED = (int)((blendRatio*sourceImg1.getRed(x, y)) + ((1-blendRatio)*sourceImg2.getRed(x, y)));
                rGREEN = (int)((blendRatio*sourceImg1.getGreen(x, y)) + ((1-blendRatio)*sourceImg2.getGreen(x, y)));
                rBLUE = (int)((blendRatio*sourceImg1.getBlue(x, y)) + ((1-blendRatio)*sourceImg2.getBlue(x, y)));
                
                if(rRED>255){
                    rRED = 255;
                }else if(rRED<0){
                    rRED = 0;
                }
                
                if(rGREEN>255){
                    rGREEN = 255;
                }else if(rGREEN<0){
                    rGREEN = 0;
                }
                
                if(rBLUE>255){
                    rBLUE = 255;
                }else if(rBLUE<0){
                    rBLUE = 0;
                }
                
                //save result
                resultImg.setPixel(x, y, 255, rRED, rGREEN, rBLUE);
            }
        }
    }
}//class ends here
