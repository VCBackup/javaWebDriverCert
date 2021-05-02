package challenge4;


public class challenge4 {

    public static void main(String[] arguments){
        int desiredLength = fibonacciSequenceGenerator.getDesiredLength();
        int fiboValue = fibonacciSequenceGenerator.createFiboSequence(desiredLength);
        String convertedFiboValue = englishNumberToWords.convert(fiboValue);
        System.out.println(convertedFiboValue);
    }
}