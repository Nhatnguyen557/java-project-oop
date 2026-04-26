import java.util.ArrayList;
import java.util.List;

public class Map {

    private int rows;
    private int cols;
    private int tileSize = 32;

    private char[][] grid;

    private int pacmanRow, pacmanCol;
    private List<int[]> ghostSpawns = new ArrayList<>();

    private String[] tileMap = {
        "XXXXXXXXXXXXXXXXXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X                 X",
        "X XX X XXXXX X XX X",
        "X    X       X    X",
        "XXXX XXXX XXXX XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXrXX X XXXX",
        "O       bpo       O",
        "XXXX X XXXXX X XXXX",
        "OOOX X       X XOOO",
        "XXXX X XXXXX X XXXX",
        "X        X        X",
        "X XX XXX X XXX XX X",
        "X  X     P     X  X",
        "XX X X XXXXX X X XX",
        "X    X   X   X    X",
        "X XXXXXX X XXXXXX X",
        "X                 X",
        "XXXXXXXXXXXXXXXXXXX"
    };

    public Map() {
        loadMap();
    }

    private void loadMap() {
        rows = tileMap.length;
        cols = tileMap[0].length();

        grid = new char[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                char tile = tileMap[r].charAt(c);
                grid[r][c] = tile;

                // detect Pac-Man spawn
                if (tile == 'P') {
                    pacmanRow = r;
                    pacmanCol = c;
                }

                // detect ghosts
                if (tile == 'r' || tile == 'b' || tile == 'p' || tile == 'o') {
                    ghostSpawns.add(new int[]{r, c});
                }
            }
        }
    }

    public boolean canMove(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }

        return grid[row][col] != 'X';
    }

    public boolean eatFood(int row, int col) {
        if (grid[row][col] == ' ') {
            grid[row][col] = '.'; // mark as eaten
            return true;
        }
        return false;
    }
 
    public char getTile(int row, int col) {
        return grid[row][col];
    }

    public int getPacmanRow() { return pacmanRow; }
    public int getPacmanCol() { return pacmanCol; }

    public List<int[]> getGhostSpawns() {
        return ghostSpawns;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }

    public int getTileSize() { return tileSize; }
