package com.github.norbo11.norbopong.util;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.github.norbo11.norbopong.game.Entity;
import com.github.norbo11.norbopong.main.Game;

public class MouseManager
{
    public static Vector3f getMousePos()
    {
        return new Vector3f((float) Mouse.getX(), (float) (Game.SCREEN_HEIGHT - Mouse.getY()), 0);
    }
    
    public static boolean isInside(Entity entity)
    {
        Vector3f mouse = getMousePos();
        
        return mouse.x > entity.position.x && mouse.x < entity.position.x + entity.width && mouse.y > entity.position.y && mouse.y < entity.position.y + entity.height;
    }
}
