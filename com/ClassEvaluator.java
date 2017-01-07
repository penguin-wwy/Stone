package com;

import com.AST.ASTree;
import com.AST.ClassBody;
import com.AST.ClassStmnt;
import com.AST.PrimaryExpr;
import com.Lexer.StoneException;

import java.util.List;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;

/**
 * Created by penguin on 2017/1/7.
 */
@Require(FuncEvaluator.class)
public class ClassEvaluator {
    @Reviser
    public static class ClassStmntEx extends ClassStmnt {
        public ClassStmntEx(List<ASTree> c) {
            super(c);
        }
        public Object eval(Environment env) {
            ClassInfo ci = new ClassInfo(this, env);
            ((FuncEvaluator.EnvEx)env).put(name(), ci);
            return name();
        }
    }
    @Reviser
    public static class ClassBodyEx extends ClassBody {
        public ClassBodyEx(List<ASTree> c) {
            super(c);
        }
        public Object eval(Environment env) {
            for (ASTree t : this.children)
                ((BasicEvaluator.ASTreeEx)t).eval(env);
            return null;
        }
    }
    @Reviser
    public static class DotEx extends Dot {
        public DotEx(List<ASTree> c) {
            super(c);
        }
        public Object eval(Environment env, Object value) {
            String member = name();
            if (value instanceof ClassInfo) {
                if ("new".equals(member)) {
                    ClassInfo ci = (ClassInfo)value;
                    NestedEnv e = new NestedEnv(ci.environment());
                    StoneObject so = new StoneObject(e);
                    e.putNew("this", so);
                    initObject(ci, e);
                    return so;
                }
            }
            else if (value instanceof StoneObject) {
                try {
                    return ((StoneObject)value).read(member);
                } catch (StoneObject.AccessException e) {

                }
            }
            throw new StoneException("bad member access: " + member, this);
        }
        protected void initObject(ClassInfo ci, Environment env) {
            if (ci.superClass() != null)
                initObject(ci.superClass(), env);
            ((ClassBodyEx)ci.body()).eval(env);
        }
    }
    @Reviser
    public static class AssignEx extends BasicEvaluator.BinaryEx {
        public AssignEx(List<ASTree> c) {
            super(c);
        }
        @Override
        protected Object computeAssign(Environment env, Object rvalue) {
            ASTree le = left();
            if (le instanceof PrimaryExpr) {
                FuncEvaluator.PrimaryEx p = (FuncEvaluator.PrimaryEx) le;
                if (p.hasPostfix(0) && p.postfix(0) instanceof Dot) {
                    Object t = ((FuncEvaluator.PrimaryEx)le).evalSubExpr(env, 1);
                    if (t instanceof StoneObject)
                        return setField((StoneObject)t, (Dot)p.postfix(0), rvalue);
                }
            }
            return super.computeAssign(env, rvalue);
        }
        protected Object setField(StoneObject obj, Dot expr, Object rvalue) {
            String name = expr.name();
            try {
                obj.write(name, rvalue);
                return rvalue;
            } catch (StoneObject.AccessException e) {
                throw new StoneException("bad member access " + location() + ": " + name);
            }
        }
    }
}
