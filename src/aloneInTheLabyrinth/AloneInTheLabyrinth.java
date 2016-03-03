package aloneInTheLabyrinth;

import org.newdawn.slick.state.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import org.w3c.dom.css.Rect;

class blocked {

    public static boolean[][] blocked;
    public static boolean[][] getblocked() {
        return blocked;
    }
}

public class AloneInTheLabyrinth extends BasicGameState {

    public Enemy numberone;
    public Orb magic8ball;
    public Player player;
    public PassItem grabtopass;
    public ArrayList<PassItem> stuffpass = new ArrayList();
    public ArrayList<Enemy> monster = new ArrayList();
    private static TiledMap forestMap;
    private static AppGameContainer app;
    private static Camera camera;
    public static int counter = 0;
    private static final int SIZE = 64;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 750;
    public AloneInTheLabyrinth(int xSize, int ySize) {
    }

    public void init(GameContainer gc, StateBasedGame sbg)
            throws SlickException {

        gc.setTargetFrameRate(60);
        gc.setShowFPS(false);
        forestMap = new TiledMap("res/d4.tmx");
        camera = new Camera(gc, forestMap);

        blocked.blocked = new boolean[forestMap.getWidth()][forestMap.getHeight()];
        for (int xAxis = 0; xAxis < forestMap.getWidth(); xAxis++) {
            for (int yAxis = 0; yAxis < forestMap.getHeight(); yAxis++) {
                int tileID = forestMap.getTileId(xAxis, yAxis, 1);
                String value = forestMap.getTileProperty(tileID,
                        "blocked", "false");
                if ("true".equals(value)) {
                    blocked.blocked[xAxis][yAxis] = true;
                }
            }
        }
        grabtopass = new PassItem(4400, 4400);
        stuffpass.add(grabtopass);
        magic8ball = new Orb((int) player.x, (int) player.y);
        numberone = new Enemy((int) player.x + 150, (int) player.y + 150);
        monster.add(numberone);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) Player.x, (int) Player.y);
        camera.drawMap();
        camera.translateGraphics();
        player.sprite.draw((int) player.x, (int) player.y);
        g.drawString("Health: " + Player.health, camera.cameraX + 10,
                camera.cameraY + 10);
        g.drawString("Score: " + Player.score, camera.cameraX + 10,
                camera.cameraY + 25);
        g.drawString("Time Passed: " + counter / 1000, camera.cameraX + 600, camera.cameraY);
        if(magic8ball.isIsVisible()){
            magic8ball.orbimage.draw(magic8ball.getX(), magic8ball.getY());
        }
        for (PassItem w : stuffpass) {
            if (w.isvisible) {
                w.currentImage.draw(w.x, w.y);
            }
        }
        for (Enemy e : monster) {
            e.currentanime.draw(e.Bx, e.By);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
            throws SlickException {
        
        counter += delta;
        Input input = gc.getInput();
        float fdelta = delta * Player.speed;
        Player.setpdelta(fdelta);
        double rightlimit = (forestMap.getWidth() * SIZE) - (SIZE * 0.75);
        float projectedright = Player.x + fdelta + SIZE;
        boolean cangoright = projectedright < rightlimit;

        if (input.isKeyDown(Input.KEY_UP)) {
            player.sprite = player.proup;
            float fdsc = (float) (fdelta - (SIZE * .15));
            if (!(isBlocked(Player.x, Player.y - fdelta) || isBlocked((float) (Player.x + SIZE + 1.5), Player.y - fdelta))) {
                player.sprite.update(delta);
                Player.y -= fdelta;
            }
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            player.sprite = player.prodown;
            if (!isBlocked(Player.x, Player.y + SIZE + fdelta)
                    || !isBlocked(Player.x + SIZE - 1, Player.y + SIZE + fdelta)) {
                player.sprite.update(delta);
                Player.y += fdelta;
            }
        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            player.sprite = player.proleft;
            if (!(isBlocked(Player.x - fdelta, Player.y) || isBlocked(Player.x
                    - fdelta, Player.y + SIZE - 1))) {
                player.sprite.update(delta);
                Player.x -= fdelta;
            }
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            player.sprite = player.proright;
            if (cangoright
                    && (!(isBlocked(Player.x + SIZE + fdelta,
                            Player.y) || isBlocked(Player.x + SIZE + fdelta, Player.y
                            + SIZE - 1)))) {
                player.sprite.update(delta);
                Player.x += fdelta;

            }
        } else if (input.isKeyDown(Input.KEY_SPACE)) {
            magic8ball.setDirection(player.getDirection());
            magic8ball.settimeExists(100);
            magic8ball.setX((int) player.x);
            magic8ball.setY((int) player.y);
            magic8ball.hitbox.setX(magic8ball.getX());
            magic8ball.hitbox.setY(magic8ball.getY());
            magic8ball.setIsVisible(!magic8ball.isIsVisible());
        }

        Player.rect.setLocation(Player.getplayershitboxX(),
                Player.getplayershitboxY());

        for (PassItem w : stuffpass) {
            if (Player.rect.intersects(w.hitbox)) {
                if (w.isvisible) {
                    w.isvisible = false;
                    makevisible();
                    sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                }
            }
        }
        for (Enemy e : monster) {
            if (magic8ball.hitbox.intersects(e.rect)){
                e.isVisible = false;
            }
        }
        for (Enemy e : monster) {
            if (Player.rect.intersects(e.rect)){
                Player.health -= 500;
            }
        }
        if (magic8ball.isIsVisible()) {
            if (magic8ball.gettimeExists() > 0) {
                if (magic8ball.getDirection() == 0) {
                    magic8ball.setX((int) player.x);
                    magic8ball.setY(magic8ball.getY() - 5);
                } else if (magic8ball.getDirection() == 2) {
                    magic8ball.setX((int) player.x);
                    magic8ball.setY(magic8ball.getY() + 5);
                } else if (magic8ball.getDirection() == 3) {
                    magic8ball.setX(magic8ball.getX() - 5);
                    magic8ball.setY(magic8ball.getY());
                } else if (magic8ball.getDirection() == 1) {
                    magic8ball.setX(magic8ball.getX() + 5);
                    magic8ball.setY(magic8ball.getY());
                }
                magic8ball.hitbox.setX(magic8ball.getX());
                magic8ball.hitbox.setY(magic8ball.getY());
                magic8ball.countdown();
            } else {
                magic8ball.setIsVisible(false);
            }
        }
        Player.health = 1000;
        if (Player.health <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
        for (Enemy e : monster) {
            if (e.isVisible) {
                e.move();
            }
        }
    }
    
    public int getID() {
        return 1;
    }
    
    public void makevisible() {
        for (PassItem h : stuffpass) {
            h.isvisible = true;
        }
    }

    private boolean isBlocked(float tx, float ty) {
        int xBlock = (int) tx / SIZE;
        int yBlock = (int) ty / SIZE;
        return blocked.blocked[xBlock][yBlock];
    }
}
