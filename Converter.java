public abstract class Converter {
    // Abstract method means all children classes have to have this method
    public abstract void convert(String inputFileName, String outputFileName);

    // Easy access to get red value of a pixel
    public int redFromRGB(int rgbN){
        return (rgbN >> 16) & 0xff;
    }

    // Easy access to get green value of a pixel
    public int greenFromRGB(int rgbN){
        return (rgbN >> 8) & 0xff;
    }

    // Easy access to get blue value of a pixel
    public int blueFromRGB(int rgbN){
        return (rgbN >> 0) & 0xff;
    }
}
