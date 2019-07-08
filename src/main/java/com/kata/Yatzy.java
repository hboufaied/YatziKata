package com.kata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Yatzy {

    public static int chance(int d1, int d2, int d3, int d4, int d5)
    {
    	return IntStream.of(d1, d2, d3, d4, d5).sum();
    }

    public static int yatzy(int... dice)
    {
        int[] counts = new int[6];
        for (int die : dice)
            counts[die-1]++;
        for (int i = 0; i != 6; i++)
            if (counts[i] == 5)
                return 50;
        return 0;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return IntStream.of(d1, d2, d3, d4, d5).filter(x -> x == 1).sum();
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        return IntStream.of(d1, d2, d3, d4, d5).filter(x -> x == 2).sum();
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return IntStream.of(d1, d2, d3, d4, d5).filter(x -> x == 3).sum();
    }

    protected int[] dice;
    public Yatzy(int d1, int d2, int d3, int d4, int _5)
    {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = _5;
    }

    public int fours()
    {
    	return Arrays.stream(dice).filter(x -> x == 4).sum();
    }

    public int fives()
    {
    	return Arrays.stream(dice).filter(x -> x == 5).sum();
    }

    public int sixes()
    {    	
    	return Arrays.stream(dice).filter(x -> x == 6).sum();
    }

	public static List<Integer> findPairs(List<Integer> integers, int occurance) {
		final List<Integer> allExistingPairs = new ArrayList<>();
		integers.stream().filter(i -> Collections.frequency(integers, i) > occurance)
				.collect(Collectors.toSet())
				.forEach(allExistingPairs::add);
		return allExistingPairs;
	}
	
	public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
		List<Integer> integers = Arrays.asList(d1, d2, d3, d4, d5);
		return findPairs(integers, 1).stream().max(Comparator.comparing(Integer::valueOf)).get() * 2;

	}

	public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
		List<Integer> integers = Arrays.asList(d1, d2, d3, d4, d5);
		return findPairs(integers, 1).stream().mapToInt(Integer::valueOf).sum() * 2;
	}

	public static int four_of_a_kind(int _1, int _2, int d3, int d4, int d5) {
		List<Integer> integers = Arrays.asList(_1, _2, d3, d4, d5);
		return findPairs(integers, 3).stream().mapToInt(Integer::valueOf).sum() * 4;
	}

	public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
		List<Integer> integers = Arrays.asList(d1, d2, d3, d4, d5);
		return findPairs(integers, 2).stream().mapToInt(Integer::valueOf).sum() * 3;
	}

	public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
		List<Integer> integers = Arrays.asList(d1, d2, d3, d4, d5);
		List<Integer> exactDice = findPairs(integers, 0).stream().filter(a -> a != 6).collect(Collectors.toList());
		if (exactDice.size() == 5) {
			return 15;
		}
		return 0;
	}

	public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
		List<Integer> integers = Arrays.asList(d1, d2, d3, d4, d5);
		List<Integer> exactDice = findPairs(integers, 0).stream().filter(a -> a != 1).collect(Collectors.toList());
		if (exactDice.size() == 5) {
			return 20;
		}
		return 0;
	}

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
		List<Integer> integers = Arrays.asList(d1, d2, d3, d4, d5);

		List<Integer> twoOfKind = findPairs(integers, 1);
		List<Integer> threeOfKind = findPairs(integers, 2);
		Set<Integer> result = Stream.concat(twoOfKind.stream(), threeOfKind.stream()).distinct()
				.collect(Collectors.toSet());
		// one of two kind and one of three kind
		if (result.size() == 2) {
			IntStream stream = IntStream.of(d1, d2, d3, d4, d5);
			return stream.sum();
		}
		return 0;
	}
}