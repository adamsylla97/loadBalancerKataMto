package edu.iis.mto.serverloadbalancer;

public class   FizzBuzz {

    public String check(int number) {
        if(isDividableByThree(number)){
            return "Fizz";
        } else if (isDividableByFive(number)){
            return "Buzz";
        }
        return "aaa";
    }

    private boolean isDividableByFive(int number) {
        return number%5 == 0;
    }

    private boolean isDividableByThree(int number) {
        return number % 3 == 0;
    }
}
