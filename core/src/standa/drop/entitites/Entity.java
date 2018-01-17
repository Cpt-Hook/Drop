package standa.drop.entitites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

public abstract class Entity implements Disposable {
    public Rectangle rect;
    protected Texture texture;

    protected Entity(int x, int y, int width, int height, Texture texture) {
        this.rect = new Rectangle(x, y, width, height);
        this.texture = texture;
    }

    protected Entity(Texture texture){
        this(0,0, texture.getWidth(), texture.getHeight(), texture);
    }

    public abstract void tick();

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer){
        batch.draw(texture, rect.x, rect.y, rect.width, rect.height);
    }

    public void dispose(){
        texture.dispose();
    }
}
