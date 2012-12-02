package com.github.norbo11.norbopong.util.ui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.github.norbo11.norbopong.util.interfaces.GameElement;
import com.github.norbo11.norbopong.util.interfaces.GroupableByActivity;

import com.github.norbo11.norbopong.game.Entity;
import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.Color;
import com.github.norbo11.norbopong.util.MouseHelper;

public class TextBox extends Entity implements GroupableByActivity, GameElement
{
    public TextBox(int x, int y, int width, int height, int maxLenght, Color colorActive, Color colorInactive)
    {
        super(x, y, width, height);
        
        this.maxLenght = maxLenght;
        this.colorActive = colorActive;
        this.colorInactive = colorInactive;
        this.color = colorInactive;
    }
    
    public TextBox(int x, int y, int width, int height, int maxLenght, Color colorActive, Color colorInactive, String text)
    {
        this(x, y, width, height, maxLenght, colorActive, colorInactive);
        
        this.text = text;
    }
    
    ActiveGroup<TextBox> activeGroup;
    Color colorActive;
    Color colorInactive;
    String text = "";
    boolean active;
    int maxLenght;

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void handleKeyboardInput()
    {
        if (active)
        {
            if (Keyboard.getEventKeyState()) 
            {
                switch (Keyboard.getEventKey())
                {
                    case Keyboard.KEY_BACK:
                        if (text.length() > 0) 
                        {
                            text = text.substring(0, text.length() - 1);
                        }
                        break;
                    case Keyboard.KEY_RETURN: 
                    case Keyboard.KEY_LSHIFT: 
                    case Keyboard.KEY_RSHIFT:
                    case Keyboard.KEY_LEFT: 
                    case Keyboard.KEY_RIGHT: 
                    case Keyboard.KEY_UP: 
                    case Keyboard.KEY_DOWN:
                    case Keyboard.KEY_CAPITAL:
                    case Keyboard.KEY_DELETE:
                    case Keyboard.KEY_END:
                    case Keyboard.KEY_HOME:
                    case Keyboard.KEY_INSERT:
                        break;
                    default:
                        if (text.length() < maxLenght)
                        {
                            text +=  Keyboard.getEventCharacter();
                        }
                        break;
                }
            }
        }
    }
    
    public void handleMouseInput()
    {
        if (Mouse.getEventButtonState())
        {
            switch (Mouse.getEventButton())
            {
                case 0:
                    if (MouseHelper.isInside(this))
                    {
                        onClick();
                    }
                    break;
            }
        }
    }
    
    public void onClick()
    {
        setActive(true);
        activeGroup.setActiveItem(this);
    }
    
    public void setActive(boolean active)
    {
        this.active = active;
        if (active) color = colorActive;
        else color = colorInactive;
    }
    
    @Override
    public void handleLogic()
    {
    }

    @Override
    public void render()
    {
        /* Box */
        GL11.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
        GL11.glRectf(position.x, position.y + height - 3, position.x + width, position.y + height);
        
        /* Text */
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Game.fontButton.drawString(position.x + 5, position.y + 5, text);
        
        /* Cursor */
        if (active)
        {
            int x1 = (int) (position.x + Game.fontButton.getWidth(text) + 7);
            int y1 = (int) (position.y + 8);
            GL11.glRectf(x1, y1, x1 + 2, y1 + 19 - 1);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setGroup(ActiveGroup<?> group)
    {
        this.activeGroup = (ActiveGroup<TextBox>) group;
    }
}
