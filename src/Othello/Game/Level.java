package Othello.Game;

public enum Level {
    EASY(1),
    MEDIUM(3),
    DIFFICULT(5);

    private int depth;

    Level(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }

}
