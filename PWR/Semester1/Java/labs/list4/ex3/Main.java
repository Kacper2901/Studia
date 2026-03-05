//Kacper Gęśla 290168

import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;     //includes package term (createElements() functions were moved there)

String sixDigit(double n){
    return String.format("%.6f", n);
}

double TankFillTimeSim(double vt, double v0, double x, double tau){
    double timePassed = 0;
    double currFill = v0;
    double tauInflow = tau * x;

    gotoxy(1,1);
    print("Simulation for: \n" +
            "-Capacity: " + vt + "\n" +
            "-Tau: " + tau + "\n" +
            "-Liters/second: " + x);

    gotoxy(1,6);
    print("Time: " + sixDigit(timePassed) +"\n" +
            "Current water level: " + sixDigit(currFill));

    while (currFill < vt){
//        delay(200);
        currFill += tauInflow;
        timePassed += tau;

        gotoxy(1,1);
        print("Simulation for: \n" +
                "-Capacity: " + vt + "\n" +
                "-Tau: " + tau + "\n" +
                "-Liters/second: " + x);

        gotoxy(1,6);
        print("Time: " + sixDigit(timePassed) +"\n" +
                "Current water level: " + sixDigit(currFill));
    }

    return timePassed;
}

double TankFillTimeLin(double vt, double v0, double x){
//    double timePassed = 0;
//    double currFill = v0;
//
//    gotoxy(1,10);
//    print("Simulation for: \n" +
//            "-Capacity: " + vt + "\n" +
//            "-Liters/second: " + x);
//
//    gotoxy(1,14);
//    print("Time: " + sixDigit(timePassed) +"\n" +
//            "Current water level: " + sixDigit(currFill));
//
//    while (currFill < vt){
//        delay(200);
//        currFill += x;
//        timePassed += 1;
//
//        gotoxy(1,10);
//        print("Simulation for: \n" +
//                "-Capacity: " + vt + "\n" +
//                "-Liters/second: " + x);
//
//        gotoxy(1,14);
//        print("Time: " + sixDigit(timePassed) +"\n" +
//                "Current water level: " + sixDigit(currFill));
//    }

    return (vt - v0) / x;
}

void main() {
    clrscr();
    cursor_hide();
    final double vt = 100;
    final double v0 = 5;
    final double x = 0.6;
    final double tau = 0.001;

    double first = TankFillTimeSim(vt, v0, x, tau);

    double second = TankFillTimeLin(vt, v0, x);
    print("\n\n" + "Linear time: " + second);
    print("\n");
    cursor_show();


}