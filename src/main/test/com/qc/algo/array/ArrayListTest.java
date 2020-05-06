package com.qc.algo.array;

/**
 * description
 *
 * @author qinc 2020/05/06 15:06
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(i+1);
        }
        System.out.println(list);
        list.add(7,77);
        System.out.println(list);
        System.out.println(list.size());
    }
}
