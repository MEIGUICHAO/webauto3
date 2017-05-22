package com.example.webtest.Utils;

/**
 * Created by MGC on 17/5/16.
 */

public class ConstantUtils {


    public static int autoSame20 = 27;
    public static int autoSame15 = 34;
    public static int autoSame10 = 44;
    public static int originAmount = 1;
    public static int AllCountCoordinate = 1;

    public static boolean isCUSTOM() {
        return CUSTOM;
    }

    public static void setCUSTOM(boolean CUSTOM) {
        ConstantUtils.CUSTOM = CUSTOM;
    }

    public static boolean CUSTOM = false;



    //blank
    public static int autoBlank20 = 20;
    public static int autoBlank15 = 15;
    public static int autoBlank10 = 10;

    public static int getAutoSame20() {
        return autoSame20;
    }

    public static void setAutoSame20(String num) {
        autoSame20 = Integer.parseInt(num);

    }

    public static int getAutoSame15() {
        return autoSame15;
    }

    public static void setAutoSame15(String num) {
        autoSame15 = Integer.parseInt(num);
    }

    public static int getAutoSame10() {
        return autoSame10;
    }

    public static void setAutoSame10(String num) {
        autoSame10 = Integer.parseInt(num);
    }

    public static int getOriginAmount() {
        return originAmount;
    }

    public static void setOriginAmount(String num) {
        originAmount  = Integer.parseInt(num);
    }

    public static int getAllCountCoordinate() {
        return AllCountCoordinate;
    }

    public static void setAllCountCoordinate(String num) {
        AllCountCoordinate  = Integer.parseInt(num);
    }

    public static int getAutoBlank20() {
        return autoBlank20;
    }

    public static void setAutoBlank20(String num) {
        autoBlank20  = Integer.parseInt(num);
    }

    public static int getAutoBlank15() {
        return autoBlank15;
    }

    public static void setAutoBlank15(String num) {
        autoBlank15  = Integer.parseInt(num);
    }

    public static int getAutoBlank10() {
        return autoBlank10;
    }

    public static void setAutoBlank10(String num) {
        autoBlank10  = Integer.parseInt(num);
    }



}
