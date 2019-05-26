package com.wzk.mylambda;

import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MyLambda {

    /**
     * 线程
     */
    @Test
    public void thread(){
        new Thread(() -> System.out.println("hello world")).start();
    }

    /**
     * 事件
     */
    @Test
    public void event(){
        JButton show =  new JButton("Show");
        show.addActionListener((e) -> System.out.println("hello event"));
    }

    /**
     * 列表迭代
     */
    @Test
    public void foreach(){
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(item -> System.out.println(item));
        features.forEach(System.out::println);
    }

    /**
     * 条件，过滤，断言
     */
    @Test
    public void predicate(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str)-> String.valueOf(str).startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->String.valueOf(str).endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->String.valueOf(str).length() > 4);
    }

    private static void filter(List<String> names,Predicate condition){
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    /**
     * lambda 中应用 predicate
     */
    @Test
    public void predicateInLambda(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        languages.stream().filter(
                startsWithJ.
                        and(fourLetterLong)//or() 和 xor() 方法
        ).forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));
    }


    /**
     * 对集合元素挨个操作
     */
    @Test
    public void map(){
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
    }
    /**
     * 对集合元素挨个操作
     */
    @Test
    public void reduce(){
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost)
                .reduce((sum, cost) -> sum + cost)//sum、cost代表list中的元素，sum+cost表示对元素进行的操作
                .get();
        System.out.println("Total : " + bill);
    }

    /**
     * 流式过滤器
     */
    @Test
    public void filter(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        List<String> filtered = languages.stream().filter(x -> x.length()> 4).collect(Collectors.toList());
        System.out.printf("Original List : %s, \n filtered list : %s %n", languages, filtered);
    }

    /**
     * 计算集合元素的最大值、最小值、总和以及平均值
     * IntSummaryStatistics、LongSummaryStatistics、DoubleSummaryStatistics
     */
    @Test
    public void calculate(){
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }
}
