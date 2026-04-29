package map;

public class scoreManager {
    private int score;
    private int totalFood;
    private int eatenFood;

    public scoreManager(int totalFood) {
        this.score = 0;
        this.totalFood = totalFood;
        this.eatenFood = 0;
    }

    public void addScore(int points) {
        score += points;
        eatenFood++;
    }

    public int getScore() {
        return score;
    }

    public boolean hasWon() {
        return eatenFood >= totalFood;
    }

    public int getRemainingFood() {
        return totalFood - eatenFood;
    }
}