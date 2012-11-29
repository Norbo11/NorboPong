package com.github.norbo11.norbopong.util;
import org.lwjgl.opengl.GL11;

//import static org.lwjgl.opengl.GL11.*;

public class DrawingHelper
{    
    public static void drawCircle(float cx, float cy, float r, int num_segments) 
    {   
        GL11.glPushMatrix();
        GL11.glTranslatef(cx + r, cy + r, 0);
        GL11.glScalef(r, r, 1);

        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2f(0, 0);
        for(int i = 0; i <= num_segments; i++){ //NUM_PIZZA_SLICES decides how round the circle looks.
            double angle = Math.PI * 2 * i / num_segments;
            GL11.glVertex2f((float)Math.cos(angle), (float)Math.sin(angle));
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        
    }
}
