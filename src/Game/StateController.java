package Game;

import GameStates.GameState;
//import GameStates.LoseState;
//import GameStates.MenuState;
//import GameStates.WinState;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class StateController extends StateBasedGame
{

    static final int SWidth = 640;
    static final int SHeight = 560;

    public StateController()
    {
        super("Game");
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new StateController());
            app.setDisplayMode(SWidth, SHeight, false);
            app.start();
            app.setSmoothDeltas(true);

        } catch (SlickException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
        //addState(new MenuState());
        addState(new GameState());
        //addState(new WinState());
        //addState(new LoseState());
    }

}
