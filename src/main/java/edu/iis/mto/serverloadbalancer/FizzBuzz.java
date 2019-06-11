package edu.iis.mto.serverloadbalancer;

public class   FizzBuzz {

    public String check(int number) {
        if(number % 3 == 0){
            return "Fizz";
        } else if (number%5 == 0){
            return "Buzz";
        }
        return "aaa";
    }
}
