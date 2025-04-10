import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

public class Gridify extends Converter{
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
            gridify(originalImage, width, height, newImage);
            File outputFile = new File(outN);
            ImageIO.write(newImage, "PNG", outputFile);
            
        } catch (IOException e){
            System.out.println("Gridify IOException");
        }
    }

    // gridify method
    // nested for loop to draw lines on the thirds of the image
    public void gridify(BufferedImage img, int w, int h, BufferedImage newI){
        for (int i = 0; i < w; i++){
            for (int j = 0; j < h; j++){
                int rgbNum = img.getRGB(i, j);
                int red = redFromRGB(rgbNum);
                int green = greenFromRGB(rgbNum);
                int blue = blueFromRGB(rgbNum);
                int finRGB = (red << 16) | (green << 8) | (blue << 0);
                newI.setRGB(i,j, finRGB);
                if (i == w/3 || i == 2*w/3){
                    for (int x = 0; x < h; x++){
                        newI.setRGB(i,x,0);
                    }
                } 
                if (j == h/3 || j == 2*h/3){
                    for (int z = 0; z < w; z++){
                        newI.setRGB(z,j,0);
                    }
                } 
            }
        }
    }
}