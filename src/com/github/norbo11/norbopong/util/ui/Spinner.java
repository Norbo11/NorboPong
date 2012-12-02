package com.github.norbo11.norbopong.util.ui;

import java.math.BigDecimal;

import org.lwjgl.input.Mouse;

import com.github.norbo11.norbopong.game.Entity;
import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.DrawingHelper;
import com.github.norbo11.norbopong.util.MouseHelper;
import com.github.norbo11.norbopong.util.NumberHelper;
import com.github.norbo11.norbopong.util.interfaces.GameElement;
import com.github.norbo11.norbopong.util.interfaces.RGBColor;

public class Spinner implements GameElement
{
    public Spinner(int x, int y, int width, int height, RGBColor baseColor, RGBColor buttonColor, RGBColor colorHover, float value, float increment)
    {
        int buttonHeight = (int) (height * 0.25f);
        int baseHeight = (int) (height * 0.5f);
        
        up = new Entity(x, y, width, buttonHeight, buttonColor);
        base = new Entity(x, y + buttonHeight, width, baseHeight, baseColor);
        down = new Entity(x, y + buttonHeight + baseHeight, width, buttonHeight, buttonColor);
        
        this.value = value;
        this.increment = increment;
        this.colorHover = colorHover;
        this.colorNormal = buttonColor;
    }
    
    public float getValue()
    {
        return value;
    }

    public void setValue(float value)
    {
        this.value = value;
    }

    private Entity up;
    private Entity down;
    private Entity base;
    private float value;
    private float increment;
    private RGBColor colorHover;
    private RGBColor colorNormal;
    
    @Override
    public void render()
    {        
        up.render();
        base.render();
        down.render();
        
        DrawingHelper.drawCenteredText(Game.fontButton, up.position, up.width, up.height, "/\\");
        DrawingHelper.drawCenteredText(Game.fontButton, base.position, base.width, base.height, NumberHelper.round(value, 1, BigDecimal.ROUND_HALF_UP) + "");
        DrawingHelper.drawCenteredText(Game.fontButton, down.position, down.width, down.height, "\\/");
    }

    @Override
    public void handleLogic()
    {        
        if (MouseHelper.isInside(up))
        {
            up.color = colorHover;
        } else {
            up.color = colorNormal;
        }
        
        if (MouseHelper.isInside(down))
        {
            down.color = colorHover;
        } else {
            down.color = colorNormal;
        }
    }

    @Override
    public void handleKeyboardInput()
    {        
    }

    @Override
    public void handleMouseInput()
    {                
        if (Mouse.getEventButtonState())
        {
            if (MouseHelper.isInside(up))
            {
                value += increment;
            }
            
            if (MouseHelper.isInside(down))
            {
                value -= increment;
            }
        }
    }

}
