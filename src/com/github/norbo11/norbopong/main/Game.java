package com.github.norbo11.norbopong.main;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.github.norbo11.game.Ball;
import com.github.norbo11.game.Player;
import com.github.norbo11.game.states.GameState;
import com.github.norbo11.game.states.StateGame;
import com.github.norbo11.game.states.StateGameOver;

public class Game
{
    /* Constants */
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final int STARTING_MARGIN = 10;
    public static final int BORDER_WIDTH = 10;
    public static final String WINDOW_TITLE = "NorboPong";
    public static final int FPS_CAP = 60;
    
    /* States */
    public static final GameState STATE_GAME = new StateGame();
    public static final GameState STATE_MENU = new StateGame();
    public static final GameState STATE_GAME_OVER = new StateGameOver();
    
    private static UnicodeFont font;
    
    private static long lastFrame;
    private static int fps;
    private static int fpsToDisplay = FPS_CAP;
    private static long lastFps;
    private static int currentDelta;
    private static GameState currentState = STATE_GAME_OVER;
    
    public static GameState getCurrentState()
    {
        return currentState;
    }

    public static void setCurrentState(GameState currentState)
    {
        Game.currentState = currentState;
    }

    private static Player player1 = new Player(STARTING_MARGIN + BORDER_WIDTH, STARTING_MARGIN + BORDER_WIDTH);
    private static Player player2 = new Player(SCREEN_WIDTH - Player.WIDTH - STARTING_MARGIN - BORDER_WIDTH, SCREEN_HEIGHT - Player.HEIGHT - STARTING_MARGIN - BORDER_WIDTH);
    private static Ball ball = new Ball(SCREEN_WIDTH / 2 - Ball.RADIUS, SCREEN_HEIGHT / 2 - Ball.RADIUS);
    
    public static void main(String[] args) throws LWJGLException
    {
        Game.gameLoop();
    }
    
    public static int getCurrentDelta()
    {
        return currentDelta;
    }

    public static int getLeftEdge()
    {
        return BORDER_WIDTH;
    }
    
    public static int getRightEdge()
    {
        return SCREEN_WIDTH - BORDER_WIDTH;
    }
    
    public static int getTopEdge()
    {
        return BORDER_WIDTH;
    }
    
    public static int getBottomEdge()
    {
        return SCREEN_HEIGHT - BORDER_WIDTH;
    }
    
    public static Texture loadTexture(String file)
    {
        String extension = file.split("\\.")[1].toUpperCase();
        try
        {
            return TextureLoader.getTexture(extension, ResourceLoader.getResourceAsStream("res" + File.separator + "texture" + File.separator + file + ".png"));
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void updateFps()
    {
        font.drawString(SCREEN_WIDTH / 2 - 60, BORDER_WIDTH, "FPS: " + fpsToDisplay);
        glDisable(GL_TEXTURE_2D); //Done to allow all other stuff to draw
        
        if (getTime() - lastFps > 1000) 
        {
            fpsToDisplay = fps;
            fps = 0;
            lastFps += 1000;
        }
        
        fps++;
    }

    
    @SuppressWarnings("unchecked")
    public static void init()
    {
        try {
            Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
            Display.setResizable(false);
            Display.setTitle(WINDOW_TITLE);
            Display.create(new PixelFormat(8, 8, 8));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        try
        {
            font = new UnicodeFont(File.separator + "res" + File.separator + "font" + File.separator + "visitor1.ttf", 20, false, false);
            font.addAsciiGlyphs();
            font.getEffects().add(new ColorEffect(Color.red));
            font.loadGlyphs();
        } catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void gameLoop()
    {
        init();
               
        updateDelta();
        lastFps = getTime();
        
        while (!Display.isCloseRequested())
        {
            updateDelta();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            currentState.handleInput();
            currentState.handleLogic();
            currentState.render();
                       
            updateFps();
            Display.update();
            Display.sync(FPS_CAP);
        }
        
        cleanUp();
    }

    public static UnicodeFont getFont()
    {
        return font;
    }

    public static void drawBorder()
    {
        //Top, right, bottom, left
        glRecti(0, 0, SCREEN_WIDTH, BORDER_WIDTH);
        glRecti(SCREEN_WIDTH - BORDER_WIDTH, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        glRecti(0, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT - BORDER_WIDTH);
        glRecti(0, 0, BORDER_WIDTH, SCREEN_HEIGHT);
    }
    
    public static void cleanUp()
    {
        Display.destroy();
        System.exit(1);
    }
    
    public static void updateDelta()
    {
        long time = getTime();
        currentDelta = (int) (time - lastFrame);
        lastFrame = time;
    }
    
    public static Player getPlayer1()
    {
        return player1;
    }

    public static Player getPlayer2()
    {
        return player2;
    }

    public static Ball getBall()
    {
        return ball;
    }

    public static long getTime()
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public static void lose(int i)
    {
        currentState = STATE_GAME_OVER;
    }
}