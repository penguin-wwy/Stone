package com.Lexer;

import com.AST.ASTree;

/**
 * Created by penguin on 2016/11/22.
 */

public class StoneException extends RuntimeException {
    public StoneException(String m) {
        super(m);
    }
    public StoneException(String m, ASTree t) {
        super(m + " " + t.location());
    }
}
