package lecture_note_0_1;//TypeCasting.java

public class TypeCasting
{
    public static void main( String [] args )
    {
        int sum = 100;
        int count = 3;
        // calculate the average

        double average = sum / count;
        System.out.println( "average is " + average ); // 33.0

        // use explicit casting before performing division
        average = ( double ) sum / count;
        System.out.println( "average is " + average ); // 33.333...

    }
}