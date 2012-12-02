package com.github.norbo11.norbopong.game.states;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.geom.Vector2f;

import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.Color;
import com.github.norbo11.norbopong.util.DrawingHelper;
import com.github.norbo11.norbopong.util.ui.Button;

public class StateMenu implements GameState
{

    public StateMenu()
    {
        buttons.add(buttonPlay);
        buttons.add(buttonOptions);
        buttons.add(buttonCredits);
        buttons.add(buttonExit);
    }

    private ArrayList<Button> buttons = new ArrayList<>();
    private Button buttonPlay = new Button(Game.SCREEN_WIDTH / 2 - 200, 200, 400, 50, "Play!", Color.BLUE, Color.GREEN, Button.runnableButtonPlay, new int[] {Keyboard.KEY_SPACE});
    private Button buttonOptions = new Button(Game.SCREEN_WIDTH / 2 - 200, 256, 400, 50, "Options", Color.BLUE, Color.GREEN, Button.runnableButtonOptions);
    private Button buttonCredits = new Button(Game.SCREEN_WIDTH / 2 - 200, 312, 400, 50, "Credits", Color.BLUE, Color.GREEN, Button.runnableButtonCredits);
    private Button buttonExit = new Button(Game.SCREEN_WIDTH / 2 - 200, 368, 400, 50, "Exit Game", Color.BLUE, Color.GREEN, Button.runnableButtonExit);

    @Override
    public void handleKeyboardInput()
    {
        for (Button button : buttons)
        {
            button.handleKeyboardInput();
        }
        
        if (Keyboard.getEventKeyState())
        {
            switch (Keyboard.getEventKey())
            {
                case Keyboard.KEY_ESCAPE:
                    Game.cleanUp();
                    break;
            }
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
        DrawingHelper.drawCenteredText(Game.fontTitle, new Vector2f(0, 10), Game.SCREEN_WIDTH, 0, Game.GAME_NAME);
        for (Button button : buttons)
        {
            button.render();
        }
    }
}
