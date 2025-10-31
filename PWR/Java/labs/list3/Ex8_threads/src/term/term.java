//Simple wrappers for terminal input/output control with non-blocking keys reading and sound generation
//Some features are not OS independent (e.g. terminal resize does not work in most of Windows terminals, setup them manually to 120x30)
//Author: Maciej Huk
//version 0v3
package Ex8_threads.src.term;

import java.io.IOException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.NonBlockingReader;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import static java.lang.IO.print;

public class term {

    private static Terminal terminal;
    private static NonBlockingReader reader;
    private static ScreenBuffer screen_buffer;

    public static void write(String s) {
        if (s.length()>0) {
            System.out.print(s);
            int posX = screen_buffer.getCursorPosX();  //values set earlier by gotoxy
            int posY = screen_buffer.getCursorPosY();
            for (int i = 0; i < s.length(); i++)
                screen_buffer.putChar(posX + i, posY, s.charAt(i));
            screen_buffer.setCursorPos(posX + s.length(), posY);
        }
    }

    public static void write(char c) {
        System.out.print(c);
        int posX = screen_buffer.getCursorPosX();  //values set earlier by gotoxy
        int posY = screen_buffer.getCursorPosY();
        screen_buffer.putChar(posX,posY,c);
        screen_buffer.setCursorPos(posX+1,posY);
    }

    //resize terminal to given size (not standard, can not work in some terminals, especially in Windows)
    public static void set_term_size(int width, int height) {
        String RESIZE_TERM = "\u001b[8%d;%dt";  //(rows,cols)
        System.out.print(String.format(RESIZE_TERM, height, width));
        screen_buffer.resize(width,height);
    }

    public static void gotoxy(int x, int y) {
        String GOTO_XY = "\u001b[%d;%dH";
        System.out.print(String.format(GOTO_XY, y, x));
        screen_buffer.setCursorPos(x,y);
    }

    public static void clrscr() {
        String CLEAR_SCREEN = "\u001b[2J";
        System.out.print(String.format(CLEAR_SCREEN));
        gotoxy(1,1);
        screen_buffer.clear();
    }

    public static void clrscrc(char c) {
        for (int i = 1; i < 30; i++) {
            gotoxy(1, i);
            for (int j = 1; j < 120; j++) {
                print(c);
                screen_buffer.setCursorPos(j,i);
                screen_buffer.putChar(j,i,c);
            }
        }
    }

    //clear terminal with history
    public static void clrscrh() {
        String CLEAR_SCREEN = "\u001b[3J";
        System.out.print(String.format(CLEAR_SCREEN));
        screen_buffer.clear();
    }

    public static void scrollup(int n) {
        String SCROLL_UP = "\u001b[%dS";
        System.out.print(String.format(SCROLL_UP, n));
    }

    public static void scrolldn(int n) {
        String SCROLL_DN = "\u001b[%dT";
        System.out.print(String.format(SCROLL_DN, n));
    }

    //see: https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit
    //for better colors see: https://forums.powershell.org/t/how-to-use-ansi-vt100-formatting-in-powershell-ooh-pretty-colors/12590
    public static final int black = 0;
    public static final int brown = 1;
    public static final int green = 2;
    public static final int lime = 3;
    public static final int blue = 4;
    public static final int magenta = 5;
    public static final int cyan = 6;
    public static final int ltgrey = 7;
    public static final int grey = 8;
    public static final int red = 9;
    public static final int ltgreen = 10;
    public static final int yellow = 11;
    public static final int white = 15;

    public static void setfgcolor(int n) {
        String SET_FG_COLOR = "\u001b[38;5;%dm";
        System.out.print(String.format(SET_FG_COLOR, n));
        screen_buffer.setFgColor(n);
    }

    public static void setbgcolor(int n) {
        String SET_BG_COLOR = "\u001b[48;5;%dm";
        System.out.print(String.format(SET_BG_COLOR, n));
        screen_buffer.setBgColor(n);
    }

    public static void cursor_hide() {
        String HIDE_CURSOR = "\u001b[?25l";
        System.out.print(String.format(HIDE_CURSOR));
    }

    public static void cursor_show() {
        String SHOW_CURSOR = "\u001b[?25h";
        System.out.print(String.format(SHOW_CURSOR));
    }

    public static void render_screen_buffer() {
        screen_buffer.renderBuffer();
    }

    public static void delay(int msec) {
        try {
            Thread.sleep(msec);
        } catch (InterruptedException e) {
        }
    }

    public static void beep() {
        //Toolkit.getDefaultToolkit().beep(); //java.awt can have problems in linux
        //Toolkit.getDefaultToolkit().sync();
        System.out.print('\007');  //seems to work under win and ubuntu
    }

