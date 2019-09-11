import java.util.ArrayList;

public abstract class GAApplication {


    protected final int pop_size = 100;//100
    protected final int parent_size = 40;//40
    protected final double cross_prob = 0.8;//0.8
    protected final double mut_prob = 0.001;//0.5
    public Individual indi = new Individual();
    protected ArrayList<String> population = new ArrayList<>();

    public void setPopulation(ArrayList<String> population) {

        this.population = population;
    }

    protected abstract ArrayList<String> randompopulation();

    protected abstract void mutation(ArrayList<String> population);

    protected abstract void crossover(ArrayList<String> population);

    public void run() {
        mutation(population);
        ArrayList<Double> fitnessarray = fitness(population);
        sortfitness(fitnessarray, population);
        if (Math.random() <= cross_prob)
            crossover(population);
        fitnessarray = fitness(population);
        sortfitness(fitnessarray, population);
    }


    public void sortfitness(ArrayList<Double> fitnessarray, ArrayList<String> population) {

        for (int i = 0; i < fitnessarray.size(); i++) {
            int pos = i;
            for (int j = i; j < fitnessarray.size(); j++) {
                if (fitnessarray.get(j) < fitnessarray.get(pos))
                    pos = j;
            }
            double min = fitnessarray.get(pos);
            String mins = population.get(pos);

            fitnessarray.set(pos, fitnessarray.get(i));
            population.set(pos, population.get(i));

            fitnessarray.set(i, min);
            population.set(i, mins);

        }
    }

    public Individual getBest() {
        return indi;
    }

    protected ArrayList<Double> fitness(ArrayList<String> population) {
        ArrayList<Double> fitnessarray = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
            double fitness = 0;
            indi.chromosome = population.get(i);
            fitness = indi.getFitness();
            fitnessarray.add(fitness);
        }
        return fitnessarray;
    }

}
