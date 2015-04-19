package Graphics;

import Math.CoordinateTranslator;
import Math.Point2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author coleman
 */
public class GameMap
{

    private final TiledMap map;
    private boolean blocked[][];
    private ArrayList<Rectangle> blocks;
    private final int mapWidth;
    private final int mapHeight;
    private final int numTilesX;
    private final int numTilesY;
    private final int tileWidth;
    private final int tileHeight;

    private final int screenWidth;
    private final int screenHeight;

    private final CoordinateTranslator trans;

    public GameMap(String filename, double worldWidth, double worldHeight,
            int screenWidth, int screenHeight)
            throws SlickException
    {
        map = new TiledMap("res/"+filename);


        tileWidth = map.getTileWidth();
        tileHeight = map.getTileHeight();
        blocks = new ArrayList<>();
        mapWidth = tileWidth * map.getWidth();
        mapHeight = tileHeight * map.getHeight();

        numTilesX = map.getWidth();
        numTilesY = map.getHeight();

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        trans = new CoordinateTranslator(mapWidth, mapHeight, worldWidth, worldHeight, new Point2D());
        createColliders();
    }

    /**
     * Plot the map using the graphics context.
     *
     * @param g the graphics context to use. Assumed to be clipped so that
     * plotting to 0,0 is correct.
     * @param ll the world coordinates of the lower left corner
     */
    public void render(Graphics g, Point2D ll)
    {

        Point plot = trans.worldToScreen(ll);

        int x = plot.x;
        int y = plot.y - screenHeight;

        int tx = x % tileWidth;
        int ty = y % tileHeight;

        int plotX = -1 * tx;
        int plotY = -1 * ty;

        int sx = x / tileWidth;
        int sy = y / tileHeight;

        int dsx = (int) screenWidth / tileWidth + 1;
        int dsy = (int) screenHeight / tileHeight + 2;

        map.render(plotX, plotY, sx, sy, dsx, dsy);

    }

    //Figure out which tiles are collidable and store them in an array to for the player to check against.
    public void createColliders()
    {
        // This will create an Array with all the Tiles in your map. When set to true, it means that Tile is blocked.
        blocked = new boolean[numTilesX][numTilesY];

        // Loop through the Tiles and read their Properties
        // Set here the Layer you want to Read. In your case, it'll be layer 1,
        // since the objects are on the second layer.
        int layer = map.getLayerIndex("Collision");

        for (int j = 0; j < numTilesY; j++)
        {
            for (int i = 0; i < numTilesX; i++)
            {

                // Read a Tile
                int tileID = map.getTileId(i, j, layer);

                String value = map.getTileProperty(tileID, "blocked", "false");

                // If the value of the Property is "true"...
                if (value.equals("true"))
                {

                    // We set that index of the TileMap as blocked
                    blocked[i][j] = true;

                    // And create the collision Rectangle
                    Point2D wP = (convertFromTileCord(i, j));
                    blocks.add(new Rectangle((int) wP.getX(), (int) wP.getY(), tileWidth, tileHeight));
                }

            }
        }
    }
    /* Returns list of blocks on the map that are collidable*/



    public ArrayList<Rectangle> getRect()
    {
        return blocks;
    }

    public boolean[][] getBlocked()
    {
        return blocked;
    }

    public TiledMap getMap()
    {
        return map;
    }

    private Point2D convertFromTileCord(int x, int y)
    {
        double wX = 0;
        if (x != 99)
        {
            wX = x * 3;
        }
        double wY = (300 - (y * 3));

        Point2D worldPoint = new Point2D(wX, wY);
        return worldPoint;
    }
}
