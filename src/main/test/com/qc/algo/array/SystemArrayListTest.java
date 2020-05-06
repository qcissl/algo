package com.qc.algo.array;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author qinc 2020/05/06 16:18
 */
public class SystemArrayListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(15);
        for(int i = 0; i < 10; i++) {
            list.add(i+1);
        }
        System.out.println(list.remove(-1));
    }
}
