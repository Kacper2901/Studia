import static java.lang.IO.print;
import static term.term.*;

public static class TSimulation{
    TBoard board;
    TParameters parameters;
    TPlayer player1;
    TPlayer player2;
    TCircle[] circles;
    int circlesCount;
}


public static class TParameters{
    int realWidth;
    int realHeight;
    double meterToPixelX;
    double meterToPixelY;
    double g;
    double time;
    boolean dissipateEnergy;
    double wallDissipation;
    double circleDissipation;
}


public static class TBoard{
    int x;
    int y;
    int width;
    int height;
}

//TODO: stworz gracza i kolka
public static class TPlayer{

}

public static class TCircle{

}


public static void setSimulation(TSimulation simulation, TBoard board){
    simulation.board = board;
    setParameters(simulation.parameters,board);
}

public static TParameters createParameters(TBoard board){
    TParameters parameters = new TParameters();
    setParameters(parameters, board);
    return parameters;
}

public static void setParameters(TParameters parameters, TBoard board){
    parameters.realHeight = 100;
    parameters.realWidth = 100;
    parameters.meterToPixelX = parameters.realWidth / (board.width);
    parameters.meterToPixelY = parameters.realHeight / (board.height);
    parameters.g = 9.81;
    parameters.dissipateEnergy = false;
    parameters.wallDissipation = 0.95;
    parameters.circleDissipation = 0.99;
}

public static TBoard createBoard(int width, int height, int x, int y){
    TBoard board = new TBoard();
    setBoard(board, width, height,x,y);
    return board;
}

public static void setBoard(TBoard board, int width, int height, int x, int y){
    board.height = height;
    board.width = width;
    board.x = x;
    board.y = y;
}

public static void drawBoard(TBoard board){
    int x1 = board.x;
    int y1 = board.y;
    int x2 = x1 + board.width;
    int y2 = y1 + board.width;
    setfgcolor(white);
    framexyc(x1,y1, x2, y2, '*');
}
//TODO: dokoncz rekordy gracza i kolek
//TODO: zrob poruszenie sie kolko-kwadratu
//TODO: zrob collision detection ze scianami
//TODO: zrob collision detection z podgloga
//TODO: zrob collision detection z sufitem
//TODO: zrob zmiane kierunku w zderzeniu ze sciana (masa i predkosc)
//TODO: zrob zmiane kierunku z podloga (masa i predkosc)
//TODO: zrob zmiane kierunku z sufitem (masa i predkosc)
//TODO: zrob collision detection z pilkami
//TODO: zrob zmiane kierunku z pilkami uwzgledniajac predkosc i mase


void main() {
    clrscr();
    TSimulation simulation = createElements(TSimulation.class);
    setSimulation(simulation,createBoard(200,50,1,1));
    drawBoard(simulation.board);
    gotoxy(20,20);
    readkey();
}