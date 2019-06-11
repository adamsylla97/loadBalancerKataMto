package edu.iis.mto.serverloadbalancer;

public class   FizzBuzz {

    private static final String VALUE_WHEN_NUMBER_IS_DIVIDABLE_BY_THREE = "Fizz";
    private static final String VALUE_WHEN_NUMBER_IS_DIVIDABLE_BY_FIVE = "Buzz";

    public String check(int number) {
        String result = "";
        if(isDividableByThree(number)){
            result += VALUE_WHEN_NUMBER_IS_DIVIDABLE_BY_THREE;
        }
        if (isDividableByFive(number)){
            result += VALUE_WHEN_NUMBER_IS_DIVIDABLE_BY_FIVE;
        }
        if(result.isEmpty()){
            result = String.valueOf(number);
        }
        return result;
    }

    private boolean isDividableByFive(int number) {
        return number%5 == 0;
    }

    private boolean isDividableByThree(int number) {
        return number % 3 == 0;
    }
}
