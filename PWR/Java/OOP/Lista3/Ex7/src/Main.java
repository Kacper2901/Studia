//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

//add your procedures and functions here
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



void square_control(int posx, int posy){
    int color = 0;
    setfgcolor(colors[color % 3]);
    int pastx, pasty;
    draw_frame_c(posx, posy, '*');

    while(true){
        if(keypressed()){
            String keystr = readkeystr();
            pastx = posx;
            pasty = posy;
            if (keystr.equals("w")){
                posy = guard_pos_y(posy - 1);
            }
            if (keystr.equals("s")){
                posy = guard_pos_y(posy + 1);
            }
            if (keystr.equals("a")){
                posx = guard_pos_x(posx - 1);
            }
            if (keystr.equals("d")){
                posx = guard_pos_x(posx + 1);
            }
            if (keystr.equals("c")){
                color += 1;
                setfgcolor(colors[color % 3]);
                draw_frame_c(posx, posy, '*');
            }
            if (keystr.equals("x")){
                clrscr();
                break;
            }

            draw_frame_c(pastx, pasty, ' ');
            draw_frame_c(posx, posy, '*');

        }
    }
}

void main() {
    setfgcolor(white);
    setbgcolor(black);
    clrscr();
    framexyc(1,1, 121, 31, '#');

    square_control(20,20);


}