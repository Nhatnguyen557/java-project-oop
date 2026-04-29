package entity;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;
import map.Map;

public class ghost extends Entity {

    private boolean scared;
    private String type; // "CHASE" hoặc "RANDOM"
    private Random rand;

    public ghost(int x, int y, Map map, String type) {
        super(x, y, map);
        this.type = type;
        this.scared = false;
        this.rand = new Random();

        // hướng ban đầu
        this.direction = rand.nextInt(4);
    }

    // UPDATE
    @Override
    public void update() {

        //đổi hướng
        if (rand.nextInt(10) == 0) {
            direction = rand.nextInt(4);
        }

        // behavior
        if (!scared && type.equals("CHASE")) {
            chaseSimple();
        }

        // di chuyển 
        move();
    }

    // CHASE 
    private void chaseSimple() {
        int newDir = rand.nextInt(4);

        if (newDir != direction) {
            direction = newDir;
        }
    }

    //  SCARED 
    public void setScared(boolean scared) {
        this.scared = scared;
    }

    // COLLISION 
    public boolean checkCollision(int px, int py) {
        if (this.x == px && this.y == py) {
            if (scared) {
                // reset về vị trí spawn
                this.x = 1;
                this.y = 1;
                return false;
            } 
            else {
                return true; // Pac-Man chết
            }
        }
        return false;
    }

    // RENDER 
    @Override
    public void render(Graphics g) {

        if (scared) {
            g.setColor(Color.BLUE);
        } 
        else {
            if (type.equals("CHASE")) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.PINK);
            }
        }

        g.fillOval(x * 32, y * 32, 28, 28);
    }
}