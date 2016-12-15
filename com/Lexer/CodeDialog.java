package com.Lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Created by penguin on 2016/11/24.
 */

public class CodeDialog extends Reader {
    private String buff = null;
    private int pos = 0;

    public int read(char [] buf, int off, int len) throws IOException {
        if (buff == null) {
            String in = showDialog();
            if  (in == null) {
                return -1;
            }
            else {
                print(in);
                buff = in + '\n';
                pos = 0;
            }
        }
        int size = 0;
        int length = buff.length();
        while (pos < length && size < len) {
            buf[off + size++] = buff.charAt(pos++);
        }
        if (pos == length)
            buff = null;

        return size;
    }

    protected void print(String s) {
        System.out.println(s);
    }

    public void close() throws IOException {

    }

    protected String showDialog() {
        JTextArea area = new JTextArea(20, 40);
        JScrollPane pane = new JScrollPane(area);
        int result = JOptionPane.showOptionDialog(null, pane, "Input", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION)
            return area.getText();
        else
            return null;
    }

    public static Reader file() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return new BufferedReader(new FileReader(chooser.getSelectedFile()));
        }
        else {
            throw new FileNotFoundException("no file specified");
        }
    }
}
