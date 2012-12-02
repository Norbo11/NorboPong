package com.github.norbo11.norbopong.game.states;

public interface GameState
{
    public void handleKeyboardInput();

    public void handleLogic();

    public void handleMouseInput();

    public void render();

}
