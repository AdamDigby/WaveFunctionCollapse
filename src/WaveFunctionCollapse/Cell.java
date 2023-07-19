package WaveFunctionCollapse;

public class Cell {
    int x;
    int y;
    boolean collapsed;

    boolean[] possibilities;

    public Cell(int X, int Y) {
        this.x = X;
        this.y = Y;
        this.collapsed = false;
        // Initialises the possibilities coefficients to be all true.
        this.possibilities = new boolean[Main.distinctTileCount];
        for (int i = 0; i < Main.distinctTileCount; i++) {this.possibilities[i] = true;}

    }
    public void collapse() {

    }

}
