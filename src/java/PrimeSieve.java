package src.java;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrimeSieve {
    private final static int RUNS = 1000;
    private final static int CEILING = 10000001; // ends with 1 cuz using indexes for num vals.
    private final static int THREADS = 32;
    public static void main (String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        List<Callable<Result>> tasks = new ArrayList<>();

        Callable<Result> findPrimeRunController = () -> {
            byte[] nums = new byte[CEILING];
            var start = Instant.now();
            var found = findPrimes(nums);
            var stop = Instant.now();
            return new Result(start, stop, found);
        };

        // populate executor
        for (int i = 0; i < RUNS; i++) {
            tasks.add(findPrimeRunController);
        }

        int totalPassed = 0;
        List<Long> allTimes = new ArrayList<Long>();
        try {
            System.out.println(String.format("Finding primes up to %s, %s times", CEILING-1, RUNS));

            // do the thing
            List<Future<Result>> results = executor.invokeAll(tasks);

            for (int i = 0; i < results.size(); i++) {
                totalPassed++;
                allTimes.add(results.get(i).get().getTimeDiffMilli());
            }
        } catch (InterruptedException e) {
            System.err.println("interrupt" + e.getMessage());
        } catch (ExecutionException e) {
            System.err.println("execution" + e.getMessage());
        } finally {
            System.out.println(String.format("Successful passes: %s", totalPassed));
        }
        
        executor.shutdown();
        System.out.println(summarizeTimeResults(allTimes));

    }

    // controller to find primes up to CEILING
    private static int findPrimes(byte[] nums) {
        int totalFound = 0;
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i < CEILING / 2; i++) {
            if (nums[i] == 0) { // if not marked as non-prime, it must be prime
                totalFound++;
                markMultiples(nums, i); // then mark all multiples of it
            }
        }
        return totalFound;
    }

    // Mark all multiples of instance up to CEILING
    // none of those will not be primes
    private static void markMultiples(byte[] nums, int n) {
        for (int i = n * 2; i < CEILING; i+= n) {
            nums[i] = 1;
        }
    }

    private static String summarizeTimeResults(List<Long> times){
        double total = 0;
        Long min = times.get(0);
        Long max = times.get(0);

        for (int i = 0; i < times.size(); i++) {
            var val = times.get(i);
            total+=val;

            // find min
            if (val < min) {
                min = val;
            }

            // find max
            if (val > max) {
                max = val;
            }
        }

        double mean = total / times.size();

        return String.format("Avg time : %sms\nMax time : %sms\nMin time : %sms", 
            mean,
            max,
            min
        );
    }
}

