package com.nintynine.memorypond.util;

import java.util.Random;

public class WeightUtil {
    public static int calcWeight(int defaultWeight, int stringLength){
        Random random = new Random();
        int weight = defaultWeight + stringLength * (random.nextInt(1) + 1);
        weight = Math.min(weight, 600);
        return weight;
    }
}
