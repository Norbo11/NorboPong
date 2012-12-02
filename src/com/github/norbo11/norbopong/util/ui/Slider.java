package com.github.norbo11.norbopong.util.ui;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Vector2f;

import com.github.norbo11.norbopong.game.Entity;
import com.github.norbo11.norbopong.util.MouseHelper;
import com.github.norbo11.norbopong.util.interfaces.GameElement;
import com.github.norbo11.norbopong.util.interfaces.RGBColor;

public class Slider implements GameElement
{
    public Slider(Entity bar, Entity slider, RGBColor colorHover, float percentage)
    {        
        this.bar = bar;
        this.slider = slider;
        this.colorNormal = slider.color;
        this.colorHover = colorHover;
        
        slider.setMiddlePoint(new Vector2f(bar.position.x + percentage * bar.width, bar.position.y + bar.height / 2));
        
        setPercentage();
    }
    
    public Entity bar;
    public Entity slider;
    private boolean active;
    private double percentage;
    private RGBColor colorNormal;
    private RGBColor colorHover;

    public void setPercentage()
    {
        percentage = (slider.getMiddlePoint().x - bar.position.x) / bar.width;
    }
    
    public boolean isActive()
    {
        return active;
    }

    public double getPercentage()
    {
        return percentage;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    @Override
    public void handleLogic()
    {        
        if (active)
        {
            slider.position.x = MouseHelper.getMousePos().x - slider.width / 2;
            
            if (slider.getMiddlePoint().x > bar.position.x + bar.width) slider.position.x = bar.position.x + (bar.width - slider.width / 2);
            if (slider.getMiddlePoint().x < bar.position.x) slider.position.x = bar.position.x - slider.width / 2;

            setPercentage();
        }
        
        if (MouseHelper.isInside(slider) || active)
        {
            slider.color = colorHover;
        } else {
            slider.color = colorNormal;
        }
    }
    
    public void handleKeyboardInput()
    {
        
    }
    
    public void handleMouseInput()
    {
        if (Mouse.isButtonDown(0))
        {
            if (MouseHelper.isInside(bar))
            {
                active = true;
            }
        } else {
            active = false;
        }
    }

    @Override
    public void render()
    {        
        bar.render();
        slider.render();
    }
    
}
