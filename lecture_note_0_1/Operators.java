package lecture_note_0_1;//Operators.java

public class Operators
{
    public static void main( String [] args ) {
        double salad = 5.95;
        double water = .89;
        double cookie = .50;

        double lunch = salad + water + 2 * cookie;

        System.out.println("The cost of lunch is " + lunch);

        double budget = 20.0;
        budget = budget - lunch;

        System.out.println("After lunch, my budget is " + budget);

        double gas = 3.50;

        System.out.println("I can purchase " + budget / gas +
                " gallons of gas");

    }
}