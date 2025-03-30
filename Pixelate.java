import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Pixelate extends Converter {
    // convert method 
    // Main job is to read and write files
    // Also calls the method to change the RGB of the original image
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

        // The code scans a 3x3 region and averages out the colour for the new image
        // If it can't fully scan a 3x3 region, then it will do a 3x2, or 2x3, or 2x2 and so on

        for (int x = 0; x < width; x += pixelSize){
            for (int y = 0; y < height; y += pixelSize){
                red = 0;
                green = 0;
                blue = 0;
                pixels = 0;
                for (int a = 0; a < pixelSize; a++){
                    for (int b = 0; b < pixelSize; b++){
                        if (x + a < width - 1 && y + b < height - 1){ // prevent out of bounds
                            rgb = img.getRGB(x + a, y + b); // gets rgb value from a pixel within the 3x3 region
                            red += redFromRGB(rgb);
                            green += greenFromRGB(rgb);
                            blue += blueFromRGB(rgb);
                            pixels++; // keeps track of how much pixels were counted within the 3x3 region
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
                for (int a = 0; a < pixelSize; a++){ // actually sets the rgb of the new image
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