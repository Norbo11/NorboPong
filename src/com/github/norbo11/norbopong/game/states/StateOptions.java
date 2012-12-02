package com.github.norbo11.norbopong.game.states;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.github.norbo11.norbopong.game.Entity;
import com.github.norbo11.norbopong.main.Game;
import com.github.norbo11.norbopong.util.Color;
import com.github.norbo11.norbopong.util.Config;
import com.github.norbo11.norbopong.util.interfaces.RGBColor;
import com.github.norbo11.norbopong.util.ui.ActiveGroup;
import com.github.norbo11.norbopong.util.ui.Button;
import com.github.norbo11.norbopong.util.ui.Slider;
import com.github.norbo11.norbopong.util.ui.Spinner;
import com.github.norbo11.norbopong.util.ui.TextBox;

public class StateOptions implements GameState
{
    private ActiveGroup<TextBox> textBoxes;
    private ArrayList<Slider> sliders = new ArrayList<>();
    private ArrayList<Spinner> spinners = new ArrayList<>();
    
    public TextBox textBoxPlayer1Name;
    public TextBox textBoxPlayer2Name;
    public Button buttonDone;
    
    public Slider sliderPlayer1R;
    public Slider sliderPlayer1G;
    public Slider sliderPlayer1B;
    
    public Slider sliderPlayer2R;
    public Slider sliderPlayer2G;
    public Slider sliderPlayer2B;  
    
    public Slider sliderBorderR;
    public Slider sliderBorderG;
    public Slider sliderBorderB;  
    
    public Slider sliderBallR;
    public Slider sliderBallG;
    public Slider sliderBallB;  
    
    public Spinner spinnerBallSpeed;
    public Spinner spinnerPlayer1Speed;
    public Spinner spinnerPlayer2Speed;
    
