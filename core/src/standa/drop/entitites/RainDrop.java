package standa.drop.entitites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import standa.drop.BackgroundRainManager;
import standa.drop.Utils;

public class RainDrop extends Entity {

    private float speed;
    private float acceleration = 0.025f;
    public boolean toDie;

    public RainDrop(Texture texture, BackgroundRainManager manager, double speedMutliplier) {
        super(texture);
        int size = MathUtils.random(5,25);
        rect.width = rect.height = size;
        rect.x = MathUtils.random(0, manager.getWidth() - size);
        rect.y = manager.getHeight();
        speed = (float) Utils.map(size, 8,16,1*speedMutliplier,8*speedMutliplier, true);
    }

    public void setSpeedMultiplier(double speedMultiplier){
        speed = (float) Utils.map(rect.height, 8,16,1*speedMultiplier,8*speedMultiplier, true);
    }

    public RainDrop(Texture texture, BackgroundRainManager manager){
        this(texture, manager, 1);
    }

    @Override
    public void tick() {
        rect.y -= speed;
        speed += acceleration;
        if(rect.y < -rect.height){
            toDie=true;
        }
    }
}
