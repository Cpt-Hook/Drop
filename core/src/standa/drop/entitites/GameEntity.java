package standa.drop.entitites;

import com.badlogic.gdx.graphics.Texture;
import standa.drop.screens.gamescreen.GameScreen;

public abstract class GameEntity extends Entity{

    protected GameScreen game;

    protected GameEntity(int x, int y, int width, int height, Texture texture, GameScreen game) {
        super(x,y,width,height, texture);
        this.game=game;
    }

    protected GameEntity(Texture texture, GameScreen game){
        super(texture);
        this.game=game;
    }
}
