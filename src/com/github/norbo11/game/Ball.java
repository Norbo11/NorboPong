package com.github.norbo11.game;

import org.lwjgl.util.vector.Vector2f;

import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.CollisionManager;
import com.github.norbo11.norbopong.util.DrawingHelper;
import com.github.norbo11.norbopong.util.NumberHelper;

public class Ball extends Entity
{
    public static final int NUM_SEGMENTS = 360;
    public static final int RADIUS = 10;
    
    private final int initialDirection = NumberHelper.randomNumber(0, 359);

    public Ball(int x, int y)
    {
        super(x, y);
        
        setWidth(RADIUS * 2);
        setHeight(RADIUS * 2);
        setSpeed(1f);
        
        velocity.x = (float) (Math.cos(Math.toRadians(initialDirection)) * speed);
        velocity.y = (float) (Math.sin(Math.toRadians(initialDirection)) * speed);
    }

    @Override
    public void handleLogic()
    {
        String collision = "";
        collision = CollisionManager.checkCollision(this, Game.getPlayer1()) == true ? "player1" : collision;
        if (collision.equalsIgnoreCase("")) collision = CollisionManager.checkCollision(this, Game.getPlayer2()) ? "player2" : collision;
        if (collision.equalsIgnoreCase("")) collision = CollisionManager.checkCollisionWithBorder(this);
        
        Vector2f normal = new Vector2f();
                
        if (!collision.equalsIgnoreCase(""))
        {
            switch (collision)
            {
                case "top": 
                    normal.y = 1;
                    break;
                case "bottom":
                    normal.y = -1;
                    break;
                case "left":
                    Game.lose(1);
                    break;
                case "right":
                    Game.lose(2);
                    break;
                case "player1":
                    if (position.x <= Game.getPlayer1().position.x)
                    {
                        normal.x = -1;
                    } else
                    
                    if (position.x > Game.getPlayer1().position.x)
                    {
                        normal.x = 1;
                    } else 
                    
                    if (position.y <= Game.getPlayer1().position.y)
                    {
                        normal.y = -1;
                    } else
                    
                    if (position.y > Game.getPlayer1().position.y)
                    {
                        normal.y = 1;
                    }
                    break;
                case "player2":
                    if (position.x <= Game.getPlayer2().position.x)
                    {
                        normal.x = -1;
                    } else
                    
                    if (position.x > Game.getPlayer2().position.x)
                    {
                        normal.x = 1;
                    } else 
                    
                    if (position.y <= Game.getPlayer2().position.y)
                    {
                        normal.y = -1;
                    } else
                    
                    if (position.y > Game.getPlayer2().position.y)
                    {
                        normal.y = 1;
                    }
                    break;
            }
            
            float dotProduct = velocity.x * normal.x + velocity.y * normal.y;
            velocity.x = -2 * dotProduct * normal.x + velocity.x;
            velocity.y = -2 * dotProduct * normal.y + velocity.y;
        }

        position.x += velocity.x * Game.getCurrentDelta();
        position.y += velocity.y * Game.getCurrentDelta();    
    }

    @Override
    public void render()
    {
        DrawingHelper.drawCircle(position.x, position.y, width / 2, NUM_SEGMENTS);
    }

}
