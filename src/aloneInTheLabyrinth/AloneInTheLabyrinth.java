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

};

public class AloneInTheLabyrinth extends BasicGameState {

    public Enemy numberone;
    
    public Orb powerOrb;
    
    public Player player;

    public itemwin grabtowin;

    public ArrayList<itemwin> stuffwin = new ArrayList();

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

        forestMap = new TiledMap("res/agivens_4_map.tmx");

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

        grabtowin = new itemwin(4400, 4400);
        stuffwin.add(grabtowin);
        
        powerOrb = new Orb((int)Player.x + 10, (int)Player.y - 10);

        numberone = new Enemy(300, 300);
        monster.add(numberone);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
            throws SlickException {

        camera.centerOn((int) Player.x, (int) Player.y);

        camera.drawMap();

        camera.translateGraphics();

        player.sprite.draw((int) Player.x, (int) Player.y);

        g.drawString("Health: " + Player.health, camera.cameraX + 10,
                camera.cameraY + 10);

        g.drawString("speed: " + (int) (Player.speed * 10), camera.cameraX + 10,
                camera.cameraY + 25);

        g.drawString("time passed: " + counter / 1000, camera.cameraX + 600, camera.cameraY);
		// moveenemies();

        if(powerOrb.isIsVisible()){
            powerOrb.orb.draw(powerOrb.getX(), powerOrb.getY());
        }
        
        for (itemwin w : stuffwin) {
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
            
        }

        Player.rect.setLocation(Player.getplayershitboxX(),
                Player.getplayershitboxY());

        for (itemwin w : stuffwin) {

            if (Player.rect.intersects(w.hitbox)) {
                if (w.isvisible) {
                    w.isvisible = false;
                    makevisible();
                    sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

                }

            }
        }
        for (Enemy m : monster) {

            if (Player.rect.intersects(m.ahitbox)) {
                if (m.isvisible) {

                    Player.health -= 1000;
                }

            }
        }

        Player.health = 1000;
        if (Player.health <= 0) {
            makevisible();
            sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }

    }

    public int getID() {

        return 1;

    }

    public void makevisible() {
        for (itemwin h : stuffwin) {

            h.isvisible = true;
        }
    }

    private boolean isBlocked(float tx, float ty) {

        int xBlock = (int) tx / SIZE;

        int yBlock = (int) ty / SIZE;

        return blocked.blocked[xBlock][yBlock];

    }

}
