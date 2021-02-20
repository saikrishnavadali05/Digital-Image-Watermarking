import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.*;

BufferedImage image  = user_space(getImage(image_path(path,name,"png")));




to insert mvsr logo into the image:

// BufferedImage image = null;
//File file = new File();

BufferedImage logo = getImage("logo.jpg");
BufferedImage image = user_space(image_orig);



 private BufferedImage add_image(BufferedImage image,BufferedImage logo)
{
		byte image[]  = get_byte_data(image);
		byte logo[] = get_byte_data(logo);
		byte len[] = bit_conversion(logo.length);
try
{
		encode_logo(img,len,0);//positioning the logo on the output image
		encode_logo(img,logo,32768);////4096 bytes of size of logo : 8*8*8*8bytes*8bit = 512 bits
}
catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, 
"Target File cannot hold the image!", "Error",JOptionPane.ERROR_MESSAGE);
		}
		  return image;
		//return watermarked_image;
}


private BufferedImage user_space(BufferedImage image)
	{
		//create new_img with the attributes of image
		BufferedImage new_img  = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D    graphics = new_img.createGraphics();
		graphics.drawRenderedImage(image, null);
		graphics.dispose(); //release all allocated memory for this image
		return new_img;
	}


private byte[] get_byte_data(BufferedImage image)
	{
		WritableRaster raster   = image.getRaster();
		DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
		return buffer.getData();
	}


private byte[] bit_conversion(int i)
	{
		byte byte3 = (byte)((i & 0xFF000000) >>> 24); //0
		byte byte2 = (byte)((i & 0x00FF0000) >>> 16); //0
		byte byte1 = (byte)((i & 0x0000FF00) >>> 8 ); //0
		byte byte0 = (byte)((i & 0x000000FF)       );
		//{0,0,0,byte0} is equivalent, since all shifts >=8 will be 0
		return(new byte[]{byte3,byte2,byte1,byte0});
		}	
	}

private byte[] encode_logo(byte[] image, byte[] addition, int offset)
	{
		//check that the data + offset will fit in the image
		if(addition.length + offset > image.length)
		{
			throw new IllegalArgumentException("File not long enough!");
		}
		//loop through each addition byte
		for(int i=0; i<addition.length; ++i)
		{
			//loop through the 8 bits of each byte
			int add = addition[i];
			for(int bit=7; bit>=0; --bit, ++offset) //ensure the new offset value carries on through both loops
			{
				//assign an integer to b, shifted by bit spaces AND 1
				//a single bit of the current byte
				int b = (add >>> bit) & 1;
				//assign the bit by taking: [(previous byte value) AND 0xfe] OR bit to add
				//changes the last bit of the byte in the image to be the bit of addition
				image[offset] = (byte)((image[offset] & 0xFE) | b );
			}
		}
		return image;
	}


private byte[] decode_logo(byte[] image)
	{
		int length = 0;
		int offset  = 32;
		//loop through 32 bytes of data to determine text length
		for(int i=0; i<32; ++i) //i=24 will also work, as only the 4th byte contains real data
		{
			length = (length << 1) | (image[i] & 1);
		}
		
		byte[] result = new byte[length];
		
		//loop through each byte of text
		for(int b=0; b<result.length; ++b )
		{
			//loop through each bit within a byte of text
			for(int i=0; i<8; ++i, ++offset)
			{
				//assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
				result = (byte)((result << 1) | (image[offset] & 1));
			}
		}
		return result;
	}
}


