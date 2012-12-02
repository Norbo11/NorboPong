package com.github.norbo11.norbopong.util;

import com.github.norbo11.norbopong.game.Entity;
import com.github.norbo11.norbopong.main.Game;

public class CollisionHelper
{
    public static String checkCollision(Entity entity1, Entity entity2)
    {
        if (entity1.getBottom() <= entity2.getTop())
        {
            return "";
        }

        if (entity1.getTop() >= entity2.getBottom())
        {
            return "";
        }

        if (entity1.getRight() <= entity2.getLeft())
        {
            return "";
        }

        if (entity1.getLeft() >= entity2.getRight())
        {
            return "";
        }

        if (entity1.position.x <= entity2.position.x)
        {
            return "left";
        }

        if (entity1.position.x > entity2.position.x)
        {
            return "right";
        }

        if (entity1.position.y <= entity2.position.y)
        {
            return "top";
        }

        if (entity1.position.y > entity2.position.y)
        {
            return "bottom";
        }
        
        return "";
    }

    public static String checkCollisionWithBorder(Entity entity)
    {
        String collision = "";

        if (entity.getLeft() <= Game.getLeftEdge())
        {
            collision = "right";
        } else

        if (entity.getRight() >= Game.getRightEdge())
        {
            collision = "left";
        } else

        if (entity.getTop() <= Game.getTopEdge())
        {
            collision = "bottom";
        } else

        if (entity.getBottom() >= Game.getBottomEdge())
        {
            collision = "top";
        }

        return collision;
    }
}
