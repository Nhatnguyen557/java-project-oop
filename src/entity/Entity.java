package entity;

import java.awt.Graphics;

public abstract class Entity {
    protected int x,y,speed;
    protected int direction;

    public abstract void update();
    public abstract void draw(Graphics g);

}
