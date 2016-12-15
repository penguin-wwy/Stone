package com.AST;

import com.Postfix;

import java.util.List;

/**
 * Created by penguin on 2016/12/6.
 */

public class Arguments extends Postfix {
    public Arguments(List<ASTree> c) {
        super(c);
    }
    public int size() {
        return numChildren();
    }
}
