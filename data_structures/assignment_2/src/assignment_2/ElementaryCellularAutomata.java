import java.util.Random;

public class ElementaryCellularAutomata {
    private boolean[] cells;
    private EcaParams params;
    private RuleSet ruleSet;

    public ElementaryCellularAutomata(EcaParams params) {
        this.params = params;
        this.cells = new boolean[params.size];
        this.ruleSet = new RuleSet(params.ruleProbs, params.seed);
        initializeCells();
    }

    private void initializeCells() {
        Random rand = new Random(params.seed);
        for (int i = 0; i < params.size; i++) {
            cells[i] = rand.nextDouble() < params.initProb;
        }
    }

    private void printCells() {
        for (boolean cell : cells) {
            if (cell) {
                params.onColor.printBlock();
            } else {
                params.offColor.printBlock();
            }
        }
        System.out.println();
    }

    private boolean getCircularNeighbor(int idx) {
        if (idx < 0) {
            return cells[cells.length - 1];
        } else if (idx >= cells.length) {
            return cells[0];
        } else {
            return cells[idx];
        }
    }

    private void updateCells() {
        boolean[] newCells = new boolean[cells.length];
        for (int i = 0; i < cells.length; i++) {
            boolean left = getCircularNeighbor(i - 1);
            boolean center = cells[i];
            boolean right = getCircularNeighbor(i + 1);
            newCells[i] = ruleSet.getNextState(left, center, right);
        }
        cells = newCells;
    }

    public void runSimulation() {
        for (int i = 0; i < params.iterations; i++) {
            printCells();
            updateCells();
        }
    }

    public static void main(String[] args) {
        EcaParams params = new EcaParams(args);
        ElementaryCellularAutomata eca = new ElementaryCellularAutomata(params);
        eca.runSimulation();
    }
}
