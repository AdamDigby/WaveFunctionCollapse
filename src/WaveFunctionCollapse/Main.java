package WaveFunctionCollapse;

public class Main {
    public static int distinctTileCount = 2;
    public final static int kernelSize = 3;
    public final static int[][][] kernelList = new int[][][] {{
            {0, 1, 0},
            {1, 0, 1},
            {0, 1, 0}
    }, {
            {1, 0, 1},
            {0, 1, 0},
            {1, 0, 1}
    }};
    public final static String[] colorConvert = new String[] {".", "#"};
    public final static int outputImageWidth = 8;
    public final static int outputImageHeight = 8;
    public static Cell[][] cellArray = new Cell[outputImageWidth][outputImageHeight];
    public static void main(String[] args) {
        for (int x = 0; x < outputImageWidth; x++) {
            for (int y = 0; y < outputImageHeight; y++) {
                cellArray[x][y] = new Cell(x, y);

            }
        }
        cellArray[2][2].collapse();
        waveHandling(cellArray[2][2]);
        consolePrint();
    }

    public static void consolePrint() {
        for (int y = 0; y < outputImageWidth; y++) {
            String printLine = "";
            for (int x = 0; x < outputImageHeight; x++) {
                printLine += cellArray[y][x].printValue();
            }
            System.out.println(printLine);
        }
    }


    public static void waveHandling(Cell homeCell) {
        for (int[][] kernel:kernelList) {
            if (kernelTest(kernel, homeCell.x, homeCell.y)) {
                appendDemands(kernel, homeCell.x, homeCell.y);
            }
        }
        boolean[][] changedCells = pushDemands(homeCell.x, homeCell.y);
        consolePrint();
        System.out.println("Completed");
        for (int y = 0; y < outputImageHeight; y++) {
            for (int x = 0; x < outputImageHeight; x++) {
                if (changedCells[y][x]) {
                    System.out.println(String.valueOf(x) + "," + String.valueOf(y));

                }
            }
        }
        for (int y = 0; y < changedCells.length; y++) {
            for (int x = 0; x < changedCells.length; x++) {
                if (changedCells[y][x]) {
                    System.out.println("Next homeCell: " + String.valueOf(x) + "," + String.valueOf(y) + ".");
                    waveHandling(cellArray[y][x]);
                }
            }
        }
    }




    public static boolean kernelTest(int[][] kernel, int homeX, int homeY) {
        for (int y = 0; y < kernel.length; y++) {
            for (int x = 0; x < kernel.length; x++) {
                // If it doesn't match.
                try {
                    if (!cellArray[homeY+y-1][homeX+x-1].possibilities[kernel[y][x]]) {return false;}
                }catch (IndexOutOfBoundsException e) {
                    continue;
                }

            }
        }
        return true;
    }

    public static void appendDemands(int[][] kernel, int homeX, int homeY) {
        for (int y = 0; y < kernel.length; y++) {
            for (int x = 0; x < kernel.length; x++) {
                // If it doesn't match.
                try {
                    cellArray[homeY+y-1][homeX+x-1].demands[kernel[y][x]] = true;
                }catch (IndexOutOfBoundsException e) {
                    continue;
                }

            }
        }

    }

    public static boolean[][] pushDemands(int homeX, int homeY) {
        boolean[][] changedCells = new boolean[outputImageHeight][outputImageWidth];
        for (int y = 0; y < kernelSize; y++) {
            for (int x = 0; x < kernelSize; x++) {
                // If it doesn't match.
                try {
                    changedCells[homeY+y-1][homeX+x-1] = cellArray[homeY+y-1][homeX+x-1].demand();
                }catch (IndexOutOfBoundsException e) {
                    continue;
                }

            }
        }
        return changedCells;
    }
}