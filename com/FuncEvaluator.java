package com;

import com.AST.ASTree;
import com.AST.Arguments;
import com.AST.DefStmnt;
import com.AST.ParameterList;
import com.AST.PrimaryExpr;
import com.Lexer.StoneException;

import java.util.List;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;

/**
 * Created by penguin on 2016/12/27.
 */
@Require(BasicEvaluator.class)
@Reviser
public class FuncEvaluator {
    @Reviser public static interface EnvEx extends Environment {
        void putNew(String name, Object value);
        Environment where(String name);
        void setOuter(Environment e);
    }
    @Reviser
    public static class DefStmntEx extends DefStmnt {
        public DefStmntEx(List<ASTree> c) {
            super(c);
        }
        public Object eval(Environment env) {
            ((EnvEx)env).putNew(name(), new Function(parameters(), body(), env));
            return name();
        }
    }
    @Reviser
    public static class PrimaryEx extends PrimaryExpr {
        public PrimaryEx(List<ASTree> e) {
            super(e);
        }
        public ASTree operand() {
            return child(0);
        }
        public Postfix postfix(int nest) {
            return (Postfix)child(numChildren() - nest - 1);
        }
        public boolean hasPostfix(int nest) {
            return numChildren() - nest > 1;
        }
        public Object eval(Environment env) {
            return evalSubExpr(env, 0);
        }
        public Object evalSubExpr(Environment env, int nest) {
            if (hasPostfix(nest)) {
                Object target = evalSubExpr(env, nest + 1);
                return ((PostfixEx)postfix(nest)).eval(env, target);
            }
            else
                return ((BasicEvaluator.ASTreeEx)operand()).eval(env);
        }
    }
    @Reviser
    public static abstract class PostfixEx extends Postfix {
        public PostfixEx(List<ASTree> c) {
            super(c);
        }
        public abstract Object eval(Environment env, Object value);
    }
    @Reviser
    public static class ArgumentsEx extends Arguments {
        public ArgumentsEx(List<ASTree> c) {
            super(c);
        }
        public Object eval(Environment callerEnv, Object value) {
            if (!(value instanceof Function))
                throw new StoneException("bad function", this);
            Function func = (Function)value;
            ParameterList params = func.parameters();
            if (size() != params.size())
                throw new StoneException("bad number of arguments", this);
            Environment newEnv = func.makeEnv();
            int num = 0;
            for (ASTree a : this.children) {
                ((ParamsEx)params).eval(newEnv, num++,
                        ((BasicEvaluator.ASTreeEx)a).eval(callerEnv));
            }
            return ((BasicEvaluator.BlockEx)func.body()).eval(newEnv);
        }
    }
    @Reviser
    public static class ParamsEx extends ParameterList {
        public ParamsEx(List<ASTree> c) {
            super(c);
        }
        public void eval(Environment env, int index, Object value) {
            ((EnvEx)env).putNew(name(index), value);
        }
    }
}
