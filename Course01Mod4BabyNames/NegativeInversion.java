        
/**
 * Write a description of NegativeInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;


public class NegativeInversion {
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        
        for(Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setBlue(255 - inPixel.getBlue());
            pixel.setGreen(255 - inPixel.getGreen());
        }
        
        return outImage;
    }
    
    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        
        for(File f: dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            String fileName = inImage.getFileName();
            String newFileName = "inverted-"+fileName;
            ImageResource inverted = makeInversion(inImage);
            inverted.setFileName(newFileName);
            //inverted.save();
            inverted.draw();
        }
    }
}
