package com;

import com.AST.ASTree;
import com.Lexer.StoneException;

import java.util.List;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;

/**
 * Created by penguin on 2016/12/28.
 */

@Require(FuncEvaluator.class)
@Reviser
public class NativeEvaluator {
    @Reviser
    public static class NativeArgEx extends FuncEvaluator.ArgumentsEx {
        public NativeArgEx(List<ASTree> c) {
            super(c);
        }
        @Override
        public Object eval(Environment callerEnv, Object value) {
            if (!(value instanceof NativeFunction))
                return super.eval(callerEnv, value);

            NativeFunction func = (NativeFunction)value;
            int nparams = func.numOfParameters();
            if (size() != nparams)
                throw new StoneException("bad number of arguments", this);
            Object[] args = new Object[nparams];
            int num = 0;
            for (ASTree a : this.children) {
                args[num++] = ((BasicEvaluator.ASTreeEx)a).eval(callerEnv);
            }
            return func.invoke(args, this);
        }
    }
}
