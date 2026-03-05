
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

void move_square(int n, int[] square, String[] direction){
    boolean moved = false;

    while (!moved){
        String dir = direction[(int)(Math.random()*4)];

        switch (dir){
            case "left":
                if(square[0] - n < 1){continue;}
                square[0] -= n;
                square[2] -= n;
                moved = true;
                break;
            case "up":
                if(square[1] - n < 1){continue;}
                square[1] -= n;
                square[3] -= n;
                moved = true;
                break;
            case "right":
                if(square[2] + n > 120){continue;}
                square[0] += n;
                square[2] += n;
                moved = true;
                break;
            case "down":
                if(square[3] + n > 30){continue;}
                square[1] += n;
                square[3] += n;
                moved = true;
                break;
        }
    }
}

void draw_horiz_line_c(int x1, int x2, int y, char c){
    gotoxy(x1,y);

    while (x1 <= x2){
        print(c);
        ++x1;
        gotoxy(x1,y);
    }
}

void draw_vert_line_c(int x, int y1, int y2, char c){
    gotoxy(x,y1);

    while (y1 <= y2){
        print(c);
        ++y1;
        gotoxy(x,y1);
    }
}

void draw_frame_c(int x1, int y1, int x2, int y2, char c){
    draw_vert_line_c(x1, y1, y2, c);
    draw_vert_line_c(x2, y1, y2, c);
    draw_horiz_line_c(x1, x2, y1, c);
    draw_horiz_line_c(x1, x2, y2, c);
}

void crazy_square(int n){
    clrscr();
    setfgcolor(white);

    int[] square = {20,20,22,22};
    String[] coordinates = {"left","up","right","down"};

    while (true){
        draw_frame_c(square[0], square[1], square[2], square[3], '*');
        delay(200);
        draw_frame_c(square[0], square[1], square[2], square[3], ' ');
        move_square(n, square, coordinates);
    }
}

void main() {

    //example of the usage of procedures defined above
    //to be modified by usage of your procedures

    cursor_hide();
    print("press enter to start ex5:");
    readln();
    clrscr();
    crazy_square(5);
}
