package aloneInTheLabyrinth;

import static aloneInTheLabyrinth.AloneInTheLabyrinth.player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Lose extends BasicGameState {
    private StateBasedGame game;
    public Image startimage;
    public Lose(int xSize, int ySize) {

    }

    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
    	startimage = new Image("res/Lose_End_Screen.png");
        this.game = game;
    }

    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
    	startimage.draw();
        g.setColor(Color.white);
        g.drawString("press 1 to try again", 400, 320);
    }

    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {

    }

    public int getID() {
        return 2;
    }

    @Override

    public void keyReleased(int key, char c) {
        switch (key) {
            case Input.KEY_1:
                player.health  = 1000;
                player.speed = .4f;
                AloneInTheLabyrinth.counter = 0;
                PassItem.isvisible = true;
                player.x = 96f;
                player.y = 228f;
                game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                break;
            case Input.KEY_2:
                break;
            case Input.KEY_3:
                break;
            default:
                break;
        }
    }
}

