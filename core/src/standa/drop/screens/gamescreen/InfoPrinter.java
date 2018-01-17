package standa.drop.screens.gamescreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import standa.drop.DropGame;

public class InfoPrinter {

    private BitmapFont font;
    private DropGame game;
    private GlyphLayout layout;

    private int offset = 5;

    public InfoPrinter(DropGame game) {
        this.game = game;
        this.font=game.font;
        layout = new GlyphLayout(font, "", Color.WHITE, game.width/5, Align.center, true);

    }

    public void setText(String text) {
        layout.setText(font, text, Color.WHITE, game.width/2, Align.left, true);
    }

    public void setText(int score, int health, int highScore){
        setText(String.format("Your health: %s%nYour score: %s%nMax score: %s", health, score, highScore));
    }



    public void draw(SpriteBatch batch){
        font.draw(batch, layout, offset, game.height - offset);
    }
}
