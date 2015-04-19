/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Graphics.AnimationManager;
import Math.Point2D;
import Math.Vector2D;
import java.util.LinkedList;
import java.util.Queue;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Bakuryu
 */
public class AgentEntity extends Entity
{

    private String enemyType;
    private AnimationManager animM;
    private Animation anim;
    private int speed;
    private int hp;
    private LinkedList<Point2D> path;
    private Point2D currentTargetP;

    /**
     * Create an AgentEntity at location (x,y).
     *
     * @param x Agent's starting x coordinate
     * @param y Agent's starting y coordinate
     * @param type
     * @throws SlickException
     */
    public AgentEntity(double x, double y, String type) throws SlickException
    {
        currentTargetP = new Point2D();
        position = new Point2D(x, y);
        enemyType = type;
        animM = new AnimationManager();
        anim = animM.setAnimation(type);
        path = new LinkedList<>();
        speed = 20;
        generatePath();

    }

    /**
     * Update Agent's current animation based on delta time, execute the current
     * state, determine animation based on targets location from Agent, and
     * check for collision.
     *
     * @param gc
     * @param t
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, int t) throws SlickException
    {
        System.out.println("Position: " + position);
        if (!path.isEmpty() && !(isAgentNear(this, path.getFirst())))
        {
            Vector2D dist = path.getFirst().minus(this.position);
            dist.normalize();

            double nX = position.getX() + dist.getX() * speed * t / 1000;
            double nY = position.getY() + dist.getY() * speed * t / 1000;
            Point2D newPos = new Point2D(nX, nY);
            position.set(newPos);
        }

        if (!path.isEmpty() && isAgentNear(this, path.getFirst()))
        {
            path.remove();
        }
    }

    public Animation getAnimation()
    {
        return anim;
    }

    private void generatePath()
    {
        path.add(new Point2D(72.5, 11.43));
        path.add(new Point2D(92, 11.43));
        path.add(new Point2D(92, 37.14));
        path.add(new Point2D(12.5,37.14));
        path.add(new Point2D(12.5,51.43));
        path.add(new Point2D(92,51.43));
        path.add(new Point2D(92,82.86));
        path.add(new Point2D(25,82.86));
        path.add(new Point2D(22.5,77));
        path.add(new Point2D(15,77));
    }

    public boolean isAgentNear(AgentEntity a, Point2D dest)
    {
        boolean closeX = false;
        boolean closeY = false;
        if (Math.abs(a.getPosition().getX() - dest.getX()) < 1)
        {
            closeX = true;
        }

        if (Math.abs(a.getPosition().getY() - dest.getY()) < 1)
        {
            closeY = true;
        }

        return (closeX && closeY);
    }

}
