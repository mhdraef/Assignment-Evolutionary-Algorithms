package com.bham.pij.assignments.a2a;

import java.util.ArrayList;
import java.util.Random;

public class Weasel extends GAApplication {

    protected final double mut_prob = 0.01;
    protected String target;
    protected ArrayList<String> population;
    Random rand = new Random();

    public Weasel(String target) {

        this.target = target;
        this.population = randompopulation();
        setPopulation(population);
        indi.type = "Weasel";
        indi.target = target;
    }

    @Override
    protected ArrayList<String> randompopulation() {

        int strlen = target.length();
        ArrayList<String> population = new ArrayList<>();

        for (int i = 0; i < pop_size; i++) {

            char[] goal = new char[strlen];
            int max = 126;
            int min = 32;

            for (int j = 0; j < strlen; j++) {
                int gene = rand.nextInt((max - min) + 1) + min;
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
                        if (newchar >= 32 && newchar <= 126)
                            temp += (char) newchar;
                        else if (newchar == 127) {
                            newchar = 32;
                            temp += (char) newchar;
                        }
                    } else {
                        int tempchar = (int) population.get(i).charAt(j);
                        int newchar = tempchar - 1;
                        if (newchar >= 32 && newchar <= 126)
                            temp += (char) newchar;
                        else if (newchar == 31) {
                            newchar = 126;
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

            int crossoverpoint = rand.nextInt(target.length());

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
    public Individual getBest() {
        indi.chromosome = population.get(0);
        return indi;
    }

}
