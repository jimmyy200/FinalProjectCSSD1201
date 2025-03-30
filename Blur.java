import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

public class Blur extends Converter{

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
            blur(originalImage, width, height, newImage);
            File outputFile = new File(outN);
            ImageIO.write(newImage, "PNG", outputFile);
            
        } catch (IOException e){
            System.out.println("Blur IOException");
        }
    }

    // Blur method
    // For loop to go through each pixel
    // Sets RGB to blur average calculated in the blurTotal method
    public void blur(BufferedImage img, int w, int h, BufferedImage newI){
        for (int i = 4; i < w-4; i++){
            for (int j = 4; j < h-4; j++){
                int rgb = blurTotal(i,j, img);
                newI.setRGB(i,j,rgb);
            }
        }
    }

    // blurTotal method
    // For loop to calculate the average of the six surrounding pixels
    // total is divided by 36 because 6x6 pixels are accounted for in the total
    public int blurTotal(int i, int j, BufferedImage originalI){
        int rgbTotal = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int x = i-1; x < i+5; x++){
            for (int y = j-1; y < j+5; y++){
                int rgbNum = originalI.getRGB(x,y);
                red += redFromRGB(rgbNum);
                green += greenFromRGB(rgbNum);
                blue += blueFromRGB(rgbNum);
            }
        }
        rgbTotal = (red/36 << 16) | (green/36 << 8) | (blue/36 << 0);
        return rgbTotal;
    }
}