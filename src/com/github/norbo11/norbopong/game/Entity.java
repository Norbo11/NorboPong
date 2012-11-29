package com.github.norbo11.norbopong.game;

import org.lwjgl.util.vector.Vector2f;

public abstract class Entity
{    
    public Entity(int x, int y)
    {
        position.x = x;
        position.y = y;
    }
    
    public Entity(int x, int y, int width, int height)
    {
        position.x = x;
        position.y = y;
        
        setHeight(height);
        setWidth(width);
    }
    
    public int width, height;
    public Vector2f position = new Vector2f();
    public Vector2f velocity = new Vector2f();
    public float speed;

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public float getSpeed()
    {
        return speed;
    }
    
    public abstract void handleLogic();

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    
    public float getTop()
    {
        return position.y;
    }
    
    public float getBottom()
    {
        return position.y + height;
    }
    
    public float getLeft()
    {
        return position.x;
    }
    
    public float getRight()
    {
        return position.x + width;
    }
    
    public float centerX()
    {
        return (getLeft() + getRight()) / 2;
    }
    
    public float centerY()
    {
        return (getTop() + getBottom()) / 2;
    }
    
    public abstract void render();

}
