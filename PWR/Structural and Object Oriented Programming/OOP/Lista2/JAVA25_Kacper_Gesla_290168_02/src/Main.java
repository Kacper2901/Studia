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
    if (x2 < x1){
        int temp = x1;
        x1 = x2;
        x2 = temp;
    }
    gotoxy(x1,y);
    setfgcolor(white);
    while (x1 <= x2){
        print("*");
        ++x1;
        gotoxy(x1,y);
    }
}

void draw_vert_line(int x, int y1, int y2){
    if (y2 < y1){
        int temp = y1;
        y1 = y2;
        y2 = temp;
    }
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

void draw_horiz_line_c(int x1, int x2, int y, char c){
    if (x2 < x1){
        int temp = x1;
        x1 = x2;
        x2 = temp;
    }
    gotoxy(x1,y);
    setfgcolor(white);
    while (x1 <= x2){
        print(c);
        ++x1;
        gotoxy(x1,y);
    }
}

void draw_vert_line_c(int x, int y1, int y2, char c){
    if (y2 < y1){
        int temp = y1;
        y1 = y2;
        y2 = temp;
    }
    gotoxy(x,y1);
    setfgcolor(white);
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

void crazy_star(){
    clrscr();
    setfgcolor(white);
    int counter = 0;
    int x = 20;
    int y = 20;
    while (counter < 10){
        gotoxy(x, y);
        print("*");
        delay(500);
        gotoxy(x, y);
        print(" ");
        x = (int)((Math.random()*120) + 1);
        y = (int)((Math.random()*30) + 1);
        ++counter;
    }
}

void crazy_square(){
    clrscr();
    setfgcolor(white);

    int counter = 0;
    int x1 = 20;
    int y1 = 20;
    int x2 = 22;
    int y2 = 22;
    int x_limit;
    int y_limit;
    int shift;

    final String[] coordinates = {"left", "up", "right", "down"};

    while (counter < 10){
        ++counter;
        draw_frame_c(x1, y1, x2, y2, '*');
        delay(3000);
        draw_frame_c(x1, y1, x2, y2, ' ');

        int choice = (int)(Math.random()*3);
        switch (choice){
            case 0:
                x_limit = x1;
                shift = (int)(Math.random()*x_limit);
                x1 -= shift;
                x2 -= shift;
                break;
            case 1:
                y_limit = y1;
                shift = (int)(Math.random()*y_limit);
                y1 -= shift;
                y2 -= shift;
                break;
            case 2:
                x_limit = 120 - x2 + 1;
                shift = (int)(Math.random()*x_limit);
                x1 += shift;
                x2 += shift;
                break;
            case 3:
                y_limit = 30 - y2 + 1;
                shift = (int)(Math.random()*y_limit);
                y1 += shift;
                y2 += shift;
                break;
        }
    }
}


void main() {
     
    //example of the usage of procedures defined above
    //to be modified by usage of your procedures

//    cursor_hide();
//    clrscr();
//    setbgcolor(black);
//    gotoxy(4,3);
//    setfgcolor(white);
//    print("Welcome to textual terminal graphic!");
//    delay(800);
//
//    gotoxy(15,15);
//    setfgcolor(red);
//    print("RED ");
//    delay(800);
//    setfgcolor(ltgreen);
//    print("GREEN ");
//    delay(800);
//    setfgcolor(blue);
//    print("BLUE ");
//
//    gotoxy(2,25);
//    setfgcolor(white);
//    print("Press Enter to end the program...");
//    readln();
//
//    cursor_show();
//    clrscr();
//    gotoxy(1,1);

    //zad1 horizontal
//    print("zadanie 1: ")
//    readln();
//    cursor_hide();
//    clrscr();
//    draw_horiz_line(1,5,8);
//    delay(800);
//    draw_horiz_line(9,2,5);
//    delay(800);
//    draw_horiz_line(7,7,10);
//    delay(800);
//    draw_horiz_line(4,1,11);
//    delay(800);
//
//
//    //zad1 vertical
//    draw_vert_line(10,14, 3);
//    delay(800);
//    draw_vert_line(1,6, 16);
//    delay(800);
//    draw_vert_line(6,3, 3);
//    readln();
//    clrscr();

//    print("zadanie2");
//    readln();
//    clrscr();
//    cursor_hide();
//    draw_frame(2,5,8, 10);
//    readln();
//    clrscr();
//    draw_frame(5,5,6,6);
//    readln();
//    clrscr();
//    draw_frame(10,11,3,4);
//    readln();
//    clrscr();

//    print("zadanie3");
//    readln();
//    clrscr();
//    cursor_hide();
//    draw_frame_c(2,5,8, 10, 'c');
//    readln();
//    clrscr();
//    draw_frame_c(5,5,6,6, '$');
//    readln();
//    clrscr();
//    draw_frame_c(10,11,3,4, '+');
//    readln();
//    clrscr();

//    cursor_hide();
//    print("zadanie4: ");
//    readln();
//    clrscr();
//    crazy_star();
//    clrscr();

    cursor_hide();
    print("zadanie5: ");
    readln();
    clrscr();
    crazy_square();
    readln();
    clrscr();
}