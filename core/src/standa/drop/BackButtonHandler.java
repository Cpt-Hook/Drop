package standa.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class BackButtonHandler extends InputAdapter {
    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("LOG", "pushed + " + keycode);
        if (keycode == Input.Keys.BACK) {
            Gdx.app.exit();
            return true;
        }
        return super.keyDown(keycode);
    }
}
