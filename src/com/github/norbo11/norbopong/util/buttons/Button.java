package com.github.norbo11.norbopong.util.buttons;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import com.github.norbo11.game.Entity;
import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.MouseManager;

public abstract class Button extends Entity
{
    public Button(int x, int y, int width, int height, Color color, String text)
    {
        super((int) (x - width / 2), (int) (y - height / 2), width, height);
        
        this.text = text;
    }
    
    private Color color = Color.white;
    private String text;
    
    @Override
    public void handleLogic()
    {        
        if (MouseManager.isInside(this)) onHover();
        
        while (Mouse.next())
        {
            if (Mouse.getEventButtonState())
            {
                System.out.println(Mouse.getEventButton());

                switch (Mouse.getEventButton())
                {
                    case 0:
                        if (MouseManager.isInside(this))
                        {
                            onClick();
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void render()
    {
        GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
        GL11.glRectf(position.x, position.y, position.x + width, position.y + height);
        Game.getFont().drawString(position.x + (width / 2 - Game.getFont().getWidth(text) / 2), position.y + (height / 2 - Game.getFont().getHeight(text) / 2), text);
    }
    
    public abstract void onClick();
    public abstract void onHover();
}
