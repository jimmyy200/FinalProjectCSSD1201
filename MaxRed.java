import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

public class MaxRed extends Converter{
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
            System.out.println("Blur IOException");
        }
    }


    public void redify(BufferedImage img, int w, int h, BufferedImage newI){
        for (int i = 0; i < w; i++){
            for (int j = 0; j < h; j++){
                int rgbNum = img.getRGB(i, j);
                int green = redFromRGB(rgbNum);
                int blue = blueFromRGB(rgbNum);
                int finRGB = (255 << 16) | (green << 8) | (blue << 0);
                newI.setRGB(i,j, finRGB);
            }
        }
    }
}
