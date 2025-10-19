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


//procedures which are doing more than they supposed to
void BAD_move_square(int[] square, String[] directions){
    boolean moved = false;

    while (!moved) {
        int choice = (int) (Math.random() * 4);
        int limit;
        int shift;

        switch (directions[choice]) {
            case "left":
                if (square[0] == 1) {
                    continue;
                }

                limit = square[0] - 1;
                shift = (int) (Math.random() * limit + 1);
                square[0] -= shift;
                square[2] -= shift;
                moved = true;
                break;

            case "up":
                if (square[1] == 1) {
                    continue;
                }
                limit = square[1] - 1;
                shift = (int) (Math.random() * limit + 1);
                square[1] -= shift;
                square[3] -= shift;
                moved = true;
                break;

            case "right":
                if (square[2] == 120) {
                    continue;
                }

                limit = 120 - square[2];
                shift = (int) (Math.random() * limit + 1);
                square[2] += shift;
                square[0] += shift;
                moved = true;
                break;

            case "down":
                if (square[3] == 30) {
                    continue;
                }

                limit = 30 - square[3];
                shift = (int) (Math.random() * limit + 1);
                square[1] += shift;
                square[3] += shift;
                moved = true;
                break;
        }
    }
}

void BAD_crazy_square(){
    clrscr();
    setfgcolor(white);
    int counter = 0;

    int[] square = {20,20,22,22};
    String[] coordinates = {"left","up","right","down"};

    while (counter < 20){
        ++counter;
        draw_frame_c(square[0], square[1], square[2], square[3], '*');
        delay(200);
        draw_frame_c(square[0], square[1], square[2], square[3], ' ');
        BAD_move_square(square, coordinates);
    }
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
//    setfgcolor(white);
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
//    setfgcolor(white);
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



void crazy_square(int n){
    clrscr();
    setfgcolor(white);
    int counter = 0;

    int[] square = {20,20,22,22};
    String[] coordinates = {"left","up","right","down"};

    while (counter < 20){
//        ++counter;
        draw_frame_c(square[0], square[1], square[2], square[3], '*');
        delay(200);
        draw_frame_c(square[0], square[1], square[2], square[3], ' ');
        move_square(n, square, coordinates);
    }
}

void crazy_squares(int n) {
    clrscr();

    int[] first = {20, 20, 22, 22}; // first four are coordinates of square, last will be storing choice of movement
    int[] second = {20, 20, 22, 22};
    int[] third = {20, 20, 22, 22};
    int counter = 0;

    String[] coordinates = {"left", "up", "right", "down"};

    while (counter < 200) {
        ++counter;

        setfgcolor(red);
        draw_frame_c(first[0], first[1], first[2], first[3], '*');
        setfgcolor(blue);
        draw_frame_c(second[0], second[1], second[2], second[3], '*');
        setfgcolor(green);
        draw_frame_c(third[0], third[1], third[2], third[3], '*');
        delay(200);
        draw_frame_c(first[0], first[1], first[2], first[3], ' ');
        draw_frame_c(second[0], second[1], second[2], second[3], ' ');
        draw_frame_c(third[0], third[1], third[2], third[3], ' ');

        move_square(n, first,coordinates);
        move_square(n, second, coordinates);
        move_square(n, third, coordinates);
    }
}

void planned_squares(){
    clrscr();

    int[] first_x = fill_x_coordinates(new int[15]); // first four are coordinates of square, last will be storing choice of movement
    int[] first_y = fill_y_coordinates(new int[15]); // first four are coordinates of square, last will be storing choice of movement
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

void planned_squares2(){
    clrscr();

    int[] first_x = fill_x_coordinates(new int[8]); // first four are coordinates of square, last will be storing choice of movement
    int[] first_y = fill_y_coordinates(new int[8]); // first four are coordinates of square, last will be storing choice of movement
    int[] second_x = fill_x_coordinates(new int[15]);
    int[] second_y = fill_y_coordinates(new int[15]);
    int[] third_x =fill_x_coordinates(new int[4]);
    int[] third_y = fill_y_coordinates(new int[4]);;
    int i = 0;


    while(true){
        setfgcolor(red);
        draw_frame_c(first_x[i % 8], first_y[i % 8], first_x[i % 8] + 2, first_y[i % 8] + 2, '*');
        setfgcolor(blue);
        draw_frame_c(second_x[i % 15], second_y[i % 15], second_x[i % 15] + 2, second_y[i % 15] + 2, '*');
        setfgcolor(green);
        draw_frame_c(third_x[i % 4], third_y[i % 4], third_x[i % 4] + 2, third_y[i % 4] + 2, '*');
        delay(200);
        draw_frame_c(first_x[i % 8], first_y[i % 8], first_x[i % 8] + 2, first_y[i % 8] + 2, ' ');
        draw_frame_c(second_x[i % 15], second_y[i % 15], second_x[i % 15] + 2, second_y[i % 15] + 2, ' ');
        draw_frame_c(third_x[i % 4], third_y[i % 4], third_x[i % 4] + 2, third_y[i % 4] + 2, ' ');
        ++i;
    }
}

void spiral(int x, int y, char c, int s){

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
//    print("press enter to start ex1.1:");;
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
//    print("press enter to start ex1.2:");;
//    draw_vert_line(10,14, 3);
//    delay(800);
//    draw_vert_line(1,6, 16);
//    delay(800);
//    draw_vert_line(6,3, 3);
//    readln();
//    clrscr();

//    print("press enter to start ex2:");;
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

//    print("press enter to start ex3:");;//    readln();
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
//    print("press enter to start ex4:");;
//    readln();
//    clrscr();
//    crazy_star();
//    clrscr();

//    cursor_hide();
//    print("press enter to start ex5:");
//    readln();
//    clrscr();
//    crazy_square(5);
//    readln();
//    clrscr();

//    cursor_hide();
//    print("press enter to start ex6:");
//    readln();
//    clrscr();
//    crazy_squares(3);
//    readln();
//    clrscr();

//    cursor_hide();
//    print("press enter to start ex7:");
//    readln();
//    clrscr();
//    planned_squares();
//    readln();
//    clrscr();

    cursor_hide();
    print("press enter to start ex8:");
    readln();
    clrscr();
    planned_squares2();
    readln();
    clrscr();
}