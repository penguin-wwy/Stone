package com;

import com.AST.ClassParser;
import com.Lexer.ParseException;

/**
 * Created by penguin on 2017/1/7.
 */

public class ClassInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClassParser(), new Natives().environment(new NestedEnv()));
    }
}
