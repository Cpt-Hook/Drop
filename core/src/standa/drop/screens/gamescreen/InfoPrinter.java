package standa.drop.screens.gamescreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;

class InfoPrinter {

    private BitmapFont font;
    private GameScreen game;
    private GlyphLayout gameInfoLayout, pausedLayout;

    private int offset = 5;

    public InfoPrinter(GameScreen game) {
        this.game = game;
        this.font = game.drop.font;
        gameInfoLayout = new GlyphLayout(font, "", Color.WHITE, game.width / 5, Align.left, true);
        pausedLayout = new GlyphLayout(font, "GAME IS PAUSED\nTap anywhere to continue.", Color.WHITE, 0, Align.center, false);
    }

    public void setText(String text) {
        gameInfoLayout.setText(font, text, Color.WHITE, game.width / 2, Align.left, false);
    }

    public void setText(int score, int health, int highScore) {
        setText(String.format("Your health: %s%nYour score: %s%nMax score: %s", health, score, highScore));
    }

    public void draw(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        font.draw(batch, gameInfoLayout, offset, game.height - offset);
        if(game.state == GameScreen.GameState.PAUSED) font.draw(batch, pausedLayout,
                game.width/2, game.height/2 );
    }
}
