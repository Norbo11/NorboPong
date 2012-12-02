package com.github.norbo11.norbopong.util;

import com.github.norbo11.norbopong.util.interfaces.RGBColor;

public enum Color implements RGBColor
{
    RED(255, 0, 00), GREEN(0, 255, 0), BLUE(0, 0, 255), WHITE(255, 255, 255), SILVER(192, 192, 192), GRAY(128, 128, 128), BLACK(0, 0, 0), MAROON(128, 0, 0), YELLOW(255, 255, 0), OLIVE(128, 128, 0), LIME(0, 255, 0), AQUA(0, 255, 255), TEAL(0, 128, 128), NAVY(0, 0, 128), FUCHSIA(255, 0, 255), PURPLE(128, 0, 128), ORANGE(255, 165, 0);
    
    public int red, green, blue;
    
    private Color(int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed()
    {
        return red / 255f;
    }

    public float getGreen()
    {
        return green / 255f;
    }

    public float getBlue()
    {
        return blue / 255f;
    }

    public void setRed(int red)
    {
        this.red = red;
    }

    public void setGreen(int green)
    {
        this.green = green;
    }

    public void setBlue(int blue)
    {
        this.blue = blue;
    }

}
