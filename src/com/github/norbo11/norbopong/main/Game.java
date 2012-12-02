package com.github.norbo11.norbopong.main;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3d;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glRecti;

import java.io.File;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.github.norbo11.norbopong.game.Ball;
import com.github.norbo11.norbopong.game.Player;
import com.github.norbo11.norbopong.game.states.GameState;
import com.github.norbo11.norbopong.game.states.StateCredits;
import com.github.norbo11.norbopong.game.states.StateGame;
import com.github.norbo11.norbopong.game.states.StateGameOver;
import com.github.norbo11.norbopong.game.states.StateMenu;
import com.github.norbo11.norbopong.game.states.StateOptions;
import com.github.norbo11.norbopong.game.states.StatePause;
import com.github.norbo11.norbopong.util.Config;
import com.github.norbo11.norbopong.util.CustomColor;

public class Game
{
    /* Constants */
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final int STARTING_MARGIN = 10;
    public static final int BORDER_WIDTH = 10;
    public static final String GAME_NAME = "NorboPong";
    public static final String WINDOW_TITLE = GAME_NAME;
    public static final int FPS_CAP = 60;
    public static final int PLAYER_WIDTH = 20;
    public static final File FILE_CONFIG = new File("config.txt");

    /* States */
    public static GameState STATE_MENU;
    public static StateOptions STATE_OPTIONS;
    public static StateCredits STATE_CREDITS;
    public static StateGame STATE_GAME;
    public static StateGameOver STATE_GAME_OVER;
    public static StatePause STATE_PAUSE;

    public static UnicodeFont fontFps;
    public static UnicodeFont fontButton;
    public static UnicodeFont fontTitle;

    private static long lastFrame;
    private static int fps;
    private static int fpsToDisplay = FPS_CAP;
    private static long lastFps;
    private static int currentDelta;
    private static GameState currentState;
    private static Player winner;

    private static Player player1;
    private static Player player2;
    private static Ball ball;

    public static void cleanUp()
    {
        Display.destroy();
        System.exit(1);
    }

    public static void drawBorder()
    {
        // Top, right, bottom, left
        glColor3d(Config.getFloat("BorderR"), Config.getFloat("BorderG"), Config.getFloat("BorderB"));
        glRecti(0, 0, SCREEN_WIDTH, BORDER_WIDTH);
        glRecti(SCREEN_WIDTH - BORDER_WIDTH, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        glRecti(0, SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_HEIGHT - BORDER_WIDTH);
        glRecti(0, 0, BORDER_WIDTH, SCREEN_HEIGHT);
    }

    public static void gameLoop()
    {
        while (!Display.isCloseRequested())
        {
            updateDelta();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            while (Keyboard.next())
            {
                handleGeneralKeyboardInput();
                currentState.handleKeyboardInput();
            }

            while (Mouse.next())
            {
                handleGeneralMouseInput();
                currentState.handleMouseInput();
            }
            currentState.handleLogic();
            currentState.render();

            Display.update();
            Display.sync(FPS_CAP);
        }
    }

    public static void handleGeneralMouseInput()
    {

    }
    
    public static void setDisplayMode(int width, int height, boolean fullscreen) {

        // return if requested DisplayMode is already set
        if ((Display.getDisplayMode().getWidth() == width) && 
            (Display.getDisplayMode().getHeight() == height) && 
        (Display.isFullscreen() == fullscreen)) {
            return;
        }

        try {
            DisplayMode targetDisplayMode = null;
            
        if (fullscreen) {
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            int freq = 0;
                    
            for (int i=0;i<modes.length;i++) {
                DisplayMode current = modes[i];
                        
            if ((current.getWidth() == width) && (current.getHeight() == height)) {
                if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
                    if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
                    targetDisplayMode = current;
                    freq = targetDisplayMode.getFrequency();
                            }
                        }

                // if we've found a match for bpp and frequence against the 
                // original display mode then it's probably best to go for this one
                // since it's most likely compatible with the monitor
                if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
                            (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
                                targetDisplayMode = current;
                                break;
                        }
                    }
                }
            } else {
                targetDisplayMode = new DisplayMode(width,height);
            }

            if (targetDisplayMode == null) {
                System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
                return;
            }

            Display.setDisplayMode(targetDisplayMode);
            Display.setFullscreen(fullscreen);
                
        } catch (LWJGLException e) {
            System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
        }
    }

    public static void handleGeneralKeyboardInput()
    {
        if (Keyboard.getEventKeyState())
        {
            switch (Keyboard.getEventKey())
            {
                case Keyboard.KEY_F1:
                    try {                        
                        if (!Display.isFullscreen()) setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, true);
                        else setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public static Ball getBall()
    {
        return ball;
    }

    public static int getBottomEdge()
    {
        return SCREEN_HEIGHT - BORDER_WIDTH;
    }

    public static int getCurrentDelta()
    {
        return currentDelta;
    }

    public static GameState getCurrentState()
    {
        return currentState;
    }

    public static UnicodeFont getFont()
    {
        return fontFps;
    }

    public static int getLeftEdge()
    {
        return BORDER_WIDTH;
    }

    public static Player getPlayer1()
    {
        return player1;
    }

    public static Player getPlayer2()
    {
        return player2;
    }

    public static int getRightEdge()
    {
        return SCREEN_WIDTH - BORDER_WIDTH;
    }

    public static long getTime()
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public static int getTopEdge()
    {
        return BORDER_WIDTH;
    }

    public static Player getWinner()
    {
        return winner;
    }

    @SuppressWarnings("unchecked")
    public static void init()
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT));
            Display.setResizable(false);
            Display.setTitle(WINDOW_TITLE);
            Display.create();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
            
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        newGame();

        try
        {
            fontFps = new UnicodeFont(File.separator + "res" + File.separator + "font" + File.separator + "visitor1.ttf", 20, false, false);
            fontFps.addAsciiGlyphs();
            fontFps.getEffects().add(new ColorEffect(java.awt.Color.RED));
            fontFps.loadGlyphs();

            fontButton = new UnicodeFont(File.separator + "res" + File.separator + "font" + File.separator + "visitor1.ttf", 25, false, false);
            fontButton.addAsciiGlyphs();
            fontButton.getEffects().add(new ColorEffect(java.awt.Color.CYAN));
            fontButton.loadGlyphs();

            fontTitle = new UnicodeFont(File.separator + "res" + File.separator + "font" + File.separator + "visitor1.ttf", 64, false, false);
            fontTitle.addAsciiGlyphs();
            fontTitle.getEffects().add(new ColorEffect(java.awt.Color.BLUE));
            fontTitle.loadGlyphs();
        } catch (SlickException e)
        {
            e.printStackTrace();
        }
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

    public static void win(Player player)
    {
        winner = player;
        currentState = STATE_GAME_OVER;
    }

    public static void main(String[] args) throws LWJGLException
    {
        Config.reload();
        
        STATE_MENU = new StateMenu();
        STATE_OPTIONS = new StateOptions();
        STATE_CREDITS = new StateCredits();
        STATE_GAME = new StateGame();
        STATE_GAME_OVER = new StateGameOver();
        STATE_PAUSE = new StatePause();
        currentState = STATE_MENU;
        
        init();

        updateDelta();
        lastFps = getTime();
        
        Game.gameLoop();
        
        cleanUp();
    }

    public static void newGame()
    {
        int player1Height = Config.getInteger("Player1Height");
        int player2Height = Config.getInteger("Player2Height");
        
        CustomColor player1Color = new CustomColor(Config.getFloat("Player1R"), Config.getFloat("Player1G"), Config.getFloat("Player1B"));
        CustomColor player2Color = new CustomColor(Config.getFloat("Player2R"), Config.getFloat("Player2G"), Config.getFloat("Player2B"));
        int ballRadius = Config.getInteger("BallRadius");
        
        player1 = new Player(STARTING_MARGIN + BORDER_WIDTH, SCREEN_HEIGHT / 2 - player1Height / 2, PLAYER_WIDTH, player1Height, Config.getFloat("Player1Speed"), player1Color, STATE_OPTIONS.textBoxPlayer1Name.getText());
        player2 = new Player(SCREEN_WIDTH - PLAYER_WIDTH - STARTING_MARGIN - BORDER_WIDTH, SCREEN_HEIGHT / 2 - player2Height / 2, PLAYER_WIDTH, player2Height, Config.getFloat("Player2Speed"), player2Color, STATE_OPTIONS.textBoxPlayer2Name.getText());
        ball = new Ball(SCREEN_WIDTH / 2 - Ball.RADIUS, SCREEN_HEIGHT / 2 - ballRadius, ballRadius, ballRadius, Config.getFloat("BallSpeed"), new CustomColor(Config.getFloat("BallR"), Config.getFloat("BallG"), Config.getFloat("BallB")));
    }

    public static void setCurrentState(GameState currentState)
    {
        Game.currentState = currentState;
    }

    public static void updateDelta()
    {
        long time = getTime();
        currentDelta = (int) (time - lastFrame);
        lastFrame = time;
    }

    public static void updateFps()
    {
        fontFps.drawString(SCREEN_WIDTH / 2 - 60, BORDER_WIDTH, "FPS: " + fpsToDisplay);
        glDisable(GL_TEXTURE_2D); // Done to allow all other stuff to draw

        if (getTime() - lastFps > 1000)
        {
            fpsToDisplay = fps;
            fps = 0;
            lastFps += 1000;
        }

        fps++;
    }
}