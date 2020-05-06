package com.qc.algo.array;

import java.util.ArrayList;

/**
 * description
 *
 * @author qinc 2020/05/06 15:06
 */
public class MyArrayListTest {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(i+1);
        }
        System.out.println(list);

        ArrayList<Integer> list1 = new ArrayList<>(10);
        list1.add(51);
        list1.add(3);
        list1.add(5);
        list.removeAll(list1);
        System.out.println(list);
    }
}
