import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private GameMap map;
    private PacMan pacman;
    private List<Ghost> ghosts;

    private int score;
    private GameState state;

    private int scaredTimer; // controls how long ghosts stay scared

    public GameEngine() {
        initGame();
    }

    // 🟢 Initialize everything
    private void initGame() {
        map = new GameMap();
        pacman = new PacMan(1, 1);

        ghosts = new ArrayList<>();
        ghosts.add(new Ghost(3, 3));

        score = 0;
        state = GameState.PLAYING;
        scaredTimer = 0;
    }

    // 🔄 Main update loop
    public void update() {
        if (state != GameState.PLAYING) return;

        // 1. Move player
        pacman.move(map);

        // 2. Move ghosts
        for (Ghost ghost : ghosts) {
            ghost.move(map);
        }

        // 3. Handle food
        handleFood();

        // 4. Handle collisions
        handleCollisions();

        // 5. Update scared mode timer
        updateScaredMode();

        // 6. Check win condition
        checkWin();
    }

    // 🟡 Food system
    private void handleFood() {
        int r = pacman.getRow();
        int c = pacman.getCol();

        int cell = map.getCell(r, c);

        if (cell == Constants.FOOD) {
            score += 10;
            map.setCell(r, c, Constants.EMPTY);
        }

        else if (cell == Constants.POWER) {
            score += 50;
            map.setCell(r, c, Constants.EMPTY);

            activateScaredMode();
        }
    }

    // 🟡 Activate scared mode
    private void activateScaredMode() {
        scaredTimer = 10; // lasts 10 updates

        for (Ghost ghost : ghosts) {
            ghost.setScared(true);
        }
    }

    // 🟡 Update scared mode
    private void updateScaredMode() {
        if (scaredTimer > 0) {
            scaredTimer--;

            if (scaredTimer == 0) {
                for (Ghost ghost : ghosts) {
                    ghost.setScared(false);
                }
            }
        }
    }

    // 🟡 Collision system
    private void handleCollisions() {
        for (Ghost ghost : ghosts) {

            if (ghost.getRow() == pacman.getRow() &&
                ghost.getCol() == pacman.getCol()) {

                if (ghost.isScared()) {
                    score += 100;

                    // reset ghost position (simple version)
                    resetGhost(ghost);
                } else {
                    state = GameState.GAME_OVER;
                }
            }
        }
    }

    private void resetGhost(Ghost ghost) {
        ghost.setScared(false);
        // simple respawn position
        // (you can improve this later)
    }

    // 🟡 Win condition
    private void checkWin() {
        int[][] grid = map.getMap();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == Constants.FOOD ||
                    grid[i][j] == Constants.POWER) {
                    return; // still food left
                }
            }
        }

        state = GameState.WIN;
    }

    // 🟡 Getters
    public int getScore() {
        return score;
    }

    public GameState getState() {
        return state;
    }

    public PacMan getPacman() {
        return pacman;
    }

    public List<Ghost> getGhosts() {
        return ghosts;
    }

    public GameMap getMap() {
        return map;
    }
}