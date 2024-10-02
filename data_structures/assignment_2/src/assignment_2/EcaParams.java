package assignment_2;

public class EcaParams {
    public int size;
    public int iterations;
    public double initProb;
    public AnsiColor onColor;
    public AnsiColor offColor;
    public long seed = System.currentTimeMillis();
    public double[] ruleProbs = new double[8];
    public boolean isProbabilistic = false;

    public EcaParams(String[] args) {
        parseArguments(args);
    }

    private void parseArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-size":
                    size = Integer.parseInt(args[++i]);
                    break;
                case "-iter":
                    iterations = Integer.parseInt(args[++i]);
                    break;
                case "-init":
                    initProb = Double.parseDouble(args[++i]);
                    break;
                case "-on-color":
                    onColor = new AnsiColor(args[++i]);
                    break;
                case "-off-color":
                    offColor = new AnsiColor(args[++i]);
                    break;
                case "-random-seed":
                    seed = Long.parseLong(args[++i]);
                    break;
                case "-rules":
                    if (args[i + 1].contains(".")) {
                        isProbabilistic = true;
                        for (int j = 0; j < 8; j++) {
                            ruleProbs[j] = Double.parseDouble(args[++i]);
                        }
                    } else {
                        isProbabilistic = false;
                        int ruleNum = Integer.parseInt(args[++i]);
                        ruleProbs = decodeWolframRule(ruleNum);
                    }
                    break;
            }
        }
    }

    private double[] decodeWolframRule(int ruleNum) {
        double[] rules = new double[8];
        for (int i = 0; i < 8; i++) {
            rules[i] = (ruleNum & (1 << i)) > 0 ? 1.0 : 0.0;
        }
        return rules;
    }
}
