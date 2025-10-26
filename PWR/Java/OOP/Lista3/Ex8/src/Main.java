//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

//add your procedures and functions here
private int getRndNum(int min, int max){
    return (int)(Math.random()*(max - min) + min);
}

int guard_pos_x(int x) {
    if (x > 118) return 118;
    else if (x < 2) return 2;
    else return x;
}

int guard_pos_y(int y){
    if(y < 2) return 2;
    else if(y > 28) return 28;
    else return y;
}

void draw_frame_c(int x, int y, char c){
    framexyc(x, y, x + 2, y + 2, c);
}

final int[] colors = {green, blue, red};
final String[] DIRECTION = {"up", "down", "left", "right"};

public void move_ghost(int[] curr, int[] past, String[] direction){
    boolean moved = false;
    int n;



    while (!moved){
        String dir = direction[(int)(Math.random()*4)];

        switch (dir){
            case "left":
                n = getRndNum(1, curr[0] - 2);
                if(curr[0] - n < 1){continue;}
                past[0] = curr[0];
                curr[0] -= n;
                moved = true;
                break;
            case "up":
                n = getRndNum(1, curr[1] - 2);
                if(curr[1] - n < 1){continue;}
                past[1] = curr[1];
                curr[1] -= n;
                moved = true;
                break;
            case "right":
                n = getRndNum(1, 117 - curr[0]);
                if(curr[0] + n + 2> 120){continue;}
                past[0] = curr[0];
                curr[0] += n;
                moved = true;
                break;
            case "down":
                n = getRndNum(1, 27 - curr[1]);
                if(curr[1] + n + 2> 30){continue;}
                past[1] = curr[1];
                curr[1] += n;
                moved = true;
                break;
        }

    }
}


void square_control(int posx, int posy){
    int color = 0;
    int pastx, pasty;
    long loops = 0;
    int[] ghost1 = {50, 20};
    int[] ghost1_past = {50, 20};
    int[] ghost2 = {30, 10};
    int[] ghost2_past = {30, 10};

    setfgcolor(grey);
    draw_frame_c(ghost1[0], ghost1[1], '$');
    setfgcolor(yellow);
    draw_frame_c(ghost2[0], ghost2[1], '$');

    setfgcolor(colors[color % 3]);
    draw_frame_c(posx, posy, '*');




    while(true){
        if(keypressed()) {
            String keystr = readkeystr();
            pastx = posx;
            pasty = posy;
            if (keystr.equals("w")) {
                posy = guard_pos_y(posy - 1);
            }
            if (keystr.equals("s")) {
                posy = guard_pos_y(posy + 1);
            }
            if (keystr.equals("a")) {
                posx = guard_pos_x(posx - 1);
            }
            if (keystr.equals("d")) {
                posx = guard_pos_x(posx + 1);
            }
            if (keystr.equals("c")) {
                color += 1;
                setfgcolor(colors[color % 3]);
                draw_frame_c(posx, posy, '*');
            }
            if (keystr.equals("x")) {
                clrscr();
                print(loops);
                break;
            }
            setfgcolor(colors[color % 3]);
            draw_frame_c(pastx, pasty, ' ');
            draw_frame_c(posx, posy, '*');
        }
            delay(20);
            if (loops % 10 == 0) {
                setfgcolor(yellow);
                draw_frame_c(ghost1_past[0], ghost1_past[1], ' ');
                move_ghost(ghost1, ghost1_past, DIRECTION);
                draw_frame_c(ghost1_past[0], ghost1_past[1], '$');

                setfgcolor(grey);
                draw_frame_c(ghost2_past[0], ghost2_past[1], ' ');
                move_ghost(ghost2, ghost2_past, DIRECTION);
                draw_frame_c(ghost2_past[0], ghost2_past[1], '$');

            }
            loops += 1;

    }
}

void main() {
    setfgcolor(white);
    setbgcolor(black);
    clrscr();
    framexyc(1,1, 121, 31, '#');

    square_control(20,20);

}