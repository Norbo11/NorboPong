package com.github.norbo11.norbopong.game.states;

public interface GameState
{
    public void render();
    public void handleInput();
    public void handleLogic();

}
