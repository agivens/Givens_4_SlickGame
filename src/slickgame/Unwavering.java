package slickgame;

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

public class Unwavering extends BasicGameState {

	
        public Enemy numberone;
        
        public itemwin grabtowin;
	
	public ArrayList<itemwin> stuffwin = new ArrayList();
        
        public ArrayList<Enemy> monster = new ArrayList();

	private boolean[][] hostiles;

	private static TiledMap forestMap;

	private static AppGameContainer app;

	private static Camera camera;
	
	public static int counter = 0;

	// Player stuff

	private Animation sprite, proup, prodown, proleft, proright, prowait;

	/**
	 * 
	 * The collision map indicating which tiles block movement - generated based
	 * 
	 * on tile properties
	 */

	// changed to match size of sprites & map

	private static final int SIZE = 64;

	// screen width and height won't change

	private static final int SCREEN_WIDTH = 1000;

	private static final int SCREEN_HEIGHT = 750;

	public Unwavering(int xSize, int ySize) {

	}

	public void init(GameContainer gc, StateBasedGame sbg)

	throws SlickException {
		
		 gc.setTargetFrameRate(60);

		gc.setShowFPS(false);

		// *******************

		// Scenerey Stuff

		// ****************

		forestMap = new TiledMap("res/agivens_4_map.tmx");

		// Ongoing checks are useful

		System.out.println("Tile map is this wide: " + forestMap.getWidth());

		camera = new Camera(gc, forestMap);

		// *********************************************************************************

		// Player stuff --- these things should probably be chunked into methods
		// and classes

		// *********************************************************************************

		SpriteSheet proMoving = new SpriteSheet(
				"res/ProtagonistSpriteSheet.png",64, 64, 0);

		// System.out.println("Horizontal count: "
		// +runningSS.getHorizontalCount());

		// System.out.println("Vertical count: " +runningSS.getVerticalCount());

		proup = new Animation();

		proup.setAutoUpdate(true);

		proup.addFrame(proMoving.getSprite(0, 8), 330);

		proup.addFrame(proMoving.getSprite(1, 8), 330);

		proup.addFrame(proMoving.getSprite(2, 8), 330);

		proup.addFrame(proMoving.getSprite(3, 8), 330);

		proup.addFrame(proMoving.getSprite(4, 8), 330);

		proup.addFrame(proMoving.getSprite(5, 8), 330);

		proup.addFrame(proMoving.getSprite(6, 8), 330);

		proup.addFrame(proMoving.getSprite(7, 8), 330);

		proup.addFrame(proMoving.getSprite(8, 8), 330);

		prodown = new Animation();

		prodown.setAutoUpdate(false);

		prodown.addFrame(proMoving.getSprite(0, 10), 330);

		prodown.addFrame(proMoving.getSprite(1, 10), 330);

		prodown.addFrame(proMoving.getSprite(2, 10), 330);

		prodown.addFrame(proMoving.getSprite(3, 10), 330);

		prodown.addFrame(proMoving.getSprite(4, 10), 330);

		prodown.addFrame(proMoving.getSprite(5, 10), 330);

		prodown.addFrame(proMoving.getSprite(6, 10), 330);

		prodown.addFrame(proMoving.getSprite(7, 10), 330);

		prodown.addFrame(proMoving.getSprite(8, 10), 330);

		proleft = new Animation();

		proleft.setAutoUpdate(false);

		proleft.addFrame(proMoving.getSprite(0, 9), 330);

		proleft.addFrame(proMoving.getSprite(1, 9), 330);

		proleft.addFrame(proMoving.getSprite(2, 9), 330);

		proleft.addFrame(proMoving.getSprite(3, 9), 330);

		proleft.addFrame(proMoving.getSprite(4, 9), 330);

		proleft.addFrame(proMoving.getSprite(5, 9), 330);

		proleft.addFrame(proMoving.getSprite(6, 9), 330);

		proleft.addFrame(proMoving.getSprite(7, 9), 330);

		proleft.addFrame(proMoving.getSprite(8, 9), 330);

		proright = new Animation();

		proright.setAutoUpdate(false);

		proright.addFrame(proMoving.getSprite(0, 11), 330);

		proright.addFrame(proMoving.getSprite(1, 11), 330);

		proright.addFrame(proMoving.getSprite(2, 11), 330);

		proright.addFrame(proMoving.getSprite(3, 11), 330);

		proright.addFrame(proMoving.getSprite(4, 11), 330);

		proright.addFrame(proMoving.getSprite(5, 11), 330);

		proright.addFrame(proMoving.getSprite(6, 11), 330);

		proright.addFrame(proMoving.getSprite(7, 11), 330);

		proright.addFrame(proMoving.getSprite(8, 11), 330);

		prowait = new Animation();

		prowait.setAutoUpdate(true);

		prowait.addFrame(proMoving.getSprite(0, 14), 733);

		prowait.addFrame(proMoving.getSprite(1, 14), 733);

		prowait.addFrame(proMoving.getSprite(2, 14), 733);

		prowait.addFrame(proMoving.getSprite(3, 14), 733);

		// wait.addFrame(runningSS.getSprite(2, 14), 733);

		// wait.addFrame(runningSS.getSprite(5, 14), 333);

		sprite = prowait;

		// *****************************************************************

		// Obstacles etc.

		// build a collision map based on tile properties in the TileD map

		blocked.blocked = new boolean[forestMap.getWidth()][forestMap.getHeight()];

		// System.out.println("Map height:" + grassMap.getHeight());

		// System.out.println("Map width:" + grassMap.getWidth());

		// There can be more than 1 layer. You'll check whatever layer has the
		// obstacles.

		// You could also use this for planning traps, etc.

		// System.out.println("Number of tile layers: "
		// +grassMap.getLayerCount());

		System.out.println("The grassmap is " + forestMap.getWidth() + " by "
				+ forestMap.getHeight());

		for (int xAxis = 0; xAxis < forestMap.getWidth(); xAxis++) {

			for (int yAxis = 0; yAxis < forestMap.getHeight(); yAxis++) {

				// int tileID = grassMap.getTileId(xAxis, yAxis, 0);

				// Why was this changed?

				// It's a Different Layer.

				// You should read the TMX file. It's xml, i.e.,human-readable
				// for a reason

				int tileID = forestMap.getTileId(xAxis, yAxis, 1);

				String value = forestMap.getTileProperty(tileID,

				"blocked", "false");

				if ("true".equals(value)) {

					System.out.println("The tile at x axis " + xAxis + " and y axis "
							+ yAxis + " is blocked.");

					blocked.blocked[xAxis][yAxis] = true;

				}

			}

		}

		System.out.println("Array length " + blocked.blocked[0].length);

		// A remarkably similar process for finding hostiles

		hostiles = new boolean[forestMap.getWidth()][forestMap.getHeight()];
               
                
                grabtowin = new itemwin(4400,4400);
		stuffwin.add(grabtowin);
                
                numberone = new Enemy(300,300);
                monster.add(numberone);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)

