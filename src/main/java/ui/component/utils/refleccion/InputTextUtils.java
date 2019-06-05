/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ui.component.utils.refleccion;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Angel Navarro
 * Date Jun 19, 2016
 */
public class InputTextUtils extends PlainDocument {
    public final static String DIGITS = "0123456789";
        public final static String LETTERS
                = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public final static String DIGITS_AND_LETTERS = DIGITS + LETTERS;
        public final static int UNRESTRICTED_LENGTH = -1;

        private String acceptedCharacters;
        private int maximumLength = UNRESTRICTED_LENGTH;

        public InputTextUtils(String acceptedCharacters) {
            this.acceptedCharacters = acceptedCharacters;
        }

        public InputTextUtils(String acceptedCharacters, int maximumLength) {
            this.acceptedCharacters = acceptedCharacters;
            setMaximumLength(maximumLength);
        }

        public InputTextUtils(int maximumLength) {
            setMaximumLength(maximumLength);
        }

        public String getAcceptedCharacters() {
            return acceptedCharacters;
        }

        public void setAcceptedCharacters(String acceptedCharacters) {
            this.acceptedCharacters = acceptedCharacters;
        }

        public int getMaximumLength() {
            return maximumLength;
        }

        private void setMaximumLength(int maximumLength) {
            if (maximumLength < UNRESTRICTED_LENGTH || maximumLength == 0) {
                throw new IllegalArgumentException(
                        "The maximum length must be >=1 or UNRESTRICTED_LENGTH.");
            }
            this.maximumLength = maximumLength;
        }

        @Override
        public void insertString(int offset, String string, AttributeSet attributes) throws BadLocationException {
            if (string == null) {
                return;
            }
            // Checks maximum length
            if (maximumLength != UNRESTRICTED_LENGTH && getLength() + string.length() > maximumLength) {
                return;
            }
            // Checks accepted characters
            if (acceptedCharacters != null) {
                for (int i = 0; i < string.length(); i++) {
                    if (acceptedCharacters.indexOf(string.charAt(i)) == -1) {
                        return;
                    }
                }
            }
            // Performs the insert operation
            super.insertString(offset, string, attributes);
        }
}
