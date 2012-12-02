package com.github.norbo11.norbopong.game.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.geom.Vector2f;

import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.DrawingHelper;

public class StateCredits implements GameState
{
    
    @Override
    public void handleKeyboardInput()
    {
        if (Keyboard.getEventKeyState())
        {
            switch (Keyboard.getEventKey())
            {
                case Keyboard.KEY_ESCAPE:
                    Game.setCurrentState(Game.STATE_MENU);
                    break;
            }
        }
    }

    @Override
    public void handleLogic()
    {

    }

    @Override
    public void handleMouseInput()
    {

    }

    @Override
    public void render()
    {
        DrawingHelper.drawCenteredText(Game.fontTitle, new Vector2f(0, 0), Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, 
        Game.GAME_NAME + " made by Norbo11\n" +
        "Shoutout to KGB!"
        );
    }

}
