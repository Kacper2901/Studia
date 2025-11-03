//Kacper Gęśla 290168

import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;     //includes package term (createElements() functions were moved there)
double AccDistance(double v, double a, double tau){
    return v * tau + a * tau * tau / 2;
}

String round_to_6frac(double n){
    return String.format("%.2f", n);
}

double AccSpeed(double a, double tau){
    return a * tau;
}

double constSpeedDistance(double v, double tau){
    return v * tau;
}

double inconAccSpeed(double t, double t2, double t_osc, double dev_v, double v2){
    return v2 + dev_v * Math.cos(2 * Math.PI * t / t_osc);
}

double inconAcc(double t, double t2, double a2){
    return a2 * Math.sin(Math.PI / 2 * t / t2);
}
void printSituation(double time, double v1, double v2, double a1, double a2, double d1, double d2){
    gotoxy(1,1);
    println("time: " + round_to_6frac(time/60));
    print("P1:  ");
    print("v1 = " + round_to_6frac(v1/3.6) + "m/s (" + round_to_6frac(v1) + "km/h), ");
    print("a1 = " + round_to_6frac(a1) + "m/s², ");
    print("d1 = " +round_to_6frac(d1/1000) + "km");
    println();
    print("P2:  ");
    print("v2 = " + round_to_6frac(v2/3.6) + "m/s (" + round_to_6frac(v2) + "km/h), ");
    print("a2 = " + round_to_6frac(a2) + "m/s², ");
    print("d2 = " +round_to_6frac(d2/1000) + "km");
    println("");
    println("Total distance = " +round_to_6frac ((d1+d2)/1000) + "km");
}

void collisionCalc(double X, double t1, double t2, double a1, double a2, double dev_v, double t_osc, double tau){
    clrscr();
    cursor_hide();
    double d1 = 0;
    double d2 = 0;
    double v1 = 0;
    double v2 = 0;
    double totalTime = 0;
    double acc2 = 0;
    double totalDistance = 0;
    double vt2 = v2;
     t1 *= 60;
     t2 *= 60;
     X *= 1000;

    while (d1 + d2 < X){

        printSituation(totalTime,v1,v2,a1,acc2,d1,d2);

        if (keypressed()){
            String keystr = readkeystr();
            if (keystr.equals("q")){break;}
        }

        totalTime += tau;

        if (totalTime <= t1){
            d1 += AccDistance(v1, a1, tau);
            v1 += AccSpeed(a1, tau);

        }
        else{
            a1 = 0;
            d1 += constSpeedDistance(v1, tau);
        }

        if (totalTime <= t2){
            acc2 = inconAcc(totalTime, t2, a2);
            d2 += AccDistance(v2, acc2, tau);
            v2 += AccSpeed(acc2, tau);
            vt2 = v2;
        }
        else{

            v2 = inconAccSpeed(totalTime, t2, t_osc,dev_v,vt2);
            d2 += constSpeedDistance(v2,tau);
            a2 = 0;
            acc2 = 0;
        }

        totalDistance = d1 + d2;
    }
    printSituation(totalTime,v1,v2,a1,acc2,d1,d2);
    cursor_show();

}

void main() {
    collisionCalc(50, 1, 2, 0.5, 0.7, 4, 180, 10);
}