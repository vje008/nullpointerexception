package inf112.app.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.app.Card;
import inf112.app.Game;

import java.util.ArrayList;


public class Player extends InputAdapter {

    //Vector holds players position
    private Vector2 player;
    private TiledMapTileLayer.Cell playerCell;
    private Position pos;
    private Direction dir;
    private Game game;
    private ArrayList<Card> hand;

    /**
     * Initializing default/dying/winning cells of a player.
     * Texture region splits the player texture into 3 different textures and puts them in a 2-dimensional array.
     * Initializing the inputProcessor for input-listening.
     *
     * @param game game object
     */
    public Player(Game game) {

        this.game = game;
        dir = Direction.SOUTH;
        this.hand = new ArrayList<>();

        Texture robotTextures = new Texture("assets/robot.png");
        TextureRegion[][] robotTexture = TextureRegion.split(robotTextures,
                (int) Game.TILE_SIZE,
                (int) Game.TILE_SIZE);

        playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(robotTexture[0][0]));

        player = new Vector2();
        pos = new Position((int) player.x, (int) player.y);

        Gdx.input.setInputProcessor(this);
    }

    /**
     * Constructor mainly for testing purposes
     */
    public Player() {
        dir = Direction.SOUTH;
        player = new Vector2();
        pos = new Position((int) player.x, (int) player.y);
    }

    /**
     * Refreshing the former players position to null
     * Implements the board-movement of a player
     * Prints out the current position
     *
     * @param keycode - an integer representation of different possible inputs
     * @return true/false
     */
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                if (dir != Direction.EAST)
                    dir = Direction.EAST;
                else
                    game.movePlayer(pos, dir);
                break;
            case Input.Keys.LEFT:
                if (dir != Direction.WEST)
                    dir = Direction.WEST;
                else
                    game.movePlayer(pos, dir);
                break;
            case Input.Keys.UP:
                if (dir != Direction.NORTH)
                    dir = Direction.NORTH;
                else
                    game.movePlayer(pos, dir);
                break;
            case Input.Keys.DOWN:
                if (dir != Direction.SOUTH)
                    dir = Direction.SOUTH;
                else
                    game.movePlayer(pos, dir);
                break;
            case Input.Keys.Q:
                game.checkCurrentTile(this);
                break;
            case Input.Keys.SPACE:
                game.tryToMove();
                //game.movePlayer2();
            default:
        }
        updateState();
        return super.keyDown(keycode);
    }

    /**
     * Changes the position of the player to a new position
     *
     * @param newPos the new position
     */
    public void setPos(Position newPos) {
        pos = new Position(newPos);
    }

    /**
     * Changes the position of the player to a predetermined position
     *
     * @return the new position
     */
    public Position checkpoint() {
        return pos = new Position(0, 0);
    }

    /**
     * Sets the current player state to default
     *
     * @return the new player state
     */
    public TiledMapTileLayer.Cell setImage() { return playerCell; }

    /**
     * Checks which imagine to show to the screen depending on player state
     */
    public void updateState() {
        playerCell.setRotation(dir.getID());
    }

    /**
     * Gets the current position of the player
     *
     * @return position
     */
    public Position getPos() {
        return pos;
    }

    /**
     * Gets the current direction of the player
     *
     * @return direction
     */
    public Direction getDirection() {
        return dir;
    }

    /**
     * Changes the direction of the player to a new direction
     *
     * @param dir direction
     */
    public void setDirection(Direction dir) {
        this.dir = dir;
    }

    /**
     * Adds a card to the hand.
     * @param card
     */
    public void setHand(Card card) { hand.add(card); }

    /**
     * Returns a card in hand given index
     * @param index int
     * @return Card
     */
    public Card getCard(int index) { return hand.get(index); }
}