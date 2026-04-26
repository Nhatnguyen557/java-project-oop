package entity;

import java.util.Random;

public class Ghost {
    private int x, y;
    private int dx, dy;
    private boolean scared;
    private String type; // CHASE hoặc RANDOM

    private Random rand = new Random();

    public Ghost(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.dx = 0;
        this.dy = 0;
        this.scared = false;
    }

    // Move
    public void move(Object map, Object pacman) {
        // Random đổi hướng
        if (rand.nextInt(10) == 0) {
            randomDirection();
        }

        // Behavior theo loại ghost
        if(!scared) {
            if (type.equals("Chase")) {
               chasePacman(pacman);
            }
        }

        // di chuyển không đụng tường
        int nextX = x + dx;
        int nextY = y + dy;

        if (map.canMove(nextX, nextY)) {
            x = nextX;
            y = nextY;
        } 
        else {
            randomDirection(); // đụng tường thì đổi hướng đi
        }
    }

    // Random move
    private void randomDirection() {
        int dir = rand.nextInt(4);

        switch (dir) {
            case 0: dx = 1; dy = 0; break; // phải
            case 1: dx = -1; dy = 0; break; // trái
            case 2: dx = 0; dy = 1; break; // xuống
            case 3: dx = 0; dy = -1; break; // lên
        }
    }

    // Chase PacMan
    private void chasePacman(PacMan pacman) {
        int px = pacman.getX();
        int py = pacman.getY();

        // chọn hướng gần nhất
        if (Math.abs(px - x) > Math.abs(py - y)) {
            dx = (px > x) ? 1 : -1;
            dy = 0;
        }
        else {
            dy = (py > y) ? 1 : -1;
            dx = 0;
        }
    }

    // Scared
    public void setScared(boolean scared) {
        this.scared = scared;
    }

    // Collision
    public boolean checkCollision(PacMan pacman) {
        if (this.x == pacman.getX() && this.y == pacman.getY()) {
            if (scared) {
                this.x = 1;
                this.y = 1;
                return false; // ghost bị ăn, reset về vị trí ban đầu
            }
            else {
                return true; // PacMan chết
            }
        }
        return false; // không va chạm
    }

     // getter
    public int getX() { return x; }
    public int getY() { return y; }
}