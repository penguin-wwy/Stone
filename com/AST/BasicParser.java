package com.AST;

import com.Lexer.Lexer;
import com.Lexer.ParseException;
import com.Lexer.Token;
import com.Parser.Parser;

import java.util.HashSet;

/**
 * Created by penguin on 2016/11/28.
 */

public class BasicParser {
    HashSet<String> reserved = new HashSet<String>();
    Parser.Operators operators = new Parser.Operators();
    Parser expr0 = Parser.rule();
    Parser primary = Parser.rule(PrimaryExpr.class).or(Parser.rule().sep("(").ast(expr0).sep(")"),
            Parser.rule().number(NumberLiteral.class),
            Parser.rule().identifier(Name.class, reserved),
            Parser.rule().string(StringLiteral.class));
    Parser factor = Parser.rule().or(Parser.rule(NegativeExpr.class).sep("-").ast(primary),
            primary);
    Parser expr = expr0.expression(BinaryExpr.class, factor, operators);

    Parser statement0 = Parser.rule();
    Parser block = Parser.rule(BlockStmnt.class).sep("{").option(statement0)
            .repeat(Parser.rule().sep(";", Token.EOL).option(statement0)).sep("}");
    Parser simple = Parser.rule(PrimaryExpr.class).ast(expr);
    Parser statement = statement0.or(
            Parser.rule(IfStmnt.class).sep("if").ast(expr).ast(block)
            .option(Parser.rule().sep("else").ast(block)),
            Parser.rule(WhileStmnt.class).sep("while").ast(expr).ast(block),
            simple);

    Parser program = Parser.rule().or(statement, Parser.rule(NullStmnt.class)).sep(";", Token.EOL);

    public BasicParser() {
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=", 1, Parser.Operators.RIGHT);
        operators.add("==", 2, Parser.Operators.LEFT);
        operators.add(">", 2, Parser.Operators.LEFT);
        operators.add("<", 2, Parser.Operators.LEFT);
        operators.add("+", 3, Parser.Operators.LEFT);
        operators.add("-", 3, Parser.Operators.LEFT);
        operators.add("*", 4, Parser.Operators.LEFT);
        operators.add("/", 4, Parser.Operators.LEFT);
        operators.add("%", 4, Parser.Operators.LEFT);
    }

    public ASTree parser(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }
}
