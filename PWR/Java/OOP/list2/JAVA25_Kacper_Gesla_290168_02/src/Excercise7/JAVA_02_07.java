
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

int[] fill_x_coordinates(int[] coordinates){
    for(int i = 0; i < coordinates.length; ++i){
        coordinates[i] = (int)(Math.random()*118 + 1);
    }
    return coordinates;
}

int[] fill_y_coordinates(int[] coordinates){
    for(int i = 0; i < coordinates.length; ++i){
        coordinates[i] = (int)(Math.random()*28 + 1);
    }
    return coordinates;
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


void planned_squares(){
    clrscr();

    int[] first_x = fill_x_coordinates(new int[15]);
    int[] first_y = fill_y_coordinates(new int[15]);
    int[] second_x = fill_x_coordinates(new int[15]);
    int[] second_y = fill_y_coordinates(new int[15]);
    int[] third_x =fill_x_coordinates(new int[15]);
    int[] third_y = fill_y_coordinates(new int[15]);;
    int j = 0;
    int i;

    while(true){
        i = j % 15;
        setfgcolor(red);
        draw_frame_c(first_x[i], first_y[i], first_x[i] + 2, first_y[i] + 2, '*');
        setfgcolor(blue);
        draw_frame_c(second_x[i], second_y[i], second_x[i] + 2, second_y[i] + 2, '*');
        setfgcolor(green);
        draw_frame_c(third_x[i], third_y[i], third_x[i] + 2, third_y[i] + 2, '*');
        delay(200);
        draw_frame_c(first_x[i], first_y[i], first_x[i] + 2, first_y[i] + 2, ' ');
        draw_frame_c(second_x[i], second_y[i], second_x[i] + 2, second_y[i] + 2, ' ');
        draw_frame_c(third_x[i], third_y[i], third_x[i] + 2, third_y[i] + 2, ' ');
        ++j;
    }
}


void main() {
    cursor_hide();
    print("press enter to start ex7:");
    readln();
    clrscr();
    planned_squares();

}
