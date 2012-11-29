package com.github.norbo11.norbopong.util.buttons;

import org.newdawn.slick.Color;

import com.github.norbo11.norbopong.main.Game;

public class ButtonMainMenu extends Button
{

    public ButtonMainMenu(int x, int y, int width, int height, Color color, String text)
    {
        super(x, y, width, height, color, text);
    }

    @Override
    public void onClick()
    {
        Game.setCurrentState(Game.STATE_MENU);
    }

    @Override
    public void onHover()
    {
    }
}
