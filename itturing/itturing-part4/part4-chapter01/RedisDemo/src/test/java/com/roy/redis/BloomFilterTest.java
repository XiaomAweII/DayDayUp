package com.roy.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * Author： roy
 * Description：
 **/
public class BloomFilterTest {

    public static void main(String[] args) {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),1_000_000,0.01);

        for (int i = 0; i < 1_000_000; i++) {
            bloomFilter.put(i);
        }

        List<Integer> errorList = new ArrayList<>();

        for (int i = 1_000_001; i < 1_100_001; i++) {
            if (bloomFilter.mightContain(i)) {
                errorList.add(i);
            }
        }
        System.out.println("错误个数：" + errorList.size());
    }
}
