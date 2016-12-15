package com.AST;

import java.util.List;

/**
 * Created by penguin on 2016/11/28.
 */

public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> c) {
        super(c);
    }
    public ASTree condition() {
        return child(0);
    }
    public ASTree thenBlock() {
        return child(1);
    }
    public ASTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }
    public String toString() {
        return "(if" + condition() + " " + thenBlock()
                + " else" + elseBlock() + ")";
    }
}
