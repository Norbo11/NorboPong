package com.github.norbo11.norbopong.util;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Vector2f;

import com.github.norbo11.norbopong.main.Game;

//import static org.lwjgl.opengl.GL11.*;

public class DrawingHelper
{
    public static void drawCenteredText(UnicodeFont font, Vector2f position, int width, int height, String text)
    {
        Game.fontButton.drawString(position.x + (width / 2 - Game.fontButton.getWidth(text) / 2), position.y + (height / 2 - Game.fontButton.getHeight(text) / 2), text);
    }

    public static void drawCircle(float cx, float cy, float r, int num_segments)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef(cx + r, cy + r, 0);
        GL11.glScalef(r, r, 1);

        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2f(0, 0);
        for (int i = 0; i <= num_segments; i++)
        { // NUM_PIZZA_SLICES decides how round the circle looks.
            double angle = Math.PI * 2 * i / num_segments;
            GL11.glVertex2f((float) Math.cos(angle), (float) Math.sin(angle));
        }
        GL11.glEnd();
        GL11.glPopMatrix();
    }
}
