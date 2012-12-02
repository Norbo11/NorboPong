package com.github.norbo11.norbopong.game;

import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.CollisionHelper;
import com.github.norbo11.norbopong.util.interfaces.RGBColor;

public class Player extends Entity
{
    public Player(int x, int y, int width, int height, float speed, RGBColor color, String name)
    {
        super(x, y, width, height, speed, color);

        this.name = name;
    }
    
    public Player(int x, int y, int width, int height, float speed, RGBColor color)
    {
        this(x, y, width, height, speed, color, "");
     }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    private String name;

    @Override
    public void handleLogic()
    {
        position.x += velocity.x;
        position.y += velocity.y;

        String borderCollision = CollisionHelper.checkCollisionWithBorder(this);

        switch (borderCollision)
        {
            case "bottom":
                position.y = Game.BORDER_WIDTH;
                break;
            case "top":
                position.y = Game.SCREEN_HEIGHT - height - Game.BORDER_WIDTH;
                break;
            case "right":
                position.x = Game.BORDER_WIDTH;
                break;
            case "left":
                position.x = Game.SCREEN_WIDTH - width - Game.BORDER_WIDTH;
                break;
        }
    }
}
