import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

public class Grayscale extends Converter{
    public void convert(String inputN, String outN){
        File inputFile = new File(inputN);
        try{
            BufferedImage originalImage = ImageIO.read(inputFile);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            gray(originalImage, width, height, newImage);
            File outputFile = new File(outN);
            ImageIO.write(newImage, "PNG", outputFile);
            
        } catch (IOException e){
            System.out.println("Grayscale IOException");
        }
    }

    public void gray(BufferedImage img, int w, int h, BufferedImage newI){
        for (int i = 0; i < w; i++){
            for (int j = 0; j < h; j++){
                int rgbNum = img.getRGB(i, j);
                int red = redFromRGB(rgbNum);
                int green = redFromRGB(rgbNum);
                int blue = blueFromRGB(rgbNum);
                int avg = (int)((red*0.299) + (green*0.587) + (blue*0.114));
                int finRGB = (avg << 16) | (avg << 8) | (avg << 0);
                newI.setRGB(i,j, finRGB);
                System.out.println(avg);
            }
        }
    }

    public int redFromRGB(int rgbN){
        return (rgbN >> 16) & 0xff;
    }

    public int greenFromRGB(int rgbN){
        return (rgbN >> 8) & 0xff;
    }

    public int blueFromRGB(int rgbN){
        return (rgbN >> 0) & 0xff;
    }

}