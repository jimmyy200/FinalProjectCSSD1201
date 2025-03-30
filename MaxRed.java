import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

public class MaxRed extends Converter{
    // convert method 
    // Main job is to read and write files
    // Also calls the method to change the RGB of the original image
    public void convert(String inputN, String outN){
        File inputFile = new File(inputN);
        try{
            BufferedImage originalImage = ImageIO.read(inputFile);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            redify(originalImage, width, height, newImage);
            File outputFile = new File(outN);
            ImageIO.write(newImage, "PNG", outputFile);
            
        } catch (IOException e){
            System.out.println("MaxRed IOException");
        }
    }

    // redify method
    // calls the outerloop method
    public void redify(BufferedImage img, int w, int h, BufferedImage newI){
        outerL(w,h,0,img,newI);
    }

    // outerloop method
    // calls the innerloop method
    private void outerL(int w, int h, int i, BufferedImage img, BufferedImage newI){
        if (i >= w){
            return;
        }
        innerL(h, i, 0, img, newI);
        outerL(w, h, i+1, img, newI);
    }

    // innerloop
    // sets the red to 255 but keeps everything else the same
    private void innerL(int h, int i, int j, BufferedImage img, BufferedImage newI){
        if (j >= h){
            return;
        }
        int rgbNum = img.getRGB(i, j);
        int green = greenFromRGB(rgbNum);
        int blue = blueFromRGB(rgbNum);
        int finRGB = (255 << 16) | (green << 8) | (blue << 0);
        newI.setRGB(i,j, finRGB);

        innerL(h,i,j+1, img, newI);
    }
}
