/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import Components.Collider;
import Graphics.AnimationManager;
import Math.Point2D;
import Math.Vector2D;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Bakuryu
 */
public class AgentEntity extends Entity
{

    /* Current state of Agent (defaults to AgentStateChase)*/
    private EntityState aCurState;

    /* Animation manager to determine and return the correct animation based on movement and state*/
    private AnimationManager animM;

    /* Agent's current animation*/
    private Animation aAnim;


    /* Vector2D variable for storing the distance between Agent and its target*/
    private Vector2D toTarget;

    /* Agent's movement state used to determine which animations to use*/
    private String aMState;

    /* Agent's collider used to detect collision*/
    private Collider col;

    /* Point2D variable used to store Agent's starting position*/
    private Point2D startPos;


    /* Double variablue used to store the Agent's speed*/
    private double aSpeed;


    /* Boolean variable use to determine if Agent is visible or not (Also effect collision!)*/
    private boolean visible;

    private NavGraph graph;
    private GameMap gMap;

    private Point2D feetPos;


    private PathFinder pathF;
    private Point2D target;

    /**
     * Create an AgentEntity at location (x,y), give it a player to target, and
     * set it's max hunger and stamina.
     *
     * @param x Agent's starting x coordinate
     * @param y Agent's starting y coordinate
     * @param p Reference to player (for targeting)
     * @param maxHung Agent's max hunger level
     * @param maxStam Agent's max stamina
     * @param gMap
     * @throws SlickException
     */
    public AgentEntity(double x, double y, double speed,GameMap gMap) throws SlickException
    {
        visible = true;
        aMState = "D";
        feetPos = new Point2D(x + 6.875, y + 12.125);
        startPos = new Point2D(feetPos.getX() - 6.875, feetPos.getY() - 12.125);
        position = new Point2D(feetPos.getX() - 6.875, feetPos.getY() - 12.125);
        this.gMap = gMap;
        graph = new NavGraph(gMap);
        pathF = new PathFinder(graph);
        aCurState = new AgentStateWander(this,p);//AgentStateWander(this, p);
        animM = new AnimationManager();
        toTarget = new Vector2D(0, 0);
        aAnim = animM.setAnimation(this);
        col = new Collider(position, 75, 72);
        aSpeed = speed;

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
        aAnim.update(t);
        
        position = new Point2D(feetPos.getX() - 6.875, feetPos.getY() + 12.125);
        if (aCurState != null)
        {
            aCurState.Execute(this, t);
        }

        //animM.doAnimation()
        if (aCurState instanceof AgentStateChase)
        {
            target = player.getPosition();
        }
        
        if(aCurState instanceof AgentStateEat)
        {
            target = foodLoc;
        }
        
        if(aCurState instanceof AgentStateSleep)
        {
            target = homeLoc;
        }
        
        if(aCurState instanceof AgentStateWander)
        {
            target = getWanderTarget((AgentStateWander) aCurState);
        }
        
            /* Boolean variables to determine where player is located compared to Agent*/
            boolean U = ((int) target.getX() == 0 && (int) getTargetDistDir(target).getY() > 0);
            // System.out.println("UP?: " + U);
            boolean D = ((int) target.getX() == 0 && (int) getTargetDistDir(target).getY() < 0);
            //System.out.println("Down?: " + D);
            boolean L = ((int) getTargetDistDir(target).getX() < 0 && (int) getTargetDistDir(target).getY() < 20 && getTargetDistDir(target).getY() > -20);
            //System.out.println("Left?: " + L);
            boolean R = ((int) getTargetDistDir(target).getX() > 0 && (int) getTargetDistDir(target).getY() < 20 && getTargetDistDir(target).getY() > -20);
            //System.out.println("Right?: " + R);
            boolean UL = ((int) getTargetDistDir(target).getX() < 0 && (int) getTargetDistDir(target).getY() > 20);
            //System.out.println("UL?: " + UL);
            boolean UR = ((int) getTargetDistDir(target).getX() > 0 && (int) getTargetDistDir(target).getY() > 20);
            //System.out.println("UR?: " + UR);
            boolean DL = ((int) getTargetDistDir(target).getX() < 0 && (int) getTargetDistDir(target).getY() < -20);
            //System.out.println("DL?: " + DL);
            boolean DR = ((int) getTargetDistDir(target).getX() > 0 && (int) getTargetDistDir(target).getY() < -20);
            // System.out.println("DR?: " + DR);

            /*Straight up*/
            if (U && !UL && !UR && !L && !D && !R && !DR && !DL)
            {
                if (aMState != "U")
                {
                    aMState = "U";
                    setAnimation();
                }
            }

            /*Up Left*/
            if (UL && !U && !UR && !L && !D && !R && !DR && !DL)
            {
                if (aMState != "UL")
                {
                    aMState = "UL";
                    setAnimation();
                }
            }

            /*Up Right*/
            if (UR && !U && !UL && !L && !D && !R && !DR && !DL)
            {
                if (aMState != "UR")
                {
                    aMState = "UR";
                    setAnimation();
                }
            }

            /*Straight Down*/
            if (D && !DL && !DR && !U && !L && !R && !UL && !UR)
            {
                if (aMState != "D")
                {
                    aMState = "D";
                    setAnimation();
                }
            }

            /*Down Left*/
            if (DL && !D && !DR && !U && !L && !R && !UL && !UR)
            {
                if (aMState != "DL")
                {
                    aMState = "DL";
                    setAnimation();
                }
            }

            /*Down Right*/
            if (DR && !D && !DL && !U && !L && !R && !UL && !UR)
            {
                if (aMState != "DR")
                {
                    aMState = "DR";
                    setAnimation();
                }
            }

            /*Left*/
            if (L && !R && !DL && !UL && !DR && !UR && !U && !D)
            {
                if (aMState != "L")
                {
                    aMState = "L";
                    setAnimation();
                }

            }

            /*Right*/
            if (R && !L && !DL && !UL && !DR && !UR && !U && !D)
            {
                if (aMState != "R")
                {
                    aMState = "R";
                    setAnimation();
                }

            }

            /* Check if Agent has collided with player*/
            if (col.checkEntityCollision(col, player.getCollider()))
            {
                player.resetPos();
                resetPos();
                caught++;
                /*Debug Message*/
                // System.out.println("HIT!");
            }
        
    }

    /**
     * Change the current state to nState, do current state's Exit method first,
     * then change state to nState, then do nState's Enter method.
     *
     * @param nState New state being changed to
     * @param t delta time
     */
    public void ChangeState(EntityState<AgentEntity> nState, int t)
    {
        aCurState.Exit(this, t);
        aCurState = nState;
        aCurState.Enter(this, t);
    }

    /**
     * Set the animation based on Agent's movement.
     */
    public void setAnimation()
    {

        if (aAnim != (animM.setAnimation(this)));
        {
            aAnim = animM.setAnimation(this);
        }

    }

    /**
     * Return the distance from target (also used to determine direction target
     * is from Agent)
     *
     * @param target Target Agent is pursuing
     * @return
     */
    public Vector2D getTargetDistDir(Point2D target)
    {
        Vector2D test = target.minus(position);
        toTarget = target.minus(feetPos);//new Vector2D(target.getX() - position.getX(), target.getY() - position.getY());
        return toTarget;
    }

    /* Returns current animation (used by SpriteRenderer)*/
    public Animation getAnimation()
    {
        return aAnim;
    }

    /* Returns current Agent movement state (used by AnimationManager)*/
    public String getAMState()
    {
        return aMState;
    }

    /* Return's Agent's collider (used for visual debug) */
    public Collider getCollider()
    {
        return col;
    }

    /* Reset Agent's position to it's starting location*/
    public void resetPos()
    {
        position.set(startPos);
    }

    /* Returns Agent's current state used by AgentStateChase, AgentStateEat, and AgentStateSleep)*/
    public EntityState<AgentEntity> getCurState()
    {
        return aCurState;
    }

    /* Returns boolean of if Agent is visible*/
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Set Agent's visibility using true or false
     *
     * @param v boolean to set visibility
     */
    public void setVisibility(boolean v)
    {
        visible = v;
    }

    public double getSpeed()
    {
        return aSpeed;
    }

    public GameMap getMap()
    {
        return gMap;

    }

    public NavGraph getGraph()
    {
        return graph;
    }

    public PathFinder getPathF()
    {
        return pathF;
    }

    public Point2D getFeetPos()
    {
        return feetPos;
    }

    public Iterable<Edge> getConEdge()
    {
        return pathF.getConsideredEdges();
    }
    
}
