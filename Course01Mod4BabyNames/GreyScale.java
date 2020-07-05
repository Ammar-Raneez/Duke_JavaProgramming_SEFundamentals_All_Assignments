
/**
 * Write a description of GreyScale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class GreyScale {
    public ImageResource makeGray(ImageResource inImage) {
        //takes in an imageresource, sets all the pixels rgb values to 
        //average of the initial total of all, resulting in gray
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        
        for(Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
            pixel.setRed(average); pixel.setBlue(average); pixel.setGreen(average);
        }
        
        return outImage;
    }
    
    public void test() {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void multipleGray() {
        DirectoryResource dr = new DirectoryResource();
        
        for(File f: dr.selectedFiles()) {
            ImageResource inFile = new ImageResource(f);
            String fileName = inFile.getFileName();
            String newFileName = "gray-"+fileName;
            ImageResource gray = makeGray(inFile);
            gray.setFileName(newFileName);
            gray.save();
        }
    }
}
