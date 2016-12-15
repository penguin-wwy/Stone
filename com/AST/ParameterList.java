package com.AST;

import java.util.List;

/**
 * Created by penguin on 2016/12/6.
 */

public class ParameterList extends ASTList {
    public ParameterList(List<ASTree> c) {
        super(c);
    }
    public String name(int i) {
        return ((ASTLeaf)child(i)).token().getText();
    }
    public int size() {
        return numChildren();
    }
}
