package com.github.norbo11.norbopong.util;

import java.util.Date;
import java.util.Random;

public class NumberHelper
{
    private static Random random = new Random(new Date().getTime());
    
    private static int negativeRandom(int low, int high)
    {
        if (low == high) return low;

        int tmp = low;
        low = high;
        high = tmp;

        int diff = high - low;

        int i = random.nextInt(diff + 1);

        return i + low;
    }
    
    public static int randomNumber(int min, int max)
    {
        boolean negativeInvolved = min < 0 || max < 0;

        if (!negativeInvolved && min > max || negativeInvolved && min < max)
        {
            return -1;
        }
        
        if (negativeInvolved) return negativeRandom(min, max);
        else return (min + (int) (Math.random() * ((max - min) + 1)));
    }
}
