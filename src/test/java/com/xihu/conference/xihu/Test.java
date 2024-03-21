package com.xihu.conference.xihu;

/**
 * 功能简述
 *
 * @author hssy
 * @version 1.0
 */
public abstract class Test {

    public static void main(String[] args) {
        int a=1;
        String c= "s";
        new Test(a,c){
            @Override
            void a() {

            }

            @Override
            void b() {

            }
        };
    }

    public int a;

    public String b;

    Test(){}

    Test(int a,String b){
        this();
    }

    abstract void a();

    abstract void b();
}
