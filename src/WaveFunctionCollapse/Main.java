package WaveFunctionCollapse;

public class Main {
    public static int distinctTileCount = 2;
    public final static int outputImageWidth = 10;
    public final static int outputImageHeight = 10;
    public static Cell[][] cellArray = new Cell[outputImageWidth][outputImageHeight];
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Math.random());
        }
        for (int x = 0; x < outputImageWidth; x++) {
            for (int y = 0; y < outputImageHeight; y++) {
                cellArray[x][y] = new Cell(x, y);
            }
        }

    }
}
