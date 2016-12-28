package com;

import com.AST.ASTree;
import com.Lexer.StoneException;

import java.lang.reflect.Method;

/**
 * Created by penguin on 2016/12/28.
 */

public class NativeFunction {
    protected Method method;
    protected String name;
    protected int numParams;
    public NativeFunction(String name, Method method) {
        this.method = method;
        this.name = name;
        this.numParams = method.getParameterTypes().length;
    }
    @Override
    public String toString() {
        return "<native : " + hashCode() + ">";
    }
    public int numOfParameters() {
        return numParams;
    }
    public Object invoke(Object[] args, ASTree tree) {
        try {
            return method.invoke(null, args);
        } catch (Exception e) {
            throw new StoneException("bad native function call : " + name, tree);
        }
    }
}
