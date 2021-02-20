package dyimagefx.detect;
import dyimagefx.MyImage;
/**
 * File: Detect.java
 * 
 * Description:
 * This file contains edge/line detection methods.
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
public class Detect {
    
    //////////////////////////////// EDGE DETECT METHOD ////////////////////////
    
    /**
     * This method will detect edges in the image.
     * 
     * @param img The image to blur.
     */
    public static void edge(MyImage img){
        
        /**
         * Mask is a 2D square of odd size like 3x3
         * For simplicity storing it into 1D array.
         */
        int mask[] = new int[]{  -1, -1, -1,
                                 -1,  8, -1,
                                 -1, -1, -1};
        
        int maskSize = 3;   //The width of the mask.
        
        /**
         * Buffered array of pixels holds the intermediate value of pixels that
         * is multiplied with the mask to get the final value for the center
         * pixel under the mask.
         */
        int buff[];
        
        /** 
         * This array will store the output of the edge detect operation which will
         * be later written back to the original image pixels.
         */
        int outputPixels[] = new int[img.getImageTotalPixels()];
        
        //image dimension
        int width = img.getImageWidth();
        int height = img.getImageHeight();

        /** edge detect operation */
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                /** Fill buff array */
                int i = 0;
                buff = new int[9];
                for(int r = y - (maskSize / 2); r <= y + (maskSize / 2); r++){
                    for(int c = x - (maskSize / 2); c <= x + (maskSize / 2); c++){
                        if(r < 0 || r >= height || c < 0 || c >= width){
                            /** Some portion of the mask is outside the image. */
                            int tr = r, tc = c;
                            if(r < 0){
                                tr = r+1;
                            }else if(r == height){
                                tr = r-1;
                            }
                            if(c < 0){
                                tc = c+1;
                            }else if(c == width){
                                tc = c-1;
                            }
                            buff[i] = img.getPixel(tc, tr);
                        }else{
                            buff[i] = img.getPixel(c, r);
                        }
                        i++;
                    }
                }
                
                /** Multiply mask with buff array to get the final value. */
                int sa=0, sr=0, sg=0, sb=0;
                for(i = 0; i < 9; i++){
                    sa += (mask[i]*dyimagefx.PixMath.getAlpha(buff[i]))/16;
                    sr += (mask[i]*dyimagefx.PixMath.getRed(buff[i]))/16;
                    sg += (mask[i]*dyimagefx.PixMath.getGreen(buff[i]))/16;
                    sb += (mask[i]*dyimagefx.PixMath.getBlue(buff[i]))/16;
                }
                
                /** Save result in outputPixels array. */
                int p = dyimagefx.PixMath.getPixel(sa, sr, sg, sb);
                outputPixels[x+y*width] = p;
            }
        }
        /** Write the output pixels to the image pixels */
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                img.setPixelToValue(x, y, outputPixels[x+y*width]);
            }
        }
    }    
}//class ends here