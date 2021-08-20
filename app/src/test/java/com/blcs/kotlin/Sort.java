package com.blcs.kotlin;

import org.junit.Test;
/**
 * @author linweibin
 * @date 2021/8/9
 */
public class Sort {
    @Test
    public void testSort(){

    }

    public void bubbleSort(int[] a){
        boolean hasSwap;
        for (int i=0;i<a.length-1;i++){
            hasSwap = false;
            for (int j=0;j<a.length-1-i;j++){
                if (a[j]>a[j+1]){
                    swap(a[j],a[j+1]);
                    hasSwap = true;
                }
            }
            if (!hasSwap) return;
        }
    }

    public void selectSort(int[] a){
        for (int i=0;i<a.length-1;i++){
            for (int j=i+1;j<a.length;j++){
                if (a[i]>a[j]){
                    swap(a[i],a[j]);
                }
            }
        }
    }

    public void insertSort(int[] a){
        for (int i=1;i<a.length;i++){
            int get = a[i];
            int j = i-1;
            while (j>=0 && a[j]>get){
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = get;
        }
    }

    public void inserts(int[] a){
        int length = a.length;
        while (length!=0){
            int len = length / 2;
            for (int b = 0;b<len;b++){
                for (int i=b+1;i<a.length;i+=len){
                    int get = a[i];
                    int j = i-1;
                    while (j>=0 && a[j]>get){
                        a[j+1] = a[j];
                        j--;
                    }
                    a[j+1] = get;
                }
            }
        }
    }

    private void swap(int x,int y) {
        x = x^y;
        y = x^y;
        x = x^y;
    }


}
