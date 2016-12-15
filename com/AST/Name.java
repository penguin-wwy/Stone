package com.AST;

import com.Lexer.Token;

/**
 * Created by penguin on 2016/11/24.
 */

public class Name extends ASTLeaf {
    public Name(Token t) {
        super(t);
    }
    public String name() {
        return token().getText();
    }
}
