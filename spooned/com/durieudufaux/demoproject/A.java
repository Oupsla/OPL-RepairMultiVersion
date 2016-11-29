package com.durieudufaux.demoproject;


public class A {
    public A() {
    }

    public int methodAFoo() throws java.lang.Exception {
        switch (methodAFoo_version) {
            case 0 :
                return methodAFoo_0();
            case 1 :
                return methodAFoo_1();
            case 2 :
                return methodAFoo_2();
        }
        return methodAFoo_2();
    }

    public int methodAFoo_0() throws java.lang.Exception {
        java.lang.System.out.println("content of methodAFoo v3");
        throw new java.lang.Exception("MethodAFoo v3 is broken");
    }

    public int methodAFoo_1() throws java.lang.Exception {
        java.lang.System.out.println("content of methodAFoo v2");
        return 9;
    }

    public int methodAFoo_2() throws java.lang.Exception {
        java.lang.System.out.println("content of methodAFoo v1");
        return 10;
    }

    public int methodABar(com.durieudufaux.demoproject.B b) throws java.lang.Exception {
        switch (methodABar_version) {
            case 0 :
                return methodABar_0(b);
            case 1 :
                return methodABar_1(b);
            case 2 :
                return methodABar_2(b);
        }
        return methodABar_2(b);
    }

    public int methodABar_0(com.durieudufaux.demoproject.B b) throws java.lang.Exception {
        java.lang.System.out.println("content of methodABar v3");
        return b.methodBJam();
    }

    public int methodABar_1(com.durieudufaux.demoproject.B b) throws java.lang.Exception {
        java.lang.System.out.println("content of methodABar v2");
        return b.methodBJam();
    }

    public int methodABar_2(com.durieudufaux.demoproject.B b) throws java.lang.Exception {
        java.lang.System.out.println("content of methodABar v1");
        return b.methodBJam();
    }

    private static final java.lang.Integer methodABar_version = 0;

    private static final java.lang.Integer methodABar_version_max = 2;

    private static final java.lang.Integer methodAFoo_version = 0;

    private static final java.lang.Integer methodAFoo_version_max = 2;
}

