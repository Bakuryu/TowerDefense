/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Math.CoordinateTranslator;
import Math.Point2D;
import java.awt.Point;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 *
 * @author Bakuryu
 */
public class GUI
{

    private Input input;
    private Point mouseSP;
    private Point2D mouseWP;
    private CoordinateTranslator corT;

    public GUI(CoordinateTranslator corT)
    {
        this.corT = corT;
        mouseSP = new Point();
        mouseWP = new Point2D();
    }

    public void update(GameContainer gc, int t)
    {
        input = gc.getInput();
        mouseSP = new Point(input.getMouseX(), input.getMouseY());
        mouseWP = corT.screenToWorld(mouseSP);
        System.out.println("MouseWP: " + mouseWP);

    }

    public void render(Graphics g)
    {
        Point2D tileInWorld = new Point2D(convertFromTileCord(convertToTileCord(mouseWP).x,convertToTileCord(mouseWP).y));
        Point tileInScreen = corT.worldToScreen(tileInWorld);
        g.drawRect((int) tileInScreen.getX(), (int) tileInScreen.getY(), 32, 32);
    }

    private Point convertToTileCord(Point2D p)
    {
        int tx = (int) (p.getX() / 2.5);
        int ty = (int) ((100 - p.getY()) / (100/35));

        Point tilePoint = new Point(tx, ty);

        return tilePoint;
    }

    private Point2D convertFromTileCord(int x, int y)
    {
        double wX = 0;
        if (x != 39)
        {
            wX = x * 2.5;
        }
        double wY = (100 - (y * (100 / 35)));

        Point2D worldPoint = new Point2D(wX, wY);
        return worldPoint;
    }
}