    public static void sound(int freq, int durationMs) {
        byte[] buf = new byte[2];
        int base_frequency = 44100; //44100 sample points per 1 second
        AudioFormat af = new AudioFormat((float) base_frequency, 16, 1, true, false);
        SourceDataLine sdl = null;
        try {
            sdl = AudioSystem.getSourceDataLine(af);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            sdl.open();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        sdl.start();
        for (int i = 0; i < durationMs * (float) base_frequency / 1000; i++) { //1000 ms in 1 second
            float numberOfSamplesToRepresentFullSin = (float) base_frequency / freq;
            double angle = i / (numberOfSamplesToRepresentFullSin / 2.0) * Math.PI;  // /divide by 2 since sin goes 0PI to 2PI
            short a = (short) (Math.sin(angle) * 32767);  //32767 - max value for sample to take (-32767 to 32767)
            buf[0] = (byte) (a & 0xFF); //write 8bits ________WWWWWWWW out of 16
            buf[1] = (byte) (a >> 8); //write 8bits WWWWWWWW________ out of 16
            sdl.write(buf, 0, 2);
        }
        sdl.drain();
        sdl.stop();
    }

    //for better ASCII-based frames see DEC ("ESC ( 0"/"ESC ( B") at:
    //https://learn.microsoft.com/pl-pl/windows/console/console-virtual-terminal-sequences
    public static void framexyc(int x1, int y1, int x2, int y2, char c) {
        gotoxy(x1, y1);
        for (int i = x1; i <= x2; i++) {
            print(c);
        }
        for (int i = y1; i < y2; i++) {
            gotoxy(x1, i);
            print(c);
            gotoxy(x2, i);
            print(c);
        }
        gotoxy(x1, y2);
        for (int i = x1; i <= x2; i++) {
            print(c);
        }
    }

    public static void framexy(int x1, int y1, int x2, int y2) {
        framexyc(x1, y1, x2, y2, '*');
    }

    public static void wait_for_key(String key_name) {
        while (true) {
            if (keypressed()) {
                String keystr = readkeystr();
                if (keystr.equals(key_name)) {
                    break;
                }
                ;
            }
        }
    }

    //Quickly checks if any keyboard key was perssed
    //and returns true if something was pressed and false otherwise
    //This allows non-blocking reading of the keyboard.
    //For non-blocking work with keyboard it is good keypressed before readkeystr.
    public static boolean keypressed() {
        try {
            if (isKeyPressed()) return true;
                //if (System.in.available()>0) return true;
            else return false;
        } catch (IOException ex) {
            return true;
        }
    }

    public static boolean isKeyPressed() throws IOException {
        int ch = reader.peek(10); // 0 = do not wait
        return ch != NonBlockingReader.READ_EXPIRED;
    }

    public static int readkey() {
        try {
            return reader.read();
        } catch (IOException ex) {
            return 0;
        }
    }

    //performs analysis of keystrokes and returns simple identifiers of keys
    //VT100 standard, write your own version if other standard is accepted by your terminal program
    //   For more intormation - about key pressed/key released events see:
    //   https://github.com/jinput/jinput and
    //   https://jvm-gaming.org/t/jinput-introduction/19402/15
    //   but it is not easy. OS dependent way can be simpler (e.g. in Win use GetAsyncKeyState through JNA)
    //   Happily we do not need information about key release events as our training apps will be simple. :)
    public static String readkeystr() {
        int ch = readkey();
        if (ch == 27) // ESC
        {
            if (keypressed()) ch = readkey();
            if (ch == 79) // ESC O ...
            {
                ch = readkey();
                switch (ch) {
                    case 65:
                        return "arrow_up";
                    case 66:
                        return "arrow_dn";
                    case 67:
                        return "arrow_rt";
                    case 68:
                        return "arrow_lt";
                    case 80:
                        return "f1";
                    case 81:
                        return "f2";
                    case 82:
                        return "f3";
                    case 83:
                        return "f4";
                }
            } else if (ch == 91) // ESC [ ...
            {
                ch = readkey();
                switch (ch) {
                    //case 70: return "end"; // ESC [ F
                    case 72:
                        return "home"; // ESC [ H
                }
                // F5-F12: ESC [ XX ~
                if (ch >= 49 && ch <= 50) {
                    int ch2 = readkey();
                    if (ch2 >= 48 && ch2 <= 57) {
                        int ch3 = readkey();
                        if (ch3 == 126) {
                            if (ch == 49) {
                                switch (ch2) {
                                    case 53:
                                        return "f5";
                                    case 55:
                                        return "f6";
                                    case 56:
                                        return "f7";
                                    case 57:
                                        return "f8";
                                }
                            } else if (ch == 50) {
                                switch (ch2) {
                                    case 48:
                                        return "f9";
                                    case 49:
                                        return "f10";
                                    case 51:
                                        return "f11";
                                    case 52:
                                        return "f12";
                                }
                            }
                        }
                    } else {
                        if ((ch == 50) && (ch2 == 126)) {
                            return "ins";
                        }

                    }
                }
                if (ch == 52 || ch == 51 || ch == 53 || ch == 54) {
                    int ch3 = readkey();
                    if (ch3 == 126) {
                        switch (ch) {
                            case 52:
                                return "end";
                            case 51:
                                return "del";
                            case 53:
                                return "pgup";
                            case 54:
                                return "pgdn";
                        }
                    }
                }
            } else {
                return "esc";
            }
        } else {
            if (ch == 9) return "tab";
            if (ch == 8) return "backspace";
            if (ch == 10 || ch == 13) return "enter";
            if (ch >= 1 && ch <= 26) {                  // Windows Terminal - some Ctrl+X combinations can not work
                char ctrlChar = (char) ('a' + ch - 1);  // if hidden by terminal settings (e.g. Ctrl+avmi[]);
                return "ctrl_" + ctrlChar;              // what typically works: Ctrl+zsbnqwertyuop
            }
            return String.valueOf((char) ch);  // alphanumeric
        }
        return "";
    } //end readkeystr()


    //this block is being run at the beginning even if main() was not called
    //used for initialization of the reader object for non-blocking key reading
    static {
        try {
            terminal = TerminalBuilder.builder()
                    .system(true)
                    .jna(true)
                    .build();
            terminal.enterRawMode();
            reader = terminal.reader();
            screen_buffer = new ScreenBuffer(120,30);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
} //end term class


//separate memory buffer for saving info about printed chars and colors
//It allows to read back the content of the terminal and position of the cursor.
//If used as middle layer between model and terminal then allows preparation of the scene in a backgrounnd.
//This can give results close to double-bufferinig (to minimise flickering).
class ScreenBuffer {
    private int width;
    private int height;
    private char[][] chars;
    private int[][] fgColors;
    private int[][] bgColors;

    private int currentFg = 15; // domyślnie biały
    private int currentBg = 0;  // domyślnie czarne tło

    private int cursorPosX = 1;
    private int cursorPosY = 1;
    private int lastChangeX = -1;
    private int lastChangeY = -1;

    ScreenBuffer(int width, int height) {
        this.width = width;
        this.height = height;
        this.chars = new char[height][width];
        this.fgColors = new int[height][width];
        this.bgColors = new int[height][width];
        clear();
    }

    // Czyści bufor (nie terminal)
    void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                chars[y][x] = ' ';
                fgColors[y][x] = currentFg;
                bgColors[y][x] = currentBg;
            }
        }
        setCursorPos(1,1);
        setLastUpdPos(1,1);
    }

