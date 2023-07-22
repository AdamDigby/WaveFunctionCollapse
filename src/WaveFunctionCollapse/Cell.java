package WaveFunctionCollapse;

public class Cell {
    int x;
    int y;
    boolean collapsed;
    int color;


    boolean[] possibilities;
    boolean[] demands;
    int possibilitiesSum;

    public Cell(int X, int Y) {
        this.x = X;
        this.y = Y;
        this.collapsed = false;
        // Initialises the possibilities coefficients to be all true.
        this.possibilities = new boolean[Main.distinctTileCount];
        for (int i = 0; i < Main.distinctTileCount; i++) {this.possibilities[i] = true;}
        for(boolean possibility:this.possibilities) {
            if (possibility) {this.possibilitiesSum++;}
        }
        // Initialises the possibilities coefficients to be all false.
        this.demands = new boolean[Main.distinctTileCount];



    }
    public void collapse() {
        int randomIndex = (int)Math.floor(Math.random() * this.possibilitiesSum);
        int trueIndex = 0;
        for (int i = 0; i < Main.distinctTileCount; i++) {
            if (this.possibilities[i]) {
                if (randomIndex == trueIndex) {
                    this.collapsed = true;
                    this.possibilities = new boolean[Main.distinctTileCount];
                    this.color = i;
                    this.possibilities[i] = true;
                }else {
                    trueIndex++;
                }
            }
        }
    }
    public void update() {
        if (this.possibilitiesSum == 1) {
            this.collapse();
        }else if (this.possibilitiesSum == 0) {
            System.out.println("ERROR: Cell " + String.valueOf(this.x) + "," + String.valueOf(this.y) + " has 0 possibilities.");
        }
    }

    public boolean demand() {
         boolean changed = false;
         for (int i = 0; i < Main.distinctTileCount; i++) {
             if (this.possibilities[i] && !this.demands[i]) {
                 changed = true;
                 this.possibilities[i] = false;
                 this.possibilitiesSum -= 1;
             }
         }
         this.update();
         return changed;
    }
    public String printValue() {
        if (!this.collapsed) {return "n";}
        return Main.colorConvert[this.color];
    }

}
