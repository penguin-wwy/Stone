package com;

import com.AST.ASTLeaf;
import com.AST.ASTree;

import java.util.List;

/**
 * Created by penguin on 2016/12/29.
 */

public class Dot extends Postfix {
    public Dot(List<ASTree> c) {
        super(c);
    }
    public String name() {
        return ((ASTLeaf)child(0)).token().getText();
    }
    public String toString() {
        return "." + name();
    }
}
