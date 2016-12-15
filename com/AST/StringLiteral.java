package com.AST;

import com.Lexer.Token;

/**
 * Created by penguin on 2016/11/28.
 */

public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token t) {
        super(t);
    }
    public String value() {
        return token().getText();
    }
}
