# Evolutionary-Algorithm

## Introduction
In this assignment, a genetic algorithm is implemented which is a type of evolutionary algorithm. The genetic algorithm being implemented is a search algorithm that is inspired by natural evolution. Two key processes are adopted: genetic mutation and crossover. Using these two processes, the GA proceeds from an initial collection of random solutions to find some desired solution. The critical component that determines the success or otherwise of the search is called the fitness measure. The fitness measure assigns a numerical fitness to each solution, enabling the algorithm to decide which ones are the ‘fittest’, i.e. the best.

## Algorithm overview 
*The information below has been derived from the assessment brief.*

The GA starts with a collection of random individuals. This collection is usually of a fixed size, for example, 100. An individual is an attempted solution of the particular problem. Since all initial individuals are random, none of them probably represents a particularly good solution. It will usually be the case, however, that even though they are random, some individuals will be better than others.
Each individual has a chromosome. A chromosome is a collection of genes of fixed size. Each gene on a chromosome can have one value from a set of values. In a task involving binary numbers, for example, gene values can only be 1 or 0. No other values will be legal.

The GA follows the following steps.

(1) The first step of the GA is to create a population of random individuals. Each individual has a chromosome that will have been initialised to random values from the range of legal values. The population has a fixed size, e.g. 100.

(2) The mutation operation is then applied to the whole population.

(3) The GA then assesses the fitness of the population. This means it must decide which are the best individuals in the population. To do this a fitness measure is required. The GA applies a fitness measure to each individual and assigns each individual its own fitness value. The fitness measure depends entirely on the purpose of the search. For example, to search for a binary string that consists only of ones, we would use a fitness measure that rewards how many ones there are in a string: the more the better.

(4) The next step involves the creation of new individuals. We decide how many new individuals we want (for example, 20 out of a population of 100). We then select the best individuals from the population so that they can be used in a reproduction process to create the new individuals. For example, we might choose 10 of the best individuals. These are called the parents. If the population has been sorted for fitness, this step is easy. We then randomly select a pair of individuals from these best 10 and take the following steps with them.

(5) We create two new individuals (children) from each pair of parents by using the crossover operation.

(6) Once we have performed these operations and have our new 20 individuals (from doing this repeatedly), we prepare to put them into the general population. To maintain the population size, we remove the 20 worst individuals and add the new 20 individuals.

(7) We then return to step (2) and continue with this process until we reach the desired goal or until a certain number of generations have been processed.
