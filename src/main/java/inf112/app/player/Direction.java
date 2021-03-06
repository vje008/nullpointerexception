package inf112.app.player;

public enum Direction {
    NORTH(2),
    EAST(1),
    SOUTH(0),
    WEST(3);

    private final int id;

    Direction(int id) {
        this.id = id;
    }

    /**
     * @return direction as a string
     */
    public String getName() {
        if (this == NORTH)
            return "N";
        if (this == EAST)
            return "E";
        if (this == SOUTH)
            return "S";
        return "W";
    }

    /**
     * Changes direction to the opposite direction
     *
     * @return new direction
     */
    public Direction reverseDirection() {
        if (this == NORTH)
            return SOUTH;
        if (this == EAST)
            return WEST;
        if (this == SOUTH)
            return NORTH;
        return EAST;
    }

    /**
     * turns the player left
     *
     * @return direction
     */
    public Direction turnLeft() {
        if (this == NORTH)
            return WEST;
        if (this == EAST)
            return NORTH;
        if (this == SOUTH)
            return EAST;
        return SOUTH;
    }

    /**
     * Turns the player right
     *
     * @return direction
     */
    public Direction turnRight() {
        if (this == NORTH)
            return EAST;
        if (this == EAST)
            return SOUTH;
        if (this == SOUTH)
            return WEST;
        return NORTH;
    }

    /**
     * An int value of an direction.
     *
     * @return direction id
     */
    public int getID() {
        return this.id;
    }


}
