package assignment_2;

import java.util.Random;

public class RuleSet {
    private double[] ruleProbs;
    private Random rand;

    public RuleSet(double[] ruleProbs, long seed) {
        this.ruleProbs = ruleProbs;
        this.rand = new Random(seed);
    }

    public boolean getNextState(boolean left, boolean center, boolean right) {
        int idx = (left ? 4 : 0) + (center ? 2 : 0) + (right ? 1 : 0);
        return rand.nextDouble() < ruleProbs[idx];
    }
}