	throws SlickException {

		camera.centerOn((int) Player.x, (int) Player.y);

		camera.drawMap();

		camera.translateGraphics();

		// it helps to add status reports to see what's going on

		// but it gets old quickly

		// System.out.println("Current X: " +player.x + " \n Current Y: "+ y);

		sprite.draw((int) Player.x, (int) Player.y);
		
		//g.drawString("x: " + (int)player.x + "y: " +(int)player.y , player.x, player.y - 10);

		g.drawString("Health: " + Player.health, camera.cameraX + 10,
				camera.cameraY + 10);
		
		g.drawString("speed: " + (int)(Player.speed *10), camera.cameraX + 10,
				camera.cameraY + 25);

		//g.draw(player.rect);

		g.drawString("time passed: " +counter/1000, camera.cameraX +600,camera.cameraY );
		// moveenemies();
		
		for (itemwin w: stuffwin) {
			if (w.isvisible) {
				w.currentImage.draw(w.x, w.y);
				// draw the hitbox
				//g.draw(w.hitbox);

			}
		}
                
                for (Enemy e : monster) {

                //System.out.println("The current selection is: " +e.currentanime);

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

		// System.out.println("Right limit: " + rightlimit);

		float projectedright = Player.x + fdelta + SIZE;

		boolean cangoright = projectedright < rightlimit;

		// there are two types of fixes. A kludge and a hack. This is a kludge.

		if (input.isKeyDown(Input.KEY_UP)) {

			sprite = proup;

			float fdsc = (float) (fdelta - (SIZE * .15));

			if (!(isBlocked(Player.x, Player.y - fdelta) || isBlocked((float) (Player.x + SIZE + 1.5), Player.y - fdelta))) {

				sprite.update(delta);

				// The lower the delta the slower the sprite will animate.

				Player.y -= fdelta;

			}

		} else if (input.isKeyDown(Input.KEY_DOWN)) {

			sprite = prodown;

			if (!isBlocked(Player.x, Player.y + SIZE + fdelta)

			|| !isBlocked(Player.x + SIZE - 1, Player.y + SIZE + fdelta)) {

				sprite.update(delta);

				Player.y += fdelta;

			}

		} else if (input.isKeyDown(Input.KEY_LEFT)) {

			sprite = proleft;

			if (!(isBlocked(Player.x - fdelta, Player.y) || isBlocked(Player.x

			- fdelta, Player.y + SIZE - 1))) {

				sprite.update(delta);

				Player.x -= fdelta;

			}

		} else if (input.isKeyDown(Input.KEY_RIGHT)) {

			sprite = proright;

			// the boolean-kludge-implementation

			if (cangoright
					&& (!(isBlocked(Player.x + SIZE + fdelta,

					Player.y) || isBlocked(Player.x + SIZE + fdelta, Player.y
							+ SIZE - 1)))) {

				sprite.update(delta);

				Player.x += fdelta;

			} // else { System.out.println("Right limit reached: " +
				// rightlimit);}

		}

		Player.rect.setLocation(Player.getplayershitboxX(),
				Player.getplayershitboxY());

		
		for (itemwin w : stuffwin) {

			if (Player.rect.intersects(w.hitbox)) {
				//System.out.println("yay");
				if (w.isvisible) {
					w.isvisible = false;
					makevisible();
					sbg.enterState(3, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
					
				}

			}
		}
                for (Enemy m : monster) {

			if (Player.rect.intersects(m.ahitbox)) {
				//System.out.println("yay");
				if (m.isvisible) {

					Player.health -= 1000;
				}

			}
		}
		 
		Player.health = 1000;
		if(Player.health <= 0){
			makevisible();
			sbg.enterState(2, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}

	}

	public int getID() {

		return 1;

	}

	public void makevisible(){
		for (itemwin h : stuffwin) {
			
		h.isvisible = true;}
        }
                
	
	private boolean isBlocked(float tx, float ty) {

		int xBlock = (int) tx / SIZE;

		int yBlock = (int) ty / SIZE;

		return blocked.blocked[xBlock][yBlock];

		// this could make a better kludge

	}

}
