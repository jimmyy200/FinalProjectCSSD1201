import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pixelate extends Converter {
    public void convert(String input, String output){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(input));
            int width = img.getWidth();
            int height = img.getHeight();
            BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            pixelate(img, width, height, newImg);
            ImageIO.write(newImg, "PNG", new File(output));
        }   
        catch (IOException e){
            System.out.println("Pixelate IOException");
        }
    }
    public void pixelate(BufferedImage img, int width, int height, BufferedImage newImg){
        int rgb = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int pixels = 0;
        int pixelSize = 3; // 3x3 pixel
        for (int x = 0; x < width; x += pixelSize){
            for (int y = 0; y < height; y += pixelSize){
                red = 0;
                green = 0;
                blue = 0;
                pixels = 0;
                for (int a = 0; a < pixelSize; a++){
                    for (int b = 0; b < pixelSize; b++){
                        if (x + a < width - 1 && y + b < height - 1){ // prevent out of bounds
                            rgb = img.getRGB(x + a, y + b);
                            red += redFromRGB(rgb);
                            green += greenFromRGB(rgb);
                            blue += blueFromRGB(rgb);
                            pixels++;
                        }
                    }
                }
                int finalRGB = (0|0|0);
                if (pixels > 0){
                    finalRGB = ((red / pixels) << 16 | (green / pixels) << 8 | (blue / pixels) << 0);
                }   
                else {
                    finalRGB = (red << 16 | green << 8 | blue << 0);
                }
                for (int a = 0; a < pixelSize; a++){
                    for (int b = 0; b < pixelSize; b++){
                        if (x + a < width - 1 && y + b < height - 1){
                            newImg.setRGB(x + a, y + b, finalRGB);
                        }
                    }
                }
            }
        }
    }
}