package com.amazon;

import java.util.PriorityQueue;

public class MedianOfContinuousArray {

    PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>((x, y) -> y - x);
    PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();
    boolean even = true;

    public void add(int number) {
        even = !even;
        if (leftMaxHeap.size() == 0) {
            leftMaxHeap.add(number);
            return;
        }
        if (number > leftMaxHeap.peek()) {
            rightMinHeap.add(number);
        } else {
            leftMaxHeap.add(number);
        }

        if (rightMinHeap.size() > leftMaxHeap.size()) {
            for (int i =0; i < rightMinHeap.size() - leftMaxHeap.size(); i++) {
                leftMaxHeap.add(rightMinHeap.poll());
            }
        }

        if (leftMaxHeap.size() > rightMinHeap.size()+1) {
            for (int i =0; i < leftMaxHeap.size() - rightMinHeap.size(); i++) {
                rightMinHeap.add(leftMaxHeap.poll());
            }
        }
    }

    public float getMedian() {
        if (this.even) {
            return (float) (leftMaxHeap.peek() + rightMinHeap.peek())/2;
        } else {
            return leftMaxHeap.peek();
        }
    }
}


package com.amazon;

import org.junit.Test;

public class TestMedian {

    @Test
    public void test() {
        MedianOfContinuousArray testClass = new MedianOfContinuousArray();

        testClass.add(2);
        System.out.println(testClass.getMedian());
        testClass.add(4);
        System.out.println(testClass.getMedian());
        testClass.add(5);
        System.out.println(testClass.getMedian());
        //testClass.add(1);
        //System.out.println(testClass.getMedian());
        testClass.add(6);
        System.out.println(testClass.getMedian());
        testClass.add(10);
        System.out.println(testClass.getMedian());
        testClass.add(3);
        System.out.println(testClass.getMedian());
    }

}
