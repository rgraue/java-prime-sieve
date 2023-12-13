package src.java;

import java.time.Instant;

public class PrimeSieve {
    private final static int CEILING = 1000000001; // ends with 1 cuz using indexes for num vals.
    private final static boolean DEBUG = false; // set true to see every info
    public static void main (String[] args) {
        byte[] nums = new byte[CEILING];

        // benchmark start
        Instant start = Instant.now();

        // do the thing!
        findPrimes(nums);

        // benchmark stop
        Instant stop = Instant.now();
        System.out.println(Summary(nums, start, stop));
    }

    // controller to find primes up to CEILING
    private static void findPrimes(byte[] nums) {
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i < CEILING / 2; i++) {
            if (nums[i] == 0) { // if not marked as non-prime, it must be prime
                markMultiples(nums, i); // then mark all multiples of it
            }
        }
    }

    // Mark all multiples of instance up to CEILING
    // none of those will not be primes
    private static void markMultiples(byte[] nums, int n) {
        for (int i = n * 2; i < CEILING; i+= n) {
            nums[i] = 1;
        }
    }

    // fancy DEBUG logging
    private static String arrToString(byte[] nums) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < CEILING; i++) {
            if (DEBUG || nums[i] == 0)
            s.append(String.format("[%s = %s]", i, nums[i]));
        }

        return s.toString();
    }

    // provide summary of pass
    // dont care ab speed here. happen post testing.
    private static String Summary(byte[] nums, Instant start, Instant stop) {
        int total = 0;
        var diff = stop.toEpochMilli() - start.toEpochMilli();
        for (int i = 0; i < CEILING; i++) {
            if (nums[i] == 0) {
                total++;
            }
        }

        return String.format("Found %s primes in %s milliseconds", total, diff);
    }
}
