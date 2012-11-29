package com.github.norbo11.game.states;

import static com.github.norbo11.norbopong.main.Game.cleanUp;
import static com.github.norbo11.norbopong.main.Game.drawBorder;
import static com.github.norbo11.norbopong.main.Game.getBall;
import static com.github.norbo11.norbopong.main.Game.getPlayer1;
import static com.github.norbo11.norbopong.main.Game.getPlayer2;

import org.lwjgl.input.Keyboard;

import com.github.norbo11.norbopong.main.Game;

public class StateGame implements GameState
{
    @Override
    public void render()
    {           
        getPlayer1().handleLogic();
        getPlayer2().handleLogic();
        getBall().handleLogic();
               
        getPlayer1().render();
        getPlayer2().render();
        getBall().render();
        
        drawBorder();
    }
    
    @Override
    public void handleInput()
    {        
        while (Keyboard.next())
        {            
            if (Keyboard.getEventKeyState())
            {
                switch (Keyboard.getEventKey())
                {
                    case Keyboard.KEY_W:
                        getPlayer1().velocity.y = (int) -(getPlayer1().getSpeed() * Game.getCurrentDelta());
                        break;
                        
                    case Keyboard.KEY_S:
                        getPlayer1().velocity.y = (int) (getPlayer1().getSpeed() * Game.getCurrentDelta());
                        break;
                        
                    case Keyboard.KEY_UP:
                        getPlayer2().velocity.y = (int) -(getPlayer2().getSpeed() * Game.getCurrentDelta());
                        break;
                        
                    case Keyboard.KEY_DOWN:
                        getPlayer2().velocity.y = (int) (getPlayer2().getSpeed() * Game.getCurrentDelta());
                        break;
                        
                    case Keyboard.KEY_ESCAPE:
                        cleanUp();
                        break;
                }
            } else
            {
                switch (Keyboard.getEventKey())
                {
                    case Keyboard.KEY_W: case Keyboard.KEY_S:
                        getPlayer1().velocity.y = 0;
                        break;
                       
                    case Keyboard.KEY_UP: case Keyboard.KEY_DOWN:
                        getPlayer2().velocity.y = 0;
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
