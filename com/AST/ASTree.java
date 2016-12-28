package com.AST;

import com.Environment;

import java.util.Iterator;

/**
 * Created by penguin on 2016/11/24.
 */

public abstract class ASTree implements Iterator<ASTree> {
    public abstract ASTree child(int i);
    public abstract int numChildren();
    public abstract Iterator<ASTree> children();
    public abstract String location();
    public Iterator<ASTree> iterator() {
        return children();
    }
}
