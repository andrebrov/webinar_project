package org.andrebrov.testcases.seleniumtwo.common;

/**
 * Author: Andrey Rebrov &lt;andrey.rebrov@ubs.com>
 */
//Simple object to store pair of objects
public class Pair {

    private Object first;
    private Object second;

    public Pair(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public Object getFirst() {
        return first;
    }

    public void setFirst(Object first) {
        this.first = first;
    }

    public Object getSecond() {
        return second;
    }

    public void setSecond(Object second) {
        this.second = second;
    }
}
