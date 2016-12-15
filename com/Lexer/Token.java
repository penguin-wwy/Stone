package com.Lexer;

/**
 * Created by penguin on 2016/11/22.
 */

public abstract class Token {
    public static final Token EOF = new Token(-1){};    //end the file
    public static final String EOL = "\\n";             //end the line
    private int lineNumber;

    protected Token(int line) {
        lineNumber = line;
    }

    public int getLineNumber() {
        return lineNumber;
    }
    public boolean isIdentifier() {
        return false;
    }
    public boolean isNumber() {
        return false;
    }
    public boolean isString() {
        return false;
    }
    public int getNumber() {
        throw new StoneException("not number token");
    }
    public String getText() {
        return "";
    }
}
