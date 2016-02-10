package aloneInTheLabyrinth;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Orb {
    private int x, y, width, height;
    private int dmg, hitboxX, hitboxY;
    private boolean isVisible;
    Image orbimage;
    Shape hitbox;
    private int timeExists;
    private int direction;

    public Orb(int a, int b) throws SlickException{
        this.x = a;
        this.y = b;
        this.isVisible = false;
        this.orbimage = new Image ("res/Orbs/TestSet_7.png");
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
    
    public void setDirection(int i){
        this.direction = i;
    }
    
    public int getDirection(){
        return this.direction;
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

    public boolean isIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Image getOrb() {
        return orbimage;
    }

    public void setOrb(Image orb) {
        this.orbimage = orb;
    }

    public Shape getHitbox() {
        return hitbox;
    }

    public void setHitbox(Shape hitbox) {
        this.hitbox = hitbox;
    }
    
     /*
    Getters and setters are a common concept in Java.
    A design guideline in Java, and object oriented
    programming in general, is to encapsulate/isolate
    values as much as possible.
    Getters- are methods used to query the value of instance variables
    this.getLocationX();
    Setters- methods that set the values for instance variables
    orb1.setLocation(Player.x, Player.y);
    */

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}
