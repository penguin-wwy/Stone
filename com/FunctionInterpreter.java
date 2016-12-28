package com;

import com.AST.FuncParser;
import com.Lexer.ParseException;

/**
 * Created by penguin on 2016/12/28.
 */

public class FunctionInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new FuncParser(), new NestedEnv());
    }
}
