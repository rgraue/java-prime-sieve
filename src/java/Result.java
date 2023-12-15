package src.java;

import java.time.Instant;

// Represent the results of a single prime sieve pass
public class Result {
    private final Instant start;
    private final Instant stop;
    private final int primesFound;

    public Result (Instant start, Instant stop, int primesFound) {
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

    public int getPrimesFound() {
        return this.primesFound;
    }

    public long getTimeDiffMilli() {
        return this.stop.toEpochMilli() - this.start.toEpochMilli();
    }

    public String toString() {
        return String.format("Found %s primes in %sms", 
            this.getPrimesFound(), 
            this.getTimeDiffMilli()
        );
    }
}
