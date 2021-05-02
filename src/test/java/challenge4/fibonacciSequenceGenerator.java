package challenge4;

import java.util.ArrayList;
import java.util.Scanner;


public class fibonacciSequenceGenerator {

    public static int getDesiredLength() {
        System.out.println("Please enter the desired Fibonacci sequence length");
        Scanner input = new Scanner(System.in);
        int desiredLength = input.nextInt();
        System.out.println("The desired length is: " + desiredLength);
        return desiredLength;
    }

    public static int createFiboSequence(int desiredLength) {
        ArrayList<Integer> fiboValues = new ArrayList<>(2);
        fiboValues.add(0);
        fiboValues.add(1);
        while (fiboValues.size() < desiredLength) {
            fiboValues.add(fiboValues.get(fiboValues.size() - 1) +
                    (fiboValues.get(fiboValues.size() - 2)));
        }
        if (fiboValues.size() == desiredLength) {
            System.out.println(fiboValues.toString());
            return fiboValues.get(fiboValues.size() - 1);
        }
        return fiboValues.get(fiboValues.size() - 1);
    }
}
