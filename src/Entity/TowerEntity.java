/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Math.Point2D;
import Math.Vector2D;
import java.util.ArrayList;
import java.util.Queue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Bakuryu
 */
public class TowerEntity extends Entity
{
    private String type;
    private Image towerSpr;
    private int fireSpeed;
    private Vector2D target;

    
    public TowerEntity(String type) throws SlickException
    {
        towerSpr = new Image("res/graphics/tower32x32.png");
        this.type = type;
  
    }
    
    @Override
    public void update(GameContainer gc,int t)
    {

    }
    

    
}
