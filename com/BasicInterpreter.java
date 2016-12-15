package com;

import com.AST.ASTLeaf;
import com.AST.ASTList;
import com.AST.ASTree;
import com.AST.BasicParser;
import com.AST.BinaryExpr;
import com.AST.BlockStmnt;
import com.AST.IfStmnt;
import com.AST.Name;
import com.AST.NegativeExpr;
import com.AST.NullStmnt;
import com.AST.NumberLiteral;
import com.AST.StringLiteral;
import com.AST.WhileStmnt;
import com.Lexer.CodeDialog;
import com.Lexer.Lexer;
import com.Lexer.ParseException;
import com.Lexer.StoneException;
import com.Lexer.Token;

import java.util.HashMap;
import java.util.List;

import javassist.gluonj.Reviser;

/**
 * Created by penguin on 2016/12/1.
 */

public class BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new BasicParser(), new BasicEnv());
    }
    public static void run(BasicParser bp, Environment env)
        throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parser(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r = ((BasicEvaluator.ASTreeEx)t).eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}
