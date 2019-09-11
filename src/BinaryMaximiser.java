package com.bham.pij.assignments.a2a;

import java.util.ArrayList;
import java.util.Random;


public class BinaryMaximiser extends GAApplication {

    private final double mut_prob = 0.001;
    private int binarygoal;
    private ArrayList<String> population;
    private Random rand = new Random();


    public BinaryMaximiser(int binarygoal) {

        this.binarygoal = binarygoal;
        this.population = randompopulation();
        setPopulation(population);
        indi.type = "Binary";
    }

    @Override
    protected ArrayList<String> randompopulation() {

        int strlen = binarygoal;
        ArrayList<String> population = new ArrayList<>();

        for (int i = 0; i < pop_size; i++) {

            char[] binary = new char[strlen];

            for (int j = 0; j < strlen; j++) {
                int gene = rand.nextInt(2);
                if (gene == 1) {
                    binary[j] = '1';
                } else
                    binary[j] = '0';
            }
            String chromosome = new String(binary);
            population.add(chromosome);
        }
        return population;
    }

    protected void mutation(ArrayList<String> population) {

        for (int i = 0; i < population.size(); i++) {
            String temp = "";
            for (int j = 0; j < population.get(i).length(); j++) {
                if (Math.random() <= mut_prob) {
                    if (population.get(i).charAt(j) == '1')
                        temp += "0";
                    else
                        temp += "1";
                } else
                    temp += population.get(i).charAt(j);
            }
            population.set(i, temp);
        }
    }

    protected void crossover(ArrayList<String> population) {

        ArrayList<String> children = new ArrayList<>();
        int max = population.size() - 1;
        int min = population.size() - parent_size;
        for (int i = 0; i < parent_size; i++) {
            int num1 = rand.nextInt((max - min) + 1) + min;
            int num2;
            do {
                num2 = rand.nextInt((max - min) + 1) + min;
            } while (num1 == num2);

            String parent1 = population.get(num1);
            String parent2 = population.get(num2);

            int crossoverpoint = rand.nextInt((int) binarygoal);

            String child1 = parent1.substring(0, crossoverpoint) + parent2.substring(crossoverpoint);
            String child2 = parent2.substring(0, crossoverpoint) + parent1.substring(crossoverpoint);

            children.add(child1);
            children.add(child2);
        }
        for (int i = 0; i < children.size(); i++) {
            population.set(i, children.get(i));
        }
    }

    @Override
    public Individual getBest() {

        indi.chromosome = population.get(pop_size - 1);
        return indi;
    }

}

