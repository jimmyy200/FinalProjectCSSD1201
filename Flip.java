import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Flip extends Converter {
    public void convert(String input, String output){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(input));
            int width = img.getWidth();
            int height = img.getHeight();
            BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            flip(img, width, height, newImg);
            ImageIO.write(newImg, "PNG", new File(output));
        }   
        catch (IOException e){
            System.out.println("Flip IOException");
        }
    }
    public void flip(BufferedImage img, int width, int height, BufferedImage newImg){
        for (int x = width; x >= 0; x--){
            flipIteration(img, x, height, newImg);
        }
        
    }
    public void flipIteration(BufferedImage img, int width, int height, BufferedImage newImg){
        int widthDiff = 0;
        int heightDiff = 0;
        int rgb = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int x = 0; x < height; x ++){
            if (width == 0){ // prevents out of bounds
            widthDiff = 0;
            }
            else {
                widthDiff = img.getWidth() - width;
            }
            if (x == 0){
                heightDiff = 0;
            }
            else {
                heightDiff = img.getHeight() - x;
            }
            rgb = img.getRGB(widthDiff, heightDiff);
            red = redFromRGB(rgb);
            green = greenFromRGB(rgb);
            blue = blueFromRGB(rgb);
            int finalRGB = (red << 16 | green << 8 | blue << 0);
            if ((width - 1) < 0){
                newImg.setRGB(width, heightDiff, finalRGB);
            }
            else {
                newImg.setRGB(width - 1, heightDiff, finalRGB);
            }
        }
    }
}