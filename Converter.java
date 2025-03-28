public abstract class Converter {
    public abstract void convert(String inputFileName, String outputFileName);

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
