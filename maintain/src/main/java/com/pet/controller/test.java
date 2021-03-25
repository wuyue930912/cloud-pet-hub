package com.pet.controller;

class ATest {
    private static ATest aTest;

    private ATest() {
    }

   /* public static ATest getInstance() {
        if (aTest == null) {
            //上锁
            synchronized (ATest.class) {
                //再次判断aTest是否为空
                if (aTest == null) {
                    return aTest = new ATest();
                }
            }
        }
        return aTest;
    }*/

    /*private static ATest bTest = new ATest();

    private ATest(){}

    public static ATest getInstance(){
        return bTest;
    }*/


}
