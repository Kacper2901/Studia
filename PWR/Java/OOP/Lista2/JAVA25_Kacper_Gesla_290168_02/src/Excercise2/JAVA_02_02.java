
import static java.lang.IO.*;  //including package to be able to use simple print()

//move cursor position to column x, row y
void gotoxy(int x, int y) {
    String GOTO_XY = "\u001b[%d;%dH";
    print(String.format(GOTO_XY, y, x));
}

//clear the terminal window
void clrscr() {
    String CLEAR_SCREEN = "\u001b[2J";
    print(String.format(CLEAR_SCREEN));
}

void cursor_hide() {
    String HIDE_CURSOR = "\u001b[?25l";
    print(String.format(HIDE_CURSOR));
}

void cursor_show() {
    String SHOW_CURSOR = "\u001b[?25h";
    print(String.format(SHOW_CURSOR));
}

void delay(int msec) {
    try {
        Thread.sleep(msec);
    } catch (InterruptedException e) {}
}

//constants with identifiers of basic colors
//see: https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit
final int black = 0;
final int brown = 1;
final int green = 2;
final int yellow = 3;
final int blue = 4;
final int magenta = 5;
final int cyan = 6;
final int ltgrey = 7;
final int grey = 8;
final int red = 9;
final int ltgreen = 10;
final int ltyellow = 11;
final int ltblue = 12;
final int white = 15;

//set text foreground color
void setfgcolor(int n) {
    String SET_FG_COLOR = "\u001b[38;5;%dm";
    print(String.format(SET_FG_COLOR,n));
}

//set text background color
void setbgcolor(int n) {
    String SET_BG_COLOR = "\u001b[48;5;%dm";
    print(String.format(SET_BG_COLOR,n));
}

//add your procedures here

void draw_horiz_line(int x1, int x2, int y){
    gotoxy(x1,y);
    setfgcolor(white);
    while (x1 <= x2){
        print("*");
        ++x1;
        gotoxy(x1,y);
    }
}

void draw_vert_line(int x, int y1, int y2){
    gotoxy(x,y1);
    setfgcolor(white);
    while (y1 <= y2){
        print("*");
        ++y1;
        gotoxy(x,y1);
    }
}

void draw_frame(int x1, int y1, int x2, int y2){
    draw_vert_line(x1, y1, y2);
    draw_vert_line(x2, y1, y2);
    draw_horiz_line(x1, x2, y1);
    draw_horiz_line(x1, x2, y2);
}


void main() {

    print("press enter to start ex2:");;
    readln();
    clrscr();
    cursor_hide();
    draw_frame(2,5,8, 10);
    delay(500);
    draw_frame(11, 11,22,22);
    delay(500);
    draw_frame(30,10, 35,23);
    readln();
    clrscr();
    cursor_show();

}
