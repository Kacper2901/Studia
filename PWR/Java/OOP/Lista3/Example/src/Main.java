//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

//add your procedures and functions here


void main() {
    //The example code showing basic non-blocking processing of the keyboard
    //Non-blocking means we do not need to wait if nothing is being pressed
    //Change this code to present your solutions.

    //Remark
    // Example code given below is intentionally not divided into subroutines.
    // This is because you should be able to understand also such 'messy' code.
    // Comments suggest which blocks of the code could be moved to subroutines.


    //1. Preparation of the scene
    cursor_hide();
    clrscr();
    setfgcolor(white);
    framexy(1,1,120,30);
    gotoxy(32,30);
    write("   Use 'a' and 'd' to move a star. Use 'x' to exit   ");
    gotoxy(28,1);
    write("   Java Lab 3, initial example (keyboard control, delay, sound)  ");

    framexy(25,5,30,25);
    framexy(90,5,95,25);
    int minx = 30;
    int maxx = 90;
    setbgcolor(black);

    cursor_hide();
    int posx = 65;
    int posy = 15;
    int last_posx;
    int ball_color = ltgreen;
    int time_color = red;

    gotoxy(posx,posy);   //draw controlled object
    setfgcolor(ball_color);
    print('*');

    boolean hit_left = false;            //flags to control which wall was hit last time
    boolean hit_right = false;

    long time_start = System.nanoTime();

    //2. main loop for listening and processing keyboard events
    while (true) {
        if ( keypressed()) {              //check if any key was pressed
            String keystr = readkeystr(); //something was pressed so read what key it was

            last_posx=posx;
            if (keystr.equals("a")) {     //perform something if 'a' was pressed
                if (posx>minx+1) posx = posx - 1;
                else {
                    if (! hit_left) {
                        hit_left = true;
                        hit_right = false;
                        sound(700,100);
                    }
                }
            };
            if (keystr.equals("d")) {     //perform something if 'd' was pressed
                if (posx<maxx-1) posx = posx + 1;
                else {
                    if (! hit_right) {
                        hit_left = false;
                        hit_right = true;
                        sound(500, 100);
                    }
                }
            };
            if (keystr.equals("x")) { break; };

            //3. refresh moving objects
            gotoxy(last_posx, posy);     //clear last position of the controlled object
            print(' ');
            gotoxy(posx, posy);          //draw controlled object
            setfgcolor(ball_color);
            print('*');
        }
        //4. perform something even if no key was pressed

        setfgcolor(white);      //print hints
        gotoxy(45,25);
        if (hit_left)  print(">>> Now hit the right wall >>>");
        if (hit_right) print("<<< Now hit the left wall <<< ");

        long time_now = System.nanoTime();   //measure and display passed seconds
        gotoxy(50,4);
        print("Seconds passed: ");
        setfgcolor(time_color);
        print(((time_now - time_start) / 1000000000));  //nanoseconds to seconds

        delay(20);        //additional delay to limit the frequency of the event processing loop
    }
    //5. clear the terminal before exit
    clrscr();
    gotoxy(1,1);
}