package edu.iis.mto.serverloadbalancer;

public class   FizzBuzz {

    public String check(int number) {
        String result = "";
        if(isDividableByThree(number)){
            result += "Fizz";
        }
        if (isDividableByFive(number)){
            result += "Buzz";
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
