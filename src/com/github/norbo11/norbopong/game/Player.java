package com.github.norbo11.norbopong.game;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.CollisionManager;

public class Player extends Entity
{
    public static final int WIDTH = 25;
    public static final int HEIGHT = 100;
    
    public Player(int x, int y)
    {
        super(x, y);
        
        setSpeed(1f);
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }
    
    @Override
    public void render()
    {
        glBegin(GL_QUADS);
            glVertex2f(position.x, position.y);
            glVertex2f(position.x + getWidth(), position.y);
            glVertex2f(position.x + getWidth(), position.y + getHeight());
            glVertex2f(position.x, position.y + getHeight());
        glEnd();
    }

    @Override
    public void handleLogic()
    {        
        position.x += velocity.x;
        position.y += velocity.y;
        
        String borderCollision = CollisionManager.checkCollisionWithBorder(this);
        
        switch (borderCollision)
        {
            case "top":
                position.y = Game.BORDER_WIDTH;
                break;
            case "bottom":
                position.y = Game.SCREEN_HEIGHT - height - Game.BORDER_WIDTH;
                break;
            case "left":
                position.x = Game.BORDER_WIDTH;
                break;
            case "right":
                position.x = Game.SCREEN_WIDTH - width - Game.BORDER_WIDTH;
                break;
        }
    }
}
