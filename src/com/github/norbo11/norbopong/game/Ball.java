package com.github.norbo11.norbopong.game;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.CollisionHelper;
import com.github.norbo11.norbopong.util.DrawingHelper;
import com.github.norbo11.norbopong.util.NumberHelper;
import com.github.norbo11.norbopong.util.interfaces.RGBColor;

public class Ball extends Entity
{
    public Ball(int x, int y, int width, int height, float speed)
    {
        super(x, y, width, height, speed);
        
        initialVelocity();
    }
    
    public void initialVelocity()
    {
        int side = NumberHelper.randomNumber(1, 2);
        int direction = side == 1 ? NumberHelper.randomNumber(60, 120) + 90: NumberHelper.randomNumber(210, 270) + 90;

        velocity.x = (float) (Math.cos(Math.toRadians(direction)) * speed);
        velocity.y = (float) (Math.sin(Math.toRadians(direction)) * speed);
    }

    public Ball(int x, int y, int width, int height, float speed, RGBColor color)
    {
        super(x, y, width, height, speed, color);
        
        initialVelocity();
    }

    public static final int NUM_SEGMENTS = 360;
    public static final int RADIUS = 10;

    @Override
    public void handleLogic()
    {
        String borderCollision = CollisionHelper.checkCollisionWithBorder(this);

        String playerCollision = CollisionHelper.checkCollision(this, Game.getPlayer1());
        playerCollision = playerCollision.equals("") ? CollisionHelper.checkCollision(this, Game.getPlayer2()) : playerCollision;

        Vector2f normal = new Vector2f();

        if (!borderCollision.equalsIgnoreCase("") || !playerCollision.equalsIgnoreCase(""))
        {
            switch (borderCollision)
            {
                case "bottom":
                    normal.y = 1;
                    break;
                case "top":
                    normal.y = -1;
                    break;
                case "right":
                    Game.win(Game.getPlayer1());
                    break;
                case "left":
                    Game.win(Game.getPlayer2());
                    break;
            }

            switch (playerCollision)
            {
                case "bottom":
                    normal.y = 1;
                    break;
                case "top":
                    normal.y = -1;
                    break;
                case "right":
                    normal.x = 1;
                    break;
                case "left":
                    normal.x = -1;
                    break;
            }

            float dotProduct = velocity.x * normal.x + velocity.y * normal.y;
            velocity.x += -2 * dotProduct * normal.x;
            velocity.y += -2 * dotProduct * normal.y;
        }

        position.x += velocity.x * speed * Game.getCurrentDelta();
        position.y += velocity.y * speed * Game.getCurrentDelta();
    }

    @Override
    public void render()
    {
        GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
        DrawingHelper.drawCircle(position.x, position.y, width / 2, NUM_SEGMENTS);
    }

}
