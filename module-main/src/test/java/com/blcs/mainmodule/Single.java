package com.blcs.mainmodule;

import androidx.annotation.NonNull;

/**
 * @author linweibin
 * @date 2021/8/23
 */
public class Single extends A {

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void change(){
        age = 222;
        name = "水电费水电费第三方";
    }
}
