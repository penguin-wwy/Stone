package com.AST;

import java.util.List;

/**
 * Created by penguin on 2016/11/24.
 */

public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> c) {
        super(c);
    }
    public ASTree left() {
        return child(0);
    }
    public String operator() {
        return ((ASTLeaf)child(1)).token().getText();
    }
    public ASTree right() {
        return child(2);
    }
}