    //saving actual color
    void setFgColor(int fg) {
        this.currentFg = fg;
    }

    void setBgColor(int bg) {
        this.currentFg = bg;
    }

    void setColor(int fg, int bg) {
        setFgColor(fg);
        setBgColor(bg);
    }

    void setCursorPos(int x, int y) {
        cursorPosX = x;
        cursorPosY = y;
    }

    void setLastUpdPos(int x, int y) {
        lastChangeX = x;
        lastChangeY = y;
    }

    void putChar(int x, int y, char c) {
        if (x < 0 || x >= width || y < 0 || y >= height) return;
        chars[y][x] = c;
        fgColors[y][x] = currentFg;
        bgColors[y][x] = currentBg;
        lastChangeX = x;
        lastChangeY = y;
    }

    char getChar(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return ' ';
        return chars[y][x];
    }

    int getFgColor(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return currentFg;
        return fgColors[y][x];
    }

    int getBgColor(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return currentBg;
        return bgColors[y][x];
    }

    int getLastUpdPosX() { return lastChangeX; }
    int getLastUpdPosY() { return lastChangeY; }
    int getCursorPosX() { return cursorPosX; }
    int getCursorPosY() { return cursorPosY; }

    // Writes whole buffer to the screen
    void renderBuffer() {
        for (int y = 0; y < height; y++) {
            term.gotoxy(1, y + 1);
            for (int x = 0; x < width; x++) {
                term.setfgcolor(fgColors[y][x]);
                term.setbgcolor(bgColors[y][x]);
                print(chars[y][x]);
            }
        }
        term.gotoxy(1,1);
        setLastUpdPos(1,1);
    }

    int getWidth()  { return width; }
    int getHeight() { return height; }

    void resize(int newWidth, int newHeight) {
        if (newHeight > chars.length || newWidth > chars[0].length) {
            char[][] newChars = new char[newHeight][newWidth];
            int[][] newFgColors = new int[newHeight][newWidth];
            int[][] newBgColors = new int[newHeight][newWidth];

            // kopiowanie starych wartości tam, gdzie pasuje
            for (int y = 0; y < Math.min(height, newHeight); y++) {
                for (int x = 0; x < Math.min(width, newWidth); x++) {
                    newChars[y][x] = chars[y][x];
                    newFgColors[y][x] = fgColors[y][x];
                    newBgColors[y][x] = bgColors[y][x];
                }
            }

            // wypełnienie nowych pól domyślnymi wartościami
            for (int y = 0; y < newHeight; y++) {
                for (int x = 0; x < newWidth; x++) {
                    if (x >= Math.min(width, newWidth) || y >= Math.min(height, newHeight)) {
                        newChars[y][x] = ' ';
                        newFgColors[y][x] = currentFg;
                        newBgColors[y][x] = currentBg;
                    }
                }
            }

            chars = newChars;
            fgColors = newFgColors;
            bgColors = newBgColors;
        }
        else {
            height = newHeight;
            width = newWidth;
        }
    }

}