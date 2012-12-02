package com.github.norbo11.norbopong.util.ui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.github.norbo11.norbopong.game.Entity;
import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.Color;
import com.github.norbo11.norbopong.util.Config;
import com.github.norbo11.norbopong.util.DrawingHelper;
import com.github.norbo11.norbopong.util.MouseHelper;
import com.github.norbo11.norbopong.util.interfaces.GameElement;

public class Button extends Entity implements GameElement
{
    public Button(int x, int y, int width, int height, String text, Color colorNormal, Color colorHover, Runnable onClick)
    {
        super(x, y, width, height);

        this.text = text;
        this.onClick = onClick;
        this.colorNormal = colorNormal;
        this.colorHover = colorHover;
        this.color = colorNormal;
    }
    
    public Button(int x, int y, int width, int height, String text, Color colorNormal, Color colorHover, Runnable onClick, int[] keyboardShortcuts)
    {
        this(x, y, width, height, text, colorNormal, colorHover, onClick);
        
        this.keyboardShortcuts = keyboardShortcuts;
    }

    public static final Runnable runnableButtonPlay = new Runnable()
    {

        @Override
        public void run()
        {
            Game.newGame();
            Game.setCurrentState(Game.STATE_GAME);
        }
    };

    public static final Runnable runnableButtonOptions = new Runnable()
    {

        @Override
        public void run()
        {
            Game.setCurrentState(Game.STATE_OPTIONS);
        }
    };

    public static final Runnable runnableButtonCredits = new Runnable()
    {

        @Override
        public void run()
        {
            Game.setCurrentState(Game.STATE_CREDITS);
        }
    };

    public static Runnable runnableButtonExit = new Runnable()
    {

        @Override
        public void run()
        {
            Game.cleanUp();
        }
    };

    public static Runnable runnableButtonMenu = new Runnable()
    {

        @Override
        public void run()
        {
            Game.setCurrentState(Game.STATE_MENU);
        }
    };
    
    public static Runnable runnableButtonContinue = new Runnable()
    {

        @Override
        public void run()
        {
            Game.setCurrentState(Game.STATE_GAME);
        }
    };
    
    public static Runnable runnableButtonOptionsDone = new Runnable()
    {

        @Override
        public void run()
        {
            Config.put("Player1Name", Game.STATE_OPTIONS.textBoxPlayer1Name.getText());
            Config.put("Player2Name", Game.STATE_OPTIONS.textBoxPlayer2Name.getText());
            
            Config.put("Player1R", Game.STATE_OPTIONS.sliderPlayer1R.getPercentage());
            Config.put("Player1G", Game.STATE_OPTIONS.sliderPlayer1G.getPercentage());
            Config.put("Player1B", Game.STATE_OPTIONS.sliderPlayer1B.getPercentage());
            
            Config.put("Player2R", Game.STATE_OPTIONS.sliderPlayer2R.getPercentage());
            Config.put("Player2G", Game.STATE_OPTIONS.sliderPlayer2G.getPercentage());
            Config.put("Player2B", Game.STATE_OPTIONS.sliderPlayer2B.getPercentage());
            
            Config.put("Player1Speed", 1f);
            Config.put("Player2Speed", 1f);
            
            Config.put("Player1Height", 100);
            Config.put("Player2Height", 100);
            
            Config.put("BorderR", Game.STATE_OPTIONS.sliderBorderR.getPercentage());
            Config.put("BorderG", Game.STATE_OPTIONS.sliderBorderG.getPercentage());
            Config.put("BorderB", Game.STATE_OPTIONS.sliderBorderB.getPercentage());
            
            Config.put("BallR", Game.STATE_OPTIONS.sliderBallR.getPercentage());
            Config.put("BallG", Game.STATE_OPTIONS.sliderBallG.getPercentage());
            Config.put("BallB", Game.STATE_OPTIONS.sliderBallB.getPercentage());
            
            Config.put("BallSpeed", Game.STATE_OPTIONS.spinnerBallSpeed.getValue());
            
            Config.save();
            
            runnableButtonMenu.run();
        }
    };

    private Color colorNormal;
    private Color colorHover;
    private String text;
    private Runnable onClick;
    private int[] keyboardShortcuts = new int[10];

    public void handleMouseInput()
    {
        if (Mouse.getEventButtonState())
        {
            switch (Mouse.getEventButton())
            {
                case 0:
                    if (MouseHelper.isInside(this))
                    {
                        onClick.run();
                    }
                    break;
            }
        }
    }
    
    public boolean isKeyboardShortcut(int key)
    {
        for (int shortcut : keyboardShortcuts)
        {
            if (key == shortcut) return true;
        }
        
        return false;
    }
    
    public void handleKeyboardInput()
    {
        if (Keyboard.getEventKeyState())
        {
            if (isKeyboardShortcut(Keyboard.getEventKey()))
            {
                onClick.run();
            }
        }
    }

    @Override
    public void handleLogic()
    {
        if (MouseHelper.isInside(this))
        {
            color = colorHover;
        } else
        {
            color = colorNormal;
        }
    }

    @Override
    public void render()
    {
        super.render();
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        DrawingHelper.drawCenteredText(Game.fontButton, position, width, height, text);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public void setOnClick(Runnable onClick)
    {
        this.onClick = onClick;
    }
}
