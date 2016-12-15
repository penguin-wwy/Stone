package com.AST;

import com.Lexer.Token;

/**
 * Created by penguin on 2016/11/24.
 */

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t) {
        super(t);
    }
    public int value() {
        return token().getNumber();
    }
}
