package org.achumakin;

public class PrimeNumber {

    public static boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }

        for (int i = 2; i <= num / 2; i++) {
            if ((num % i) == 0)
                return false;
        }

        return true;
    }

}
