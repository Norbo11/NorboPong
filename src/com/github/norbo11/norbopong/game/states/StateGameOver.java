package com.github.norbo11.norbopong.game.states;

import static com.github.norbo11.norbopong.main.Game.cleanUp;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.buttons.Button;
import com.github.norbo11.norbopong.util.buttons.ButtonMainMenu;

public class StateGameOver implements GameState
{

    private Button buttonMainMenu;
    
    public StateGameOver()
    {
        buttonMainMenu = new ButtonMainMenu(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2, 200, 50, Color.red, "Main Menu");
    }
    
    @Override
    public void render()
    {
        buttonMainMenu.render();
    }
    
    @Override
    public void handleInput()
    {        
        buttonMainMenu.handleLogic();
        while (Keyboard.next())
        {            
            if (Keyboard.getEventKeyState())
            {
                switch (Keyboard.getEventKey())
                {
                    case Keyboard.KEY_ESCAPE:
                        cleanUp();
                        break;
                }
            }
        }
    }

    @Override
    public void handleLogic()
    {
        // TODO Auto-generated method stub
        
    }

}
