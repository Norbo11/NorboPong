package com.github.norbo11.norbopong.util;

import com.github.norbo11.game.Entity;
import com.github.norbo11.norbopong.main.Game;

public class CollisionManager
{    
    public static boolean checkCollision(Entity entity1, Entity entity2)
    {
        if (entity1.getBottom() <= entity2.getTop())
        {
            return false;
        }
        
        if (entity1.getTop() >= entity2.getBottom())
        {
            return false;
        }
        
        if (entity1.getRight() <= entity2.getLeft())
        {
            return false;
        }
        
        if (entity1.getLeft() >= entity2.getRight())
        {
            return false;
        }
        
        return true;
    }
    
    public static String checkCollisionWithBorder(Entity entity)
    {
        String collision = "";
        
        if (entity.getLeft() <= Game.getLeftEdge())
        {
            collision = "left";
        } else
        
        if (entity.getRight() >= Game.getRightEdge())
        {
            collision = "right";
        } else
        
        if (entity.getTop() <= Game.getTopEdge())
        {
            collision = "top";
        } else
        
        if (entity.getBottom() >= Game.getBottomEdge())
        {
            collision = "bottom";
        }
                
        return collision;
    }
}
