package GameStates;


import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class GameState extends BasicGameState
{


    /* Constant values of the width and height of the game*/
    static final int SWidth = 640;
    static final int SHeight = 560;

 

    @Override
    public void init(GameContainer gc, StateBasedGame gm) throws SlickException
    {

        
    }

    /* Render entities and the map*/
    @Override
    public void render(GameContainer gc, StateBasedGame gm, Graphics g) throws SlickException
    {
        TiledMap map = new TiledMap("res/TDMap.tmx");
        map.render(0, 0);
       

    }

    /* Restrict delta to 50, update entities, check for P being pressed to pause the game, and update the score*/
    @Override
    public void update(GameContainer gc, StateBasedGame gm, int t) throws SlickException
    {
      

    }

    /* Returns game states ID*/
    @Override
    public int getID()
    {
        return 1;
    }

}
