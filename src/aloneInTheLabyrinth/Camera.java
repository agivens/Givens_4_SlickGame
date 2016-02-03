package aloneInTheLabyrinth;


import org.newdawn.slick.GameContainer;

import org.newdawn.slick.geom.Shape;

import org.newdawn.slick.tiled.TiledMap;


public class Camera {



   protected TiledMap map;

  


   protected int numTilesX;



   protected int numTilesY;

  


   protected int mapHeight;

  


   protected int mapWidth;

  


   protected int tileWidth;

  


   protected int tileHeight;

  


   protected GameContainer gc;



   protected float cameraX;

  


   protected float cameraY;

  


   public Camera(GameContainer gc, TiledMap map) {

      this.map = map;

     

      this.numTilesX = map.getWidth();

      this.numTilesY = map.getHeight();

     

      this.tileWidth = map.getTileWidth();

      this.tileHeight = map.getTileHeight();

     

      this.mapHeight = this.numTilesX * this.tileWidth;

      this.mapWidth = this.numTilesY * this.tileHeight;

     

      this.gc = gc;

   }

  


   public void centerOn(float x, float y) {


      cameraX = x - gc.getWidth()  / 2;

      cameraY = y - gc.getHeight() / 2;

     


      if(cameraX < 0) cameraX = 0;

      if(cameraX + gc.getWidth() > mapWidth) cameraX = mapWidth -

gc.getWidth();

     


      if(cameraY < 0) cameraY = 0;

      if(cameraY + gc.getHeight() > mapHeight) cameraY = mapHeight -

gc.getHeight();

   }


   public void centerOn(float x, float y, float height, float width) {

      this.centerOn(x + width / 2, y + height / 2);

   }



   public void centerOn(Shape shape) {

      this.centerOn(shape.getCenterX(), shape.getCenterY());

   }

  


   public void drawMap() {

      this.drawMap(0, 0);

   }

  


  

   public void drawMap(int offsetX, int offsetY) {


       int tileOffsetX = (int) - (cameraX % tileWidth);

       int tileOffsetY = (int) - (cameraY % tileHeight);

     


       int tileIndexX = (int) (cameraX / tileWidth);

       int tileIndexY = (int) (cameraY / tileHeight);

     


       map.render(  

             tileOffsetX + offsetX,

             tileOffsetY + offsetY,

             tileIndexX,

             tileIndexY,

                (gc.getWidth()  - tileOffsetX) / tileWidth  + 1,

                (gc.getHeight() - tileOffsetY) / tileHeight + 1);

   }

  

   public void translateGraphics() {

      gc.getGraphics().translate(-cameraX, -cameraY);

   }


   public void untranslateGraphics() {

      gc.getGraphics().translate(cameraX, cameraY);

   }

  

}


