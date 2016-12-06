package com.durieudufaux.demoproject;


public class B {
    private com.durieudufaux.demoproject.A a;

    public B(com.durieudufaux.demoproject.A a) {
        this.a = a;
    }

    public int methodBTuna() throws java.lang.Exception {
        switch (methodBTuna_version) {
            case 0 :
                return methodBTuna_0();
            case 1 :
                return methodBTuna_1();
            case 2 :
                return methodBTuna_2();
        }
        return methodBTuna_2();
    }

    public int methodBTuna_0() throws java.lang.Exception {
        java.lang.System.out.println("content of methodBTuna v3");
        return a.methodAFoo();
    }

    public int methodBTuna_1() throws java.lang.Exception {
        java.lang.System.out.println("content of methodBTuna v2");
        return a.methodAFoo();
    }

    public int methodBTuna_2() throws java.lang.Exception {
        java.lang.System.out.println("content of methodBTuna v1");
        return a.methodAFoo();
    }

    public int methodBJam() throws java.lang.Exception {
        switch (methodBJam_version) {
            case 0 :
                return methodBJam_0();
            case 1 :
                return methodBJam_1();
            case 2 :
                return methodBJam_2();
        }
        return methodBJam_2();
    }

    public int methodBJam_0() throws java.lang.Exception {
        java.lang.System.out.println("content of methodBJam v3");
        throw new java.lang.Exception("MethodBJam v2 & v3 is broken");
    }

    public int methodBJam_1() throws java.lang.Exception {
        java.lang.System.out.println("content of methodBJam v2");
        throw new java.lang.Exception("MethodBJam v2 is broken");
    }

    public int methodBJam_2() throws java.lang.Exception {
        java.lang.System.out.println("content of methodBJam v1");
        return 1;
    }

    private static final java.lang.Integer methodBJam_version = 0;

    private static final java.lang.Integer methodBJam_version_max = 2;

    private static final java.lang.Integer methodBTuna_version = 0;

    private static final java.lang.Integer methodBTuna_version_max = 2;
}

