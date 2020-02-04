package scene;

import java.awt.event.KeyEvent;

public enum Key{
    A(KeyEvent.VK_A),
    S(KeyEvent.VK_S),
    W(KeyEvent.VK_W),
    D(KeyEvent.VK_D),
    ESC(KeyEvent.VK_ESCAPE),
    SPACE(KeyEvent.VK_SPACE),
    ARROW_LEFT(KeyEvent.VK_LEFT),
    ARROW_RIGHT(KeyEvent.VK_RIGHT),
    ARROW_UP(KeyEvent.VK_UP),
    ARRPW_DOWN(KeyEvent.VK_DOWN);

    private int code;
    private Key(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
    }
