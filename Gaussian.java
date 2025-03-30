import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class Gaussian extends Converter {
    public void convert(String input, String output){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(input));
            int width = img.getWidth();
            int height = img.getHeight();
            BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            gaussian(img, width, height, newImg);
            ImageIO.write(newImg, "PNG", new File(output));
        }   
        catch (IOException e){
            System.out.println("Pixelate IOException");
        }
    }
    public void gaussian(BufferedImage img, int width, int height, BufferedImage newImg){
        int rgb = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int finalRGB = 0;
        Random rand = new Random();
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                rgb = img.getRGB(x, y);
                red = redFromRGB(rgb);
                green = greenFromRGB(rgb);
                blue = blueFromRGB(rgb);
                int randomNum = rand.nextInt(3);
                if (randomNum == 2){ // will create gaussian noise
                    finalRGB = (rand.nextInt(255) << 16 | rand.nextInt(255) << 8 | rand.nextInt(255) << 0);
                }
                else {
                    finalRGB = (red << 16 | green << 8 | blue << 0);
                }
                newImg.setRGB(x, y, finalRGB);
            }
        }
        // int x = 0;
        // x += rand.nextInt(6);
        // int y = 0;
        // y += rand.nextInt(6);
        // while (x < width){
        //     while (y < height){
        //         int finalRGB = (rand.nextInt(255) >> 16 | rand.nextInt(255) >> 8 | rand.nextInt(255) >> 0);
        //         newImg.setRGB(x, y, finalRGB);
        //         y += rand.nextInt(1, 6);
        //     }
        //     x += rand.nextInt(1, 6);
        // }
    }
}