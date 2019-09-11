import java.util.Stack;

public class Individual {

    protected double fitness;
    protected String chromosome;
    protected String type;
    protected String target;
    protected double solution;


    public double getFitness() {

        setFitness(chromosome);
        return fitness;
    }

    public void setFitness(String indi) {

        double fitness = 0;

        if (type.equals("Binary")) {

            for (int j = 0; j < indi.length(); j++) {
                if (indi.charAt(j) == '1')
                    fitness++;
            }
            this.fitness = fitness;
        } else if (type.equals("Weasel")) {

            for (int j = 0; j < indi.length(); j++) {

                int temp1 = indi.charAt(j);
                int temp2 = target.charAt(j);
                fitness += Math.abs(temp2 - temp1);
            }
            this.fitness = fitness;

        } else if (type.equals("Maths")) {

            double expsol = 0;
            try {
                Stack<Integer> op = new Stack<Integer>();
                Stack<Double> val = new Stack<Double>();

                Stack<Integer> optmp = new Stack<Integer>();
                Stack<Double> valtmp = new Stack<Double>();


                indi = "0" + indi;
                indi = indi.replaceAll("-", "+-");

                String temp = "";
                for (int i = 0; i < indi.length(); i++) {
                    char ch = indi.charAt(i);
                    if (ch == '-')
                        temp = "-" + temp;
                    else if (ch != '+' && ch != '*' && ch != '/')
                        temp = temp + ch;
                    else {
                        val.push(Double.parseDouble(temp));
                        op.push((int) ch);
                        temp = "";
                    }
                }
                val.push(Double.parseDouble(temp));

                char operators[] = {'/', '*', '+'};

                for (int i = 0; i < 3; i++) {
                    boolean it = false;
                    while (!op.isEmpty()) {
                        int optr = op.pop();
                        double v1 = val.pop();
                        double v2 = val.pop();
                        if (optr == operators[i]) {

                            if (i == 0) {
                                valtmp.push(v2 / v1);
                                it = true;
                                break;
                            } else if (i == 1) {
                                valtmp.push(v2 * v1);
                                it = true;
                                break;
                            } else if (i == 2) {
                                valtmp.push(v2 + v1);
                                it = true;
                                break;
                            }
                        } else {
                            valtmp.push(v1);
                            val.push(v2);
                            optmp.push(optr);
                        }
                    }

                    while (!valtmp.isEmpty())
                        val.push(valtmp.pop());
                    while (!optmp.isEmpty())
                        op.push(optmp.pop());

                    if (it)
                        i--;
                }
                expsol = val.pop();
                fitness = Math.abs(solution - expsol);
                this.fitness = solution - fitness;
            } catch (Exception e) {
                solution = 10000000;
                this.fitness = 10000000;
            }
        }

    }

    public String toString() {
        return chromosome;
    }

}


