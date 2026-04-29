package entity;
public class Food {
    private int row;
    private int col;
    private boolean isPowerFood;
    private boolean eaten;

    public Food(int row, int col, boolean isPowerFood) {
        this.row = row;
        this.col = col;
        this.isPowerFood = isPowerFood;
        this.eaten = false;
    }

    public void eat(ScoreManager scoreManager) {
        if (!eaten) {
            eaten = true;

            if (isPowerFood) {
                scoreManager.addScore(50);
                System.out.println("Power Food eaten! Ghost scared mode activated!");
            } else {
                scoreManager.addScore(10);
            }
        }
    }

    public boolean isAtPosition(int r, int c) {
        return row == r && col == c;
    }

    public boolean isEaten() {
        return eaten;
    }

    public boolean isPowerFood() {
        return isPowerFood;
    }
}