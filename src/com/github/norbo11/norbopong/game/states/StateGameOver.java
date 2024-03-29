package com.github.norbo11.norbopong.game.states;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.geom.Vector2f;

import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.Color;
import com.github.norbo11.norbopong.util.DrawingHelper;
import com.github.norbo11.norbopong.util.ui.Button;

public class StateGameOver implements GameState
{
    private ArrayList<Button> buttons = new ArrayList<>();
    private Button buttonPlay = new Button(Game.SCREEN_WIDTH / 2 - 200, 200, 400, 50, "Play Again", Color.BLUE, Color.GREEN, Button.runnableButtonPlay, new int[]{Keyboard.KEY_SPACE});
    private Button buttonMainMenu = new Button(Game.SCREEN_WIDTH / 2 - 200, 256, 400, 50, "Main Menu", Color.BLUE, Color.GREEN, Button.runnableButtonMenu, new int[]{Keyboard.KEY_ESCAPE});
    
    public StateGameOver()
    {
        buttons.add(buttonMainMenu);
        buttons.add(buttonPlay);
    }
    
    @Override
    public void handleKeyboardInput()
    {
        for (Button button : buttons)
        {
            button.handleKeyboardInput();
        }    
    }

    @Override
    public void handleLogic()
    {
        for (Button button : buttons)
        {
            button.handleLogic();
        }
    }

    @Override
    public void handleMouseInput()
    {
        for (Button button : buttons)
        {
            button.handleMouseInput();
        }    
    }

    @Override
    public void render()
    {
        DrawingHelper.drawCenteredText(Game.fontButton, new Vector2f(0, 100), Game.SCREEN_WIDTH, 0, Game.getWinner().getName() + " is the winner!");
        for (Button button : buttons)
        {
            button.render();
        }        
    }

}
