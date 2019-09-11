//package com.bham.pij.assignments.a2a;

//public class GATester {/* Change this constant to change the goal */
//    private static final int BINARY_GOAL = 400;/* Change this constant to change the maximum number of runs of the GA */
//    private static final int MAX_GENERATIONS = 1000;/* Change this constant to change the maximum number of runs of the GA */
//    private static final int MIN_GENERATIONS = 3;
//
//    public static void main(String[] args) {
//        BinaryMaximiser bm = new BinaryMaximiser(BINARY_GOAL);
//        int gens = 0;
//        do {
//            bm.run();/* Prints out the fitness of the current best individual */
//            System.out.println(bm.getBest().getFitness());/* while the goal is not reached and the number of generations has not exceeded the maximum */
//            if(bm.getBest().getFitness() == BINARY_GOAL){
//                System.out.println("found");
//            }
//        } while (bm.getBest().getFitness() != BINARY_GOAL && (gens++) < MAX_GENERATIONS);
//        if (gens <= MIN_GENERATIONS) {
//            System.out.println("The GA might not be working if it finds the goal that quickly.");
//        }
//        System.out.println("Num generations: " + gens);
//    }
//}

//public class GATester {/* Change this constant to change the goal */
//    private static final String BINARY_GOAL = "And ten thousand peoploids split into small tribes, " + "coveting the highest of the sterile skyscrapers, " + "like packs of dogs assaulting the glass fronts of Love-Me Avenue.";/* Change this constant to change the maximum number of runs of the GA */
//    private static final int MAX_GENERATIONS = 6000;/* Change this constant to change the maximum number of runs of the GA */
//    private static final int MIN_GENERATIONS = 3;
//
//    public static void main(String[] args) {
//        GAApplication bm = new Weasel(BINARY_GOAL);
//        int gens = 0;
//        String best=null;
//        do {
//            bm.run();/* Prints out the fitness of the current best individual */
//            best = bm.getBest().toString();
//            gens++;
//            System.out.println(best +"     "+ bm.getBest().getFitness() );
//            if(best.equals(BINARY_GOAL)){
//                System.out.println("found");
//           }
//            //System.out.println(bm.population);/* while the goal is not reached and the number of generations has not exceeded the maximum */
//        } while (!(best.equals(BINARY_GOAL)) && gens < MAX_GENERATIONS);
//        if (gens <= MIN_GENERATIONS) {
//            System.out.println("The GA might not be working if it finds the goal that quickly.");
//        }
//        System.out.println("Num generations: " + gens);
//    }
//}

public class GATester{

    public static void main(String[] args){
        boolean chck = false;
        for(int x =1; x<6; x++){
            Maths maths = new Maths(123456,14);
            int count = 0;
            while(count < 1000 ){
                maths.run();
                System.out.println(maths.getBest() +"   "+maths.getBest().getFitness());
                if(maths.getBest().getFitness() == 123456){
                    System.out.println("found in "+x+" run");
                    chck = true;

                    break;
                }
                count++;
            }
            System.out.println("and "+count+" generations");
            if(chck ==  true){
                break;
            }
        }
    }
}