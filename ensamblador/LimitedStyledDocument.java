package ensamblador;

import javax.swing.*; 
import javax.swing.text.*; 

import java.awt.Toolkit;

public class LimitedStyledDocument extends DefaultStyledDocument {

    int maxChar;

    public LimitedStyledDocument(int maxChars) {
        maxChar = maxChars;
    }

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException 
    {
        if ((getLength() + str.length()) <= maxChar)
            super.insertString(offs, str, a);
        else{
                  Toolkit.getDefaultToolkit().beep();
        }
    }
}
