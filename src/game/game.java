package game;

import map.Map;
import entity.Ghost;
import entity.Player;
import java.awt.Graphics;
import map.ScoreManager;
import java.util.ArrayList;


public class Game implements Runnable {
    
    // Thread
    private Thread gameThread;
    private boolean running;

    private ScoreManager scoreManager;

    //gamestate
    private GameState currentState;

    //core object
    private Map map;
    private Player player;
    private ArrayList<Ghost> ghost;

    public Game(){
        init();
    }

    private void init(){
        player = new Player();
        ghost = new ArrayList<>();
        map = new Map();

        currentState = GameState.MENU;
    }

    public synchronized void start(){
        if ( running ) return ;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Game loop
    @Override
    public void run(){
        while (running){
            update();
            // repaint(); // gọi từ GamePanel để vẽ lại UI

            try{
                Thread.sleep(16); // giới hạn khung hình 60/s
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    //update logic game
    private void update(){
        if ( currentState == GameState.PLAYING){
            player.update();// pacman move

            for ( Ghost g : ghost){
                g.update();
            }
            checkCollisions(); // check food and wall
        }
    }

    public void render(Graphics g ){
        map.render(g);
        player.render(g);

        for (Ghost g : ghost){
            g.render(g);
        }
    }

    // check collisions and eat food
    private void checkCollisions(){
        //TODO
        // Duyệt danh sách thức ăn từ Map
        //
        //
        // check win ( if eat all food )

        //
        //
    }
    
    public Player getPlayer(){
        return player;
    }

    public Map getMap(){
        return map;
    }

    public ArrayList<Ghost> getGhost(){
        return ghost;
    }

    public void setState(GameState state){
        this.currentState = state;
    }

    public GameState getState(){
        return currentState;
    }


}
