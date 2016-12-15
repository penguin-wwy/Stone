package com.AST;

import java.util.List;

/**
 * Created by penguin on 2016/12/6.
 */

public class DefStmnt extends ASTList {
    public DefStmnt(List<ASTree> c) {
        super(c);
    }
    public String name() {
        return ((ASTLeaf)child(0)).token().getText();
    }
    public ParameterList parameters() {
        return (ParameterList)child(1);
    }
    public BlockStmnt body() {
        return (BlockStmnt)child(2);
    }
    public String toString() {
        return "(def " + name() + " " + parameters() + " " + body() + ")";
    }
}
