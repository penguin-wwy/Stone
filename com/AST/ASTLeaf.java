package com.AST;

import com.Environment;
import com.Lexer.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by penguin on 2016/11/24.
 */

public class ASTLeaf extends ASTree {
    private static ArrayList<ASTree> empty = new ArrayList<ASTree>();
    protected Token token;
    public ASTLeaf(Token t) {
        token = t;
    }
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }
    @Override
    public int numChildren() {
        return 0;
    }
    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }
    public String toString() {
        return token.getText();
    }
    @Override
    public String location() {
        return "at line " + token.getLineNumber();
    }
    public Token token() {
        return token;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public ASTree next() {
        return null;
    }

    @Override
    public void remove() {

    }

}
