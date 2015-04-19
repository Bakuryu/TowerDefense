/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Entity.AgentEntity;
import Entity.Entity;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Bakuryu
 */
public class AnimationManager
{

    private SpriteSheet blinky;
    private SpriteSheet inky;
    private SpriteSheet pinky;
    private SpriteSheet clyde;
    /* Animation to be return to entity*/
    private Animation anim;

    /* Temporary animation variable for storing the next animation*/


    /**
     * Create an AnimationManager, load player and agent sprite into sprite
     * sheets, and initialize variables.
     *
     * @throws SlickException
     */
    public AnimationManager() throws SlickException
    {

        blinky = new SpriteSheet("res/graphics/BlinkyAnim2.png", 30, 29);
        inky = new SpriteSheet("res/graphics/InkyAnim2.png", 30, 29);
        pinky = new SpriteSheet("res/graphics/PinkyAnim2.png", 30, 29);
        clyde = new SpriteSheet("res/graphics/ClydeAnim2.png", 30, 29);
        anim = new Animation();
    }

    /* Retrieve animation based on entity type*/
    public Animation setAnimation(String type)
    {
        if (type == "Blinky")
        {
            anim = new Animation(blinky, 270);
        }

        if (type == "Inky")
        {
            anim = new Animation(inky, 270);
        }

        if (type == "Pinky")
        {
            anim = new Animation(pinky, 270);
        }

        if (type == "Clyde")
        {
            anim = new Animation(clyde, 270);
        }
        return anim;
    }

}
