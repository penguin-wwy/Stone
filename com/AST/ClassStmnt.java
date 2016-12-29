package com.AST;

import java.util.List;

/**
 * Created by penguin on 2016/12/29.
 */

public class ClassStmnt extends ASTList {
    public ClassStmnt(List<ASTree> c) {
        super(c);
    }
    public String name() {
        return ((ASTLeaf)child(0)).token().getText();
    }
    public String superClass() {
        if (numChildren() < 3) {
            return null;
        } else {
            return ((ASTLeaf)child(1)).token().getText();
        }
    }
    public ClassBody body() {
        return (ClassBody)child(numChildren() - 1);
    }
    public String toString() {
        String parent = superClass();
        if (parent == null) {
            parent = "*";
        }
        return "(class " + name() + " " + parent + " " + body() + ")";
    }
}
