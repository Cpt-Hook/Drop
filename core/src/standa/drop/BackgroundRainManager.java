package standa.drop;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import standa.drop.entitites.drops.RainDrop;

import java.util.Iterator;

public class BackgroundRainManager {

    private Texture raindropTexture;
    private DropGame game;
    private Array<RainDrop> raindrops;
    private double speedMultiplier;

    public BackgroundRainManager(Texture raindropTexture, DropGame game) {
        this(raindropTexture, game, 1);
    }

    public BackgroundRainManager(Texture raindropTexture, DropGame game, double speedMultiplier) {
        this.raindropTexture = raindropTexture;
        this.game=game;
        this.speedMultiplier=speedMultiplier;
        raindrops = new Array<>();
    }

    public int getHeight() {
        return game.height;
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer){
        for(RainDrop drop:raindrops){
            drop.draw(batch, shapeRenderer);
        }
    }

    public int getWidth() {
        return game.width;
    }

    public void tick(){
        createNewRaindrop();
        createNewRaindrop();
        createNewRaindrop();
        for(Iterator<RainDrop> iterator = raindrops.iterator(); iterator.hasNext();){
            RainDrop drop = iterator.next();
            drop.tick();
            if(drop.toDie) iterator.remove();
        }
    }

    public void createNewRaindrop(){
        raindrops.add(new RainDrop(raindropTexture, this, speedMultiplier));
    }

    public void dispose(){
        raindropTexture.dispose();
        for(RainDrop drop:raindrops){
            drop.dispose();
        }
    }
}
