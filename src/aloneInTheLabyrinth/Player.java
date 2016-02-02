/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aloneInTheLabyrinth;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author agivens
 */
public class Player {

    public static float x = 96f;

    public static float y = 228f;

    public static int health = 100000;

    public static float speed = .4f;

    static float hitboxX = x + 8f;

    static float hitboxY = y + 8f;

    private static int startX, startY, width = 30, height = 42;

    public static Shape rect = new Rectangle(getplayershitboxX(),
            getplayershitboxY(), width, height);

    public static float pdelta;

    public static Animation playeranime;
    
    Animation sprite, proup, prodown, proleft, proright, prowait;
    
    Player() throws SlickException{
        SpriteSheet proMoving = new SpriteSheet(
                "res/ProtagonistSpriteSheet.png", 64, 64, 0);
        
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

        sprite = prowait;
    }

    public static void setpdelta(float somenum) {

        pdelta = somenum;

    }

    public static float getpdelta() {

        return pdelta;

    }

    public static float getplayersX() {

        return x;

    }

    public static float getplayersY() {

        return y;

    }

    public static float getplayershitboxX() {

        return x + 18f;

    }

    public static float getplayershitboxY() {

        return y + 18f;

    }

    public static void setplayershitboxX() {

        hitboxX = getplayershitboxX();

    }

    public static void setplayershitboxY() {

        hitboxY = getplayershitboxY();

    }
}
