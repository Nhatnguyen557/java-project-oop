package entity;

import java.awt.Graphics;
import map.Map;

public abstract class Entity {
    protected int x, y;          // position
    protected int direction;     // current direction

    protected Map map;           // reference to map (for collision)

    // Direction constants
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public Entity(int x, int y, Map map) {
        this.x = x;
        this.y = y;
        this.map = map;
    }

    // ABSTRACT METHODS
    public abstract void update();              // logic
    public abstract void render(Graphics g);    // draw on screen

    // SHARED MOVEMENT
    protected void move() {
        int nextX = x;
        int nextY = y;

        switch (direction) {
            case UP:    nextY -= 1; break;
            case DOWN:  nextY += 1; break;
            case LEFT:  nextX -= 1; break;
            case RIGHT: nextX += 1; break;
        }

        if (map.canMove(nextX, nextY)) {
            x = nextX;
            y = nextY;
        }
    }

    // GETTERS and SETTER
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}