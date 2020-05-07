package com.qc.algo.array;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        list.addAll(list1);
        System.out.println(list);

        list.stream().filter(i -> i >5).forEach(i -> System.out.println(i));
        Map<Integer,Integer> map = list.stream().collect(Collectors.toMap(Integer::intValue,Integer::intValue,(o,n)->n));
        for(Map.Entry<Integer,Integer> en : map.entrySet()){
            System.out.println(en.getKey()+"-->"+en.getValue());
        }

    }
}
