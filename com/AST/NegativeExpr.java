package com.AST;


import java.util.List;

/**
 * Created by penguin on 2016/11/28.
 */

public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> c) {
        super(c);
    }
    public ASTree operand() {
        return child(0);
    }
    public String toString() {
        return "-" + operand();
    }
}
