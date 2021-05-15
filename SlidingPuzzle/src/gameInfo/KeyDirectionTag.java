package gameInfo;

public enum KeyDirectionTag {


    /**
     * this game has got two keyboard layout because of this,
     * getTag method defined to depend on your keyboard layout
     * return direction tag.
     */

    UP('U', 'W'),
    DOWN('D', 'S'),
    LEFT('L', 'A'),
    RIGHT('R', 'D'),
    NO_DIRECTION('0', '0'),
    BACK('B', 'B');


    private char defaultDirectionChar;
    private char WSADDirectionChar;

    private KeyDirectionTag(char defaultDirectionChar, char WSADDirectionChar) {
        this.defaultDirectionChar = defaultDirectionChar;
        this.WSADDirectionChar = WSADDirectionChar;
    }

    private char getWSADTag() {
        return this.WSADDirectionChar;
    }

    private char getDefaultTag() {
        return this.defaultDirectionChar;
    }

    public char getTag(KeyboardLayout keyboardLayout) {

        if (keyboardLayout == KeyboardLayout.DEFAULT)
            return getDefaultTag();
        else
            return getWSADTag();
    }

}