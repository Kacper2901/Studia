//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

//add your procedures and functions here
public int guard_pos_x(int x) {
    if (x > 118) return 118;
    else if (x < 2) return 2;
    else return x;
}

public int guard_pos_y(int y){
    if(y < 2) return 2;
    else if(y > 28) return 28;
    else return y;
}

public void draw_frame_c(int x, int y, char c){
    framexyc(x, y, x + 2, y + 2, c);
}

public class TermLock {
    public static final Object DRAW_LOCK = new Object();
}

class Ghost extends Thread{
    public int posx, posy;
    public int pastx, pasty;
    public int color;
    public char c;
    private final String[] directions = {"up", "down", "left", "right"};

    public Ghost(int posx, int posy, char c, int color){
        this.posx = posx;
        this.posy = posy;
        this.c = c;
        this.color = color;
        this.pastx = posx;
        this.pasty = posy;
    }

    @Override
    public void run() {
        while (true){
            synchronized (TermLock.DRAW_LOCK) {
                setfgcolor(this.color);
                draw_frame_c(this.posx, this.posy, this.c);
                delay(500);
                draw_frame_c(this.posx, this.posy, ' ');
            }
            move_ghost(this.posx, this.posy, this.directions);
            delay(20);

        }
    }

    private int getRndNum(int min, int max){
        return (int)(Math.random()*(max - min) + min);
    }

    public void move_ghost(int posx, int posy, String[] direction){
        boolean moved = false;
        int n;

        while (!moved){
            String dir = direction[(int)(Math.random()*4)];

            switch (dir){
                case "left":
                    n = getRndNum(1, posx - 2);
                    if(posx - n < 1){continue;}
                    this.pastx = this.posx;
                    this.posx -= n;
                    moved = true;
                    break;
                case "up":
                    n = getRndNum(1, posy - 2);
                    if(posy - n < 1){continue;}
                    this.pasty = this.posy;
                    this.posy -= n;
                    moved = true;
                    break;
                case "right":
                    n = getRndNum(1, 117 - posx);
                    if(posx + n + 2> 120){continue;}
                    this.pastx = this.posx;
                    this.posx += n;
                    moved = true;
                    break;
                case "down":
                    n = getRndNum(1, 27 - posy);
                    if(posy + n + 2> 30){continue;}
                    this.pasty = this.posy;
                    this.posy += n;
                    moved = true;
                    break;
            }
        }
    }
}



class MainSquare extends Thread{
    public int posx, posy;
    public final int[] colors = {green, blue, red};

    public MainSquare(int posx, int posy){
        this.posx = posx;
        this.posy = posy;
    }

     @Override
    public void run() {
        this.square_control(this.posx, this.posy);
    }

    void square_control(int posx, int posy) {
        cursor_hide();
        int color = 0;
        int pastx, pasty;

        draw_frame_c(posx, posy, '*');


        while (true) {

                if (keypressed()) {
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
                        break;
                    }
                    synchronized (TermLock.DRAW_LOCK) {
                        setfgcolor(colors[color % 3]);
                        draw_frame_c(pastx, pasty, ' ');
                        draw_frame_c(posx, posy, '*');
                    }
                }
                delay(20);

        }
    }
}

void main() {
    setfgcolor(white);
    setbgcolor(black);
    clrscr();
    framexyc(1,1, 121, 31, '#');

    MainSquare mainSquare = new MainSquare(20, 20);
    Ghost ghost1 = new Ghost(80, 20, '$', red);
    Ghost ghost2 = new Ghost(50, 20, '$', yellow);

    mainSquare.start();
    ghost1.start();
    ghost2.start();


}