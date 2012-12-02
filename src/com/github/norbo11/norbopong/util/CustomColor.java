package com.github.norbo11.norbopong.util;

import com.github.norbo11.norbopong.util.interfaces.RGBColor;

public class CustomColor implements RGBColor
{
    public int red, green, blue;
    
    public CustomColor(int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public CustomColor(float red, float green, float blue)
    {
        this.red = (int) (red * 255);
        this.green = (int) (green * 255);
        this.blue = (int) (blue * 255);
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
