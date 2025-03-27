import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

public class Grayscale extends PixelCraft{
    public void convert(String inputN, String outN){
        File inputFile = new File(inputN);
        try{
            BufferedImage originalImage = ImageIO.read(inputFile);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            gray(newImage, width, height);
            File outputFile = new File(outN);
            ImageIO.write(newImage, "PNG", outputFile);
            
        } catch (IOException e){
            System.out.println("Grayscale IOException");
        }
    }

    public void gray(BufferedImage img, int w, int h){
        for (int i = 0; i < w; i++){
            for (int j = 0; j < h; j++){
                int rgbNum = img.getRGB(i, j);
                int red = redFromRGB(rgbNum);
                int green = redFromRGB(rgbNum);
                int blue = blueFromRGB(rgbNum);
                int avg = (int)((red*0.299) + (green*0.587) + (blue*0.114));
                img.setRGB(i,j, avg);
            }
        }
    }

    public int redFromRGB(int rgbN){
        return rgbN >> 16;
    }

    public int greenFromRGB(int rgbN){
        return rgbN >> 8;
    }

    public int blueFromRGB(int rgbN){
        return rgbN;
    }

}