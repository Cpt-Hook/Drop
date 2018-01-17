package standa.drop.entitites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import standa.drop.screens.gamescreen.GameScreen;

public abstract class GameEntity extends Entity{

    protected GameScreen game;
    public Rectangle hitBox;
    public boolean debug;

    protected GameEntity(int x, int y, int width, int height, Texture texture, GameScreen game) {
        super(x,y,width,height, texture);
        hitBox = new Rectangle(rect);
        this.game=game;
        this.debug = game.drop.debug;
    }

    protected GameEntity(Texture texture, GameScreen game){
        super(texture);
        this.game=game;
    }

    protected void updateHitBox(){
        hitBox.x = rect.x;
        hitBox.y = rect.y;
    }

    public void tick(){
        updateHitBox();
    }

    @Override
    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        super.draw(batch, shapeRenderer);
        if(debug) {
            batch.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
            shapeRenderer.end();
            batch.begin();
        }
    }
}