    public StateOptions()
    {
        /* Textboxes */
        textBoxPlayer1Name = new TextBox(20, 45, 250, 35, 20, Color.ORANGE, Color.FUCHSIA, Config.getString("Player1Name"));
        textBoxPlayer2Name = new TextBox(Game.SCREEN_WIDTH - 300, 45, 250, 35, 20, Color.ORANGE, Color.FUCHSIA, Config.getString("Player2Name"));
        textBoxes = new ActiveGroup<TextBox>(textBoxPlayer1Name, textBoxPlayer2Name);
        
        /* Sliders */
        sliders.add(sliderPlayer1R = new Slider(new Entity(20, 130, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("Player1R")));
        sliders.add(sliderPlayer1G = new Slider(new Entity(20, 180, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("Player1G")));
        sliders.add(sliderPlayer1B = new Slider(new Entity(20, 230, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("Player1B")));
        
        sliders.add(sliderPlayer2R = new Slider(new Entity(Game.SCREEN_WIDTH - 300, 130, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("Player2R")));
        sliders.add(sliderPlayer2G = new Slider(new Entity(Game.SCREEN_WIDTH - 300, 180, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("Player2G")));
        sliders.add(sliderPlayer2B = new Slider(new Entity(Game.SCREEN_WIDTH - 300, 230, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("Player2B")));
        
        sliders.add(sliderBorderR = new Slider(new Entity(Game.SCREEN_WIDTH / 2 - 100, 130, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("BorderR")));
        sliders.add(sliderBorderG = new Slider(new Entity(Game.SCREEN_WIDTH / 2 - 100, 180, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("BorderG")));
        sliders.add(sliderBorderB = new Slider(new Entity(Game.SCREEN_WIDTH / 2 - 100, 230, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("BorderB")));
        
        sliders.add(sliderBallR = new Slider(new Entity(Game.SCREEN_WIDTH / 2 - 100, 470, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("BallR")));
        sliders.add(sliderBallG = new Slider(new Entity(Game.SCREEN_WIDTH / 2 - 100, 520, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("BallG")));
        sliders.add(sliderBallB = new Slider(new Entity(Game.SCREEN_WIDTH / 2 - 100, 570, 200, 10, Color.GREEN), new Entity(0, 0, 10, 20, Color.BLUE), Color.ORANGE, Config.getFloat("BallB")));
        
        spinners.add(spinnerBallSpeed = new Spinner(20, 470, 40, 100, Color.GREEN, Color.ORANGE, Color.GREEN, Config.getFloat("BallSpeed"), 0.1f));
        
        /* Buttons */
        buttonDone = new Button(Game.SCREEN_WIDTH / 2 - 200, Game.SCREEN_HEIGHT - 70, 400, 50, "Done", Color.BLUE, Color.GREEN, Button.runnableButtonOptionsDone);
    }
    
    @Override
    public void handleKeyboardInput()
    {
        for (TextBox tb : textBoxes)
        {
            tb.handleKeyboardInput();
        }
        
        buttonDone.handleKeyboardInput();
        
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
        for (TextBox tb : textBoxes)
        {
            tb.handleLogic();
        }
        
        for (Slider slider : sliders)
        {
            slider.handleLogic();                        
        }  
        
        for (Spinner spinner : spinners)
        {
            spinner.handleLogic();                        
        }  
        
        buttonDone.handleLogic();
        
        Game.getPlayer1().color.setRed((int) (sliderPlayer1R.getPercentage() * 255));
        Game.getPlayer1().color.setGreen((int) (sliderPlayer1G.getPercentage() * 255));
        Game.getPlayer1().color.setBlue((int) (sliderPlayer1B.getPercentage() * 255));
        
        Game.getPlayer2().color.setRed((int) (sliderPlayer2R.getPercentage() * 255));
        Game.getPlayer2().color.setGreen((int) (sliderPlayer2G.getPercentage() * 255));
        Game.getPlayer2().color.setBlue((int) (sliderPlayer2B.getPercentage() * 255));
    }

    @Override
    public void handleMouseInput()
    {
        for (TextBox tb : textBoxes)
        {
            tb.handleMouseInput();
        }  
        
        for (Slider slider : sliders)
        {
            slider.handleMouseInput();
        }  
        
        for (Spinner spinner : spinners)
        {
            spinner.handleMouseInput();
        }  
        
        buttonDone.handleMouseInput();
    }

    @Override
    public void render()
    {
        Game.fontButton.drawString(textBoxPlayer1Name.position.x, 20, "Player 1 Name:");
        Game.fontButton.drawString(textBoxPlayer2Name.position.x, 20, "Player 2 Name:");
        Game.fontButton.drawString(sliderBorderR.bar.position.x, 20, "Border Color:");
        Game.fontButton.drawString(sliderBallR.bar.position.x, sliderBallR.bar.position.y - 130, "Ball Color:");
        
        Game.fontButton.drawString(sliderPlayer1R.bar.position.x, sliderPlayer1R.bar.position.y - 30, "Paddle Color - Red");
        Game.fontButton.drawString(sliderPlayer1G.bar.position.x, sliderPlayer1G.bar.position.y - 30, "Paddle Color - Green");
        Game.fontButton.drawString(sliderPlayer1B.bar.position.x, sliderPlayer1B.bar.position.y - 30, "Paddle Color - Blue");
        
        Game.fontButton.drawString(sliderPlayer2R.bar.position.x, sliderPlayer2R.bar.position.y - 30, "Paddle Color - Red");
        Game.fontButton.drawString(sliderPlayer2G.bar.position.x, sliderPlayer2G.bar.position.y - 30, "Paddle Color - Green");
        Game.fontButton.drawString(sliderPlayer2B.bar.position.x, sliderPlayer2B.bar.position.y - 30, "Paddle Color - Blue");
        
        Game.fontButton.drawString(sliderBorderR.bar.position.x, sliderBorderR.bar.position.y - 30, "Border Color - Red");
        Game.fontButton.drawString(sliderBorderG.bar.position.x, sliderBorderG.bar.position.y - 30, "Border Color - Green");
        Game.fontButton.drawString(sliderBorderB.bar.position.x, sliderBorderB.bar.position.y - 30, "Border Color - Blue");
        
        Game.fontButton.drawString(sliderBallR.bar.position.x, sliderBallR.bar.position.y - 30, "Ball Color - Red");
        Game.fontButton.drawString(sliderBallG.bar.position.x, sliderBallG.bar.position.y - 30, "Ball Color - Green");
        Game.fontButton.drawString(sliderBallB.bar.position.x, sliderBallB.bar.position.y - 30, "Ball Color - Blue");
        
        RGBColor color1 = Game.getPlayer1().color;
        GL11.glColor3f(color1.getRed(), color1.getGreen(), color1.getBlue());
        GL11.glRectf(sliderPlayer1B.bar.position.x + (sliderPlayer1B.bar.width / 2 - 50), sliderPlayer1B.bar.position.y + 25, sliderPlayer1B.bar.position.x + (sliderPlayer1B.bar.width / 2 - 50) + 100, sliderPlayer1B.bar.position.y + 70);
        
        RGBColor color2 = Game.getPlayer2().color;
        GL11.glColor3f(color2.getRed(), color2.getGreen(), color2.getBlue());
        GL11.glRectf(sliderPlayer2B.bar.position.x + (sliderPlayer2B.bar.width / 2 - 50), sliderPlayer2B.bar.position.y + 25, sliderPlayer2B.bar.position.x + (sliderPlayer2B.bar.width / 2 - 50) + 100, sliderPlayer2B.bar.position.y + 70);
        
        GL11.glColor3d(sliderBorderR.getPercentage(), sliderBorderG.getPercentage(), sliderBorderB.getPercentage());
        GL11.glRectf(sliderBorderB.bar.position.x + (sliderBorderB.bar.width / 2 - 50), sliderBorderB.bar.position.y + 25, sliderBorderB.bar.position.x + (sliderBorderB.bar.width / 2 - 50) + 100, sliderBorderB.bar.position.y + 70);
        
        GL11.glColor3d(sliderBallR.getPercentage(), sliderBallG.getPercentage(), sliderBallB.getPercentage());
        GL11.glRectf(sliderBallB.bar.position.x + (sliderBallB.bar.width / 2 - 50), sliderBallB.bar.position.y + 25, sliderBallB.bar.position.x + (sliderBallB.bar.width / 2 - 50) + 100, sliderBallB.bar.position.y + 70);
        
        for (Slider slider : sliders)
        {
            slider.render();
        }
        
        for (TextBox tb : textBoxes)
        {
            tb.render();
        }     
        
        for (Spinner spinner : spinners)
        {
            spinner.render();
        }  
        
        buttonDone.render();
        
        
        /*Test*/
        GL11.glColor3f(0f, 1.00f, 0f);        
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(0, 0);
        GL11.glVertex2f(0 + 100, 0);
        GL11.glVertex2f(0 + 100, 0 + 100);
        GL11.glVertex2f(0, 0 + 100);
        GL11.glEnd();
    }

}
