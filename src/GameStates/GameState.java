package GameStates;

import Entity.AgentEntity;
import Entity.EntityManager;
import Graphics.GUI;
import Graphics.GameMap;
import Math.CoordinateTranslator;
import Math.Point2D;
import java.awt.Rectangle;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState
{


    /* Constant values of the width and height of the game*/
    static final int SWidth = 640;
    static final int SHeight = 560;
    private Animation anim;
    private AgentEntity agent;
    private EntityManager entM;
    private GameMap map;
    private CoordinateTranslator corT;
    private GUI gUI;

    @Override
    public void init(GameContainer gc, StateBasedGame gm) throws SlickException
    {
        corT = new CoordinateTranslator(SWidth, SHeight, 100, 100, new Point2D(0, 0));
        map = new GameMap("TDMap.tmx", 100, 100, SWidth, SHeight);
        entM = new EntityManager();
        agent = new AgentEntity(72.5, 0, "Clyde");
        entM.addEnt(agent);
        anim = new Animation();
        gUI = new GUI(corT);
    }

    /* Render entities and the map*/
    @Override
    public void render(GameContainer gc, StateBasedGame gm, Graphics g) throws SlickException
    {

        map.render(g, new Point2D(0, 0));
        for (Rectangle r : map.getFreeRect())
        {
            g.drawRect(r.x, r.y, r.width, r.height);
        }

        anim = agent.getAnimation();
        anim.draw((float) corT.worldToScreen(agent.getPosition()).getX(), (float) corT.worldToScreen(agent.getPosition()).getY());
        gUI.render(g);

    }

    /* Restrict delta to 50, update entities, check for P being pressed to pause the game, and update the score*/
    @Override
    public void update(GameContainer gc, StateBasedGame gm, int t) throws SlickException
    {
        entM.updateEnts(gc, t);
        gUI.update(gc, t);
        System.out.println("DeltaTime: " + t);

    }

    /* Returns game states ID*/
    @Override
    public int getID()
    {
        return 1;
    }

}
