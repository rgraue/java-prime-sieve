package src.java;

import java.time.Instant;

// Class to represent the results of a single prime sieve pass
public class Result {
    private final Instant start;
    private final Instant stop;
    private final byte[] primesFound;

    public Result (Instant start, Instant stop, byte[] primesFound) {
        this.start = start;
        this.stop = stop;
        this.primesFound = primesFound;
    }

    public Instant getStart() {
        return this.start;
    }

    public Instant getStop() {
        return this.stop;
    }

    public byte[] getPrimesFound() {
        return this.primesFound;
    }

    public long getTimeDiffMilli() {
        return this.stop.toEpochMilli() - this.start.toEpochMilli();
    }

    public int summarizePrimesFound() {
        var total = 0;
        for (int i = 0; i < this.primesFound.length; i++) {
            if (this.primesFound[i] == 0) {
                total++;
            }
        }

        return total;
    }

    public String toString() {
        return String.format("Found %s primes in %sms", 
            this.summarizePrimesFound(), 
            this.getTimeDiffMilli()
        );
    }
}
