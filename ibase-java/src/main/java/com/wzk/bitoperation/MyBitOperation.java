package com.wzk.bitoperation;

import org.junit.Test;

/**
 * @author ivekes
 * @date 2019/06/07
 *
 * 2 、3 、4 和 -4 的二进制如下，本例在此基础上做演示
 * 2 =======>0010
 * 3 =======>0011
 * 4 =======>0100
 * -4=======>1111 1111 1111 1111 1111 1111 1111 1100
 */
public class MyBitOperation {

    /**
     *功能描述: 针对二进制，相同的为0，不同的为1
     * @author ivekes
     */
    @Test
    public void yihuo(){
        System.out.println("2^3运算的结果是 :"+(2^3));
    }

    /**
     *功能描述: 针对二进制，只要有一个为0，就为0
     * @author ivekes
     */
    @Test
    public void yu(){
        System.out.println("2&3运算的结果是 :"+(2&3));
    }

    /**
     *功能描述: 针对二进制，转换成二进制后向左移动3位，后面用0补齐
     * @author ivekes
     */
    @Test
    public void zuoyi(){
        System.out.println("2<<3运算的结果是 :"+(2<<3));
    }

    /**
     *功能描述: 针对二进制，转换成二进制后向右移动3位
     * @author ivekes
     */
    @Test
    public void youyi(){
        System.out.println("2>>3运算的结果是 :"+(2>>3));
    }

    /**
     *功能描述: 针对二进制，无符号右移，忽略符号位，空位都以0补齐
     * @author ivekes
     */
    @Test
    public void wufuhaoyouyi(){
        System.out.println("4>>2运算的结果是 :"+((4)>>2));
        System.out.println("-4>>2运算的结果是 :"+((-4)>>2));
        System.out.println("4>>>2运算的结果是 :"+((4)>>>2));
        System.out.println("-4>>>2运算的结果是 :"+((-4)>>>2));
    }

}
