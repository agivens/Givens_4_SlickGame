package aloneInTheLabyrinth;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Orb {
    private int x, y, width, height;
    private int dmg, hitboxX, hitboxY;
    private boolean isVisible;
    public Image orbpic;
    Shape hitbox; 
    private int timeExists;
    private int direction; 
    
    public Orb(int a,int b) throws SlickException{
        this.x = a;
        this.y = b;
        this.isVisible = false;
        this.orbpic = new Image ("res/antidote.png");
        this.hitbox = new Rectangle (a, b, 32, 32); 
        this.timeExists = 0;
    }

    
    public void settimeExists(int t){
        this.timeExists = t;
    }
    
    public int gettimeExists(){
        
        return this.timeExists;
    }
    
    public void countdown(){
        this.timeExists--;
    }
    
    public int getX() {
        return x;
    }
    

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getHitboxX() {
        return hitboxX;
    }

    public void setHitboxX(int hitboxX) {
        this.hitboxX = hitboxX;
    }

    public int getHitboxY() {
        return hitboxY;
    }

    public void setHitboxY(int hitboxY) {
        this.hitboxY = hitboxY;
    }

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Image getOrbpic() {
        return orbpic;
    }

    public void setOrbpic(Image orbpic) {
        this.orbpic = orbpic;
    }

    public Shape getHitbox() {
        return hitbox;
    }

    public void setHitbox(Shape hitbox) {
        this.hitbox = hitbox;
    }
    public void setLocation(int a, int b, Player player) {
        this.setX((int)player.x);
        this.setY((int)player.y);
        this.setHitboxX((int)player.x + 5);
        this.setHitboxY((int)player.y - 10);
    }

    /**
     * Getters and setters are a common concept in Java.
     * A design guideline in Java, and object oriented
     * programming in general, is to encapsulate/isolate
     * values as much as possible. 
     * Getters - are methods used to query the value of 
     * instance variables.
     * this.getLocationX();
     * Setters - methods that set values for instance 
     * variables. 
     * orb1.setLocation(Player.x, Player.y);
     */
    public void setDirection(int i){
        this.direction = i;
    }
    public int getDirection(){
        return this.direction;
    }
}