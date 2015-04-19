/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import Components.Collider;
//import Graphics.AnimationManager;
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

    /**
     * Create an AgentEntity at location (x,y), give it a player to target, and
     * set it's max hunger and stamina.
     *
     * @param x Agent's starting x coordinate
     * @param y Agent's starting y coordinate
     * @throws SlickException
     */
    public AgentEntity(double x, double y) throws SlickException
    {

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
    }






    
}
