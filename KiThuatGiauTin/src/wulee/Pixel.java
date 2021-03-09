/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wulee;

/**
 *
 * @author hpmdu
 */
public class Pixel {
    
    private int alphal;
    private int red;
    private int green;
    private int blue;

    public Pixel(int alphal, int red, int green, int blue) {
        this.alphal = alphal;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getAlphal() {
        return alphal;
    }

    public void setAlphal(int alphal) {
        this.alphal = alphal;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
    
    public void setValueInChannel(int channel, int value){
        if (channel == Image.CHANNEL_RED){
            setRed(value);
        }
        if (channel == Image.CHANNEL_GREEN){
            setGreen(value);
        }
        if (channel == Image.CHANNEL_BLUE){
            setBlue(value);
        }
    }

    @Override
    public String toString() {
        return "Pixel{" + "alphal=" + alphal + ", red=" + red + ", green=" + green + ", blue=" + blue + '}';
    }
    
    
}
