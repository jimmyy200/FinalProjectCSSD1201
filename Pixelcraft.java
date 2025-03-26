import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class PixelCraft {
    /**
     * Append converter name to the input filename, before the file extension.
     * For example, if the input filename is "image.png" and the converter name is "GrayScale",
     * the output filename will be "image_GrayScale.png".
     */
    static String getOutputFilename(String inputFileName, String converterName) {
        int dotIndex = inputFileName.lastIndexOf(".");
        return inputFileName.substring(0, dotIndex) + "_" + converterName + inputFileName.substring(dotIndex);
    }
    public static void main(String[] args) {
        // Ensure that a converter name and a filename has been provided
        if (args.length < 2) {
            System.out.println("Usage: java -cp \"path/to/classes\" PixelCraft <ConverterName/GrayScale/Warmer/etc> <image_file.png>");
            System.exit(1);
        }
        String converterName = args[0];
        String inputFileName = args[1];
        String outputFileName = getOutputFilename(inputFileName, converterName);
        try {
            // Create an object of the class named 'converterName'
            // Read this link for more info about Java Reflection: https://www.oracle.com/technical-resources/articles/java/javareflection.html
            // Java reflection is NOT required knowledge for this course. 
            Class<?> clazz = Class.forName(converterName);
            Converter converter = (Converter) clazz.getDeclaredConstructor().newInstance();
            // The class named 'converterName' must be a subclass of 'Converter'
            // Call the convert method
            converter.convert(inputFileName, outputFileName);
       } catch  (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {

            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(); // Uncomment this line for debugging. Comment for production.
        }
    }
}

public class ARGB {

    public int alpha, red, green, blue;

    public ARGB(int pixel) {

        // extract different bits from pixel that stores the ARGB values
        this.alpha = (pixel >> 24) & 0xff;
        this.red = (pixel >> 16) & 0xff;
        this.green = (pixel >> 8) & 0xff;
        this.blue = pixel & 0xff;
    }

    public ARGB(int a, int r, int g, int b) {
        this.alpha = a;
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public int toInt() {
        // encode the ARGB values into a single integer
        return (this.alpha << 24) | (this.red << 16) | (this.green << 8) | blue;
    }
}