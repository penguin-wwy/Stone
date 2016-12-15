package com.AST;

import java.util.List;

/**
 * Created by penguin on 2016/11/28.
 */

public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> c) {
        super(c);
    }
    public ASTree condition() {
        return child(0);
    }
    public ASTree body() {
        return child(1);
    }
    public String toString() {
        return "(while" + condition() + " " + body() + ")";
    }
}
