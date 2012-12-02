package com.github.norbo11.norbopong.game;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Vector2f;

import com.github.norbo11.norbopong.util.Color;
import com.github.norbo11.norbopong.util.interfaces.RGBColor;

public class Entity
{
    public Entity(int x, int y)
    {
        position.x = x;
        position.y = y;
    }

    public Entity(int x, int y, int width, int height)
    {
        this(x, y);

        this.height = height;
        this.width = width;
    }

    public Entity(int x, int y, int width, int height, float speed)
    {
        this(x, y, width, height);

        this.speed = speed;
    }
    
    public Entity(int x, int y, int width, int height, float speed, RGBColor color)
    {
        this(x, y, width, height, speed);
        
        this.color = color;
    }
    
    public Entity(int x, int y, int width, int height, RGBColor color)
    {
        this(x, y, width, height, 0f, color);
    }

    public RGBColor color = Color.WHITE;
    public int width, height;
    public Vector2f position = new Vector2f();
    public Vector2f velocity = new Vector2f();
    public float speed = 1.0f;
    
    public float getBottom()
    {
        return position.y + height;
    }

    public int getHeight()
    {
        return height;
    }

    public float getLeft()
    {
        return position.x;
    }

    public float getRight()
    {
        return position.x + width;
    }

    public float getSpeed()
    {
        return speed;
    }

    public float getTop()
    {
        return position.y;
    }

    public int getWidth()
    {
        return width;
    }

    public void handleLogic()
    {
        
    }

    public void render()
    {
        GL11.glColor3d(color.getRed(), color.getGreen(), color.getBlue());

        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(position.x, position.y);
        GL11.glVertex2f(position.x + width, position.y);
        GL11.glVertex2f(position.x + width, position.y + height);
        GL11.glVertex2f(position.x, position.y + height);
        GL11.glEnd();
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
    
    public void setMiddlePoint(Vector2f position)
    {
        this.position.x = position.x - width / 2;
        this.position.y = position.y - height / 2;
    }
    
    public Vector2f getMiddlePoint()
    {
        Vector2f returnValue = new Vector2f();
            
        returnValue.x = position.x + width / 2;
        returnValue.y = position.y + height / 2;
        
        return returnValue;
    }

}
