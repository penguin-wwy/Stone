package com;

import com.AST.BlockStmnt;
import com.AST.ParameterList;

/**
 * Created by penguin on 2016/12/27.
 */

public class Function {
    protected ParameterList parameterList;
    protected BlockStmnt body;
    protected Environment env;
    public Function(ParameterList parameterList, BlockStmnt body, Environment env) {
        this.parameterList = parameterList;
        this.body = body;
        this.env = env;
    }
    public ParameterList parameters() {
        return parameterList;
    }
    public BlockStmnt body() {
        return body;
    }
    public Environment makeEnv() {
        return new NestedEnv(env);
    }
    @Override
    public String toString() {
        return "<func: " + hashCode() + ">";
    }
}
