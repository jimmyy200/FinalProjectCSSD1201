import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Rotate extends Converter {
    public void convert(String input, String output){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(input));
            int width = img.getWidth();
            int height = img.getHeight();
            BufferedImage newImg = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
            rotate(img, width, height, newImg);
            ImageIO.write(newImg, "PNG", new File(output));
        }   
        catch (IOException e){
            System.out.println("Rotate IOException");
        }
    }
    public void rotate(BufferedImage img, int width, int height, BufferedImage newImg){
        int rgb = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int x = 0; x < height; x++){ // height == final image width
            for (int y = 0; y < width; y++){ // width == final image height
                rgb = img.getRGB(y, x);
                red = redFromRGB(rgb);
                green = greenFromRGB(rgb);
                blue = blueFromRGB(rgb);
                int finalRGB = (red << 16 | green << 8 | blue << 0);
                newImg.setRGB(height - x - 1,y, finalRGB);
            }
        }
    }
}