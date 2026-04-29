package entity;

import java.awt.Graphics;       // for rendering
import java.awt.event.KeyEvent; // for input handling
import map.Map;                 // for using map methods 

public class Player extends Entity {

    private int score;
    private int nextDirection; // buffer for smooth turning

    public Player(int x, int y, Map map) {
        super(x, y, map);
        this.direction = LEFT;      // default
        this.nextDirection = LEFT;  // default
        this.score = 0;
    }

    // INPUT HANDLING
    public void handleInput(int key) {
        if (key == KeyEvent.VK_W) nextDirection = UP;
        if (key == KeyEvent.VK_S) nextDirection = DOWN;
        if (key == KeyEvent.VK_A) nextDirection = LEFT;
        if (key == KeyEvent.VK_D) nextDirection = RIGHT;
    }

    // MAIN UPDATE
    @Override
    public void update() {
        // Try to change direction first (smooth turning)
        if (canMove(nextDirection)) { // this method belongs to class player not map
            direction = nextDirection;
        }

        // Move using current direction
        move();

        // Eat food
        eatFood();
    }

    // CHECK IF CAN MOVE IN A DIRECTION
    private boolean canMove(int dir) {
        int nextX = x;
        int nextY = y;

        switch (dir) {
            case UP:    nextY -= 1; break;
            case DOWN:  nextY += 1; break;
            case LEFT:  nextX -= 1; break;
            case RIGHT: nextX += 1; break;
        }

        return map.canMove(nextX, nextY);
    }

    // FOOD LOGIC
    private void eatFood() {
        if (map.eatFood(x, y)) {
            score += 10;
        }

        /* ****POWER FOOD LOGIC (BONUS)****
        if (map.hasPower(x, y)) {
            map.eatFood(x, y);
            score += 50;

            // **** MISSING: GHOST SCARE MODE ACTIVATION ****
        }
            */
    }

    // RENDER
    @Override
    public void render(Graphics g) {
        // TEMP: draw simple circle (replace with image later)
        // g.fillOval(x, y, width, height);

        // error bc missing grid size
        g.fillOval(x * map.getTileSize(), y * map.getTileSize(), map.getTileSize(), map.getTileSize());
    }

    // GETTER
    public int getScore() {
        return score;
    }
}
