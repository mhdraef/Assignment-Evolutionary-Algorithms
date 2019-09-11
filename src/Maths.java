import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maths extends GAApplication {

    protected final double mut_prob = 0.02;
    protected int target;
    protected int explen;
    protected ArrayList<String> population;
    Random rand = new Random();

    public Maths(int target, int explen) {

        this.target = target;
        this.explen = explen;
        this.population = randompopulation();
        setPopulation(population);
        indi.type = "Maths";
        indi.solution = target;
    }

    protected static double evalexp(String input) {

        try {
            Stack<Integer> op = new Stack<Integer>();
            Stack<Double> val = new Stack<Double>();

            Stack<Integer> optmp = new Stack<Integer>();
            Stack<Double> valtmp = new Stack<Double>();

            input = "0" + input;
            input = input.replaceAll("-", "+-");

            String temp = "";
            for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
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
            return val.pop();
        } catch (Exception e) {
            return 10000000;
        }


    }

    @Override
    protected ArrayList<String> randompopulation() {

        int strlen = explen;
        ArrayList<String> population = new ArrayList<>();


        for (int i = 0; i < pop_size; i++) {

            char[] goal = new char[strlen];
            int max = 57;
            int min = 42;

            for (int j = 0; j < strlen; j++) {
                int gene = rand.nextInt((max - min) + 1) + min;
                while (gene == 44 || gene == 46) {
                    gene = rand.nextInt((max - min) + 1) + min;
                }
                goal[j] = (char) gene;
            }
            String chromosome = new String(goal);
            population.add(chromosome);
        }
        return population;
    }

    @Override
    protected void mutation(ArrayList<String> population) {

        for (int i = 0; i < population.size(); i++) {
            String temp = "";
            for (int j = 0; j < population.get(i).length(); j++) {
                if (Math.random() <= mut_prob) {
                    double upordown = Math.random();
                    if (upordown <= 0.5) {
                        int tempchar = (int) population.get(i).charAt(j);
                        int newchar = tempchar + 1;
                        if (newchar >= 42 && newchar <= 57)
                            temp += (char) newchar;
                        else if (newchar == 58) {
                            newchar = 42;
                            temp += (char) newchar;
                        } else if (newchar == 44) {
                            newchar = 45;
                            temp += (char) newchar;
                        } else if (newchar == 46) {
                            newchar = 47;
                            temp += (char) newchar;
                        }
                    } else {
                        int tempchar = (int) population.get(i).charAt(j);
                        int newchar = tempchar - 1;
                        if (newchar >= 42 && newchar <= 57)
                            temp += (char) newchar;
                        else if (newchar == 41) {
                            newchar = 57;
                            temp += (char) newchar;
                        } else if (newchar == 44) {
                            newchar = 43;
                            temp += (char) newchar;
                        } else if (newchar == 46) {
                            newchar = 45;
                            temp += (char) newchar;
                        }
                    }
                } else
                    temp += population.get(i).charAt(j);
            }
            population.set(i, temp);
        }
    }

    @Override
    protected void crossover(ArrayList<String> population) {

        ArrayList<String> children = new ArrayList<>();
        for (int i = 0; i < parent_size; i++) {
            int num1 = rand.nextInt(10);
            int num2;
            do {
                num2 = rand.nextInt(10);
            } while (num1 == num2);

            String parent1 = population.get(num1);
            String parent2 = population.get(num2);

            int crossoverpoint = rand.nextInt(explen);

            String child1 = parent1.substring(0, crossoverpoint) + parent2.substring(crossoverpoint);
            String child2 = parent2.substring(0, crossoverpoint) + parent1.substring(crossoverpoint);

            children.add(child1);
            children.add(child2);
        }
        for (int i = pop_size - 1, j = 0; j < children.size(); i--, j++) {
            population.set(i, children.get(j));

        }

    }

    @Override
    protected ArrayList<Double> fitness(ArrayList<String> population) {
        ArrayList<Double> fitnessarray = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
            double fitness;
            double solution = evalexp(population.get(i));
            fitness = Math.abs(target - solution);
            fitnessarray.add(fitness - target);
        }
        return fitnessarray;
    }

    public Individual getBest() {
        indi.chromosome = population.get(0);
        return indi;
    }


}
