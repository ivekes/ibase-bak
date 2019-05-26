package com.wzk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestAAA {
    public static void main(String[] args){
        /*int[] ar = new int[]{1, 2, 3, 4, 0, 5};
        IntStream intStream = Arrays.stream(ar).map(one -> one + 5);
        Arrays.stream(ar).flatMap(one -> )
        ar = intStream.toArray();
        for (int i:ar){
            System.out.println(i);
        }*/

        String[] words = new String[]{"Hello","World"};
//        Stream<String> stream = Arrays.stream(words);
        /*Stream<String[]> stream = Arrays.stream(words)
                .map(word -> word.split(""));*/
        List<String> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
//                .distinct()
                .collect(toList());
        a.forEach(System.out::print);
    }
}
