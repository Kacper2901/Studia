import static term.term.*;

final static int MAX_CIRCLES = 200;

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
    double scaleMetersToPixelsX;
    double scaleMetersToPixelsY;
    double g;
    double dt;
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
    double radius;
    int score;
    int x;
    int y;
}

public static class TCircle{
    double  mass;
    double vx;
    double vy;
    double centerX;
    double centerY;
    int color;
    int x1Rounded;
    int x2Rounded;
    int y1Rounded;
    int y2Rounded;
    boolean wasBouncedFromCeil;
    boolean wasBouncedFromFloor;
    boolean wasBouncedFromLeftWall;
    boolean wasBouncedFromRightWall;

}


public static void setSimulation(TSimulation simulation, TBoard board, TPlayer player1, TPlayer player2){
    simulation.board = board;
    simulation.player1 = player1;
    simulation.player2 = player2;
    simulation.parameters = createParameters(board);
    simulation.circlesCount = 0;
    simulation.circles = new TCircle[MAX_CIRCLES];
}

public static TParameters createParameters(TBoard board){
    TParameters parameters = new TParameters();
    setParameters(parameters, board);
    return parameters;
}

TPlayer createPlayer(TSimulation simulation, int radius, int numberOfPlayer){
    TPlayer player = new TPlayer();
    setPlayer(player, simulation, radius, numberOfPlayer);
    return player;
}

public static void setPlayer(TPlayer player,TSimulation simulation, int radius, int numberOfPlayer){
    TBoard board = simulation.board;
    player.radius = radius;
    player.score = 0;
    if(numberOfPlayer == 1){
        player.x = board.x + radius;
        player.y = board.y + board.height - 1 - radius;
    }
    else {
        player.x = board.x + board.width - 1 - radius;
        player.y = board.y + board.height - 1 - radius;
    }
}


public static void setParameters(TParameters parameters, TBoard board){
    parameters.realHeight = 100;
    parameters.realWidth = 100;
    parameters.scaleMetersToPixelsX = (double) (board.width - 2)/parameters.realWidth;
    parameters.scaleMetersToPixelsY = (double)(board.height - 2)/parameters.realHeight;
    parameters.g = 9.81;
    parameters.dissipateEnergy = false;
    parameters.wallDissipation = 0.95;
    parameters.circleDissipation = 0.99;
    parameters.dt = 0.02;
}

public static void addCircle(TSimulation simulation, int numberOfSquares){
    TCircle[] circles = simulation.circles;
    for(int i = 0; i < numberOfSquares; i++){
        int randomMass = (int)(Math.random()*3 + 1);
        circles[simulation.circlesCount] = createCircle(simulation, randomMass);
        simulation.circlesCount += 1;
    }
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

public static TCircle createCircle(TSimulation simulation, int mass){
    TCircle circle = new TCircle();
    setCircle(circle,simulation, mass);
    return circle;
}

public static void setCircle(TCircle circle,TSimulation simulation, int mass){
    int[] speedX = {-20, -10, - 5, 5, 10, 20};
    int[] speedY = {10, 15, 8, 12, 20, 6};
    TBoard board = simulation.board;
    circle.mass = mass;
    circle.centerX = (Math.random()*(simulation.parameters.realWidth - 2*mass - 1) + mass + 1);
    circle.centerY = (Math.random()*(simulation.parameters.realHeight - 2*mass - 1) + mass + 1);
    circle.vx = speedX[(int)(Math.random()*6)];
    circle.vy = speedX[(int)(Math.random()*6)];
    circle.color = (int)(Math.random()*15 + 1);
    circle.x1Rounded = (int)((circle.centerX - mass)*simulation.parameters.scaleMetersToPixelsX) + 2;
    circle.y1Rounded = (int)((circle.centerY - mass)*simulation.parameters.scaleMetersToPixelsY) + 2;
    circle.x2Rounded = (int)((circle.centerX + mass)*simulation.parameters.scaleMetersToPixelsX) + 2;
    circle.y2Rounded = (int)((circle.centerY + mass)*simulation.parameters.scaleMetersToPixelsY) + 2;
    circle.wasBouncedFromCeil = false;
    circle.wasBouncedFromFloor = false;
    circle.wasBouncedFromLeftWall = false;
    circle.wasBouncedFromRightWall = false;
}

public static void drawBoard(TBoard board){
    int x1 = board.x;
    int y1 = board.y;
    int x2 = x1 + board.width;
    int y2 = y1 + board.height;
    setfgcolor(white);
    framexyc(x1,y1, x2, y2, '*');
}



public static void drawCircle(TSimulation simulation,TCircle circle, char c){
    double scaleX = simulation.parameters.scaleMetersToPixelsX;
    double scaleY = simulation.parameters.scaleMetersToPixelsY;
    double radius = circle.mass;

    int scaledX1 = (int)((circle.centerX - radius) * scaleX) +2;
    int scaledY1 = (int)((circle.centerY - radius) * scaleY) +2; //+1 for frame and + 1 to pass frame
    int scaledX2 = (int)((circle.centerX + radius) * scaleX) +2;
    int scaledY2 = (int)((circle.centerY + radius) * scaleY) + 2;

    if(scaledX1 != circle.x1Rounded || scaledY1 != circle.y1Rounded){
        framexyc(circle.x1Rounded, circle.y1Rounded, circle.x2Rounded, circle.y2Rounded, ' ');
        setfgcolor(circle.color);
        circle.x1Rounded = scaledX1;
        circle.x2Rounded = scaledX2;
        circle.y1Rounded = scaledY1;
        circle.y2Rounded = scaledY2;
        framexyc(circle.x1Rounded, circle.y1Rounded, circle.x2Rounded, circle.y2Rounded, c);

    }
}

public static void drawAllCircles(TSimulation simulation, char c){
    TCircle[] circles = simulation.circles;
    for(int i = 0; i < simulation.circlesCount; i++){
        drawCircle(simulation, circles[i], c);
    }
}




public static void applyGravityToCircle(TSimulation simulation, TCircle circle){
    TParameters parameters = simulation.parameters;
    circle.vy += parameters.g * parameters.dt;
    circle.centerY += circle.vy * parameters.dt;
}

public static void applyVxSpeedToCircle(TSimulation simulation, TCircle circle){
    TParameters parameters = simulation.parameters;
    circle.centerX += circle.vx*parameters.dt;
}

public static void applySpeedToAllCircles(TSimulation simulation){
    TCircle[] circles = simulation.circles;
    for(int i = 0; i < simulation.circlesCount; i++) {
        applyGravityToCircle(simulation, circles[i]);
        applyVxSpeedToCircle(simulation, circles[i]);
    }
}

public static boolean isFloorCeilCollision(TSimulation simulation, TCircle circle){
    double radius = circle.mass;
    double tempSpeed = circle.vy + simulation.parameters.g*simulation.parameters.dt;
    double yAfterApplyingGravity = circle.centerY + tempSpeed*simulation.parameters.dt;
    if((yAfterApplyingGravity + radius > simulation.parameters.realHeight && tempSpeed > 0) || (yAfterApplyingGravity - radius < 0 && tempSpeed <0)) return true;
    else return false;
}

public static void floorCeilBounce(TSimulation simulation, TCircle circle){
    double minY = circle.mass;
    double maxY = simulation.parameters.realHeight  - circle.mass;
    if(circle.centerY < 50){
        circle.centerY = minY;
        circle.wasBouncedFromCeil = true;
    }
    else {
        circle.centerY = maxY;
        circle.wasBouncedFromFloor = true;
    }
    circle.vy += simulation.parameters.dt * simulation.parameters.g;
    circle.vy *= -1;
    drawCircle(simulation,circle, 'o');

}

public static void bounceCirclesOnTheFloor(TSimulation simulation){
    TCircle[] circles = simulation.circles;
    for(int i = 0; i < simulation.circlesCount; i++){
        if(isFloorCeilCollision(simulation,circles[i])) floorCeilBounce(simulation, circles[i]);
        else{
            circles[i].wasBouncedFromCeil = false;
            circles[i].wasBouncedFromFloor = false;
        }
    }
}

public static boolean isBounceFromWall(TSimulation simulation, TCircle circle){
    double radius = circle.mass;
    double xAfterStep = circle.centerX + simulation.parameters.dt * circle.vx;
    double leftSide = 0;
    double rightSide = simulation.parameters.realWidth;
    if(xAfterStep - radius <= leftSide || xAfterStep + radius >= rightSide) return true;
    return false;
}

public static void bounceFromWall(TSimulation simulation, TCircle circle){
    double minX = circle.mass;
    double maxX = simulation.parameters.realWidth - circle.mass;
    if(circle.centerX > 50){
        circle.centerX = maxX;
        circle.wasBouncedFromRightWall = true;
    }
    else{
        circle.centerX = minX;
        circle.wasBouncedFromLeftWall = true;
    }
    circle.vx *= -1;
    drawCircle(simulation,circle, 'o');
}

public static void bounceCirclesNearWalls(TSimulation simulation){
    TCircle[] circles = simulation.circles;
    for(int i = 0; i < simulation.circlesCount; i++){
        if(isBounceFromWall(simulation,circles[i])) bounceFromWall(simulation,circles[i]);
        else{
            circles[i].wasBouncedFromLeftWall = false;
            circles[i].wasBouncedFromRightWall = false;
        }
    }
}

public static void controller(TSimulation simulation){
    String keystr = "";
    if(keypressed()) keystr = readkeystr();
    model(simulation, keystr);
}



public static void model(TSimulation simulation, String keystr){
//    interpreteKey(keystr);
    bounceCirclesOnTheFloor(simulation);
    bounceCirclesNearWalls(simulation);
    applySpeedToAllCircles(simulation);

    view(simulation);
}


public static void view(TSimulation simulation){
//    delay(200);
    drawAllCircles(simulation, 'o');
}



public static void startSimulation(TSimulation simulation){
    while(true){
        controller(simulation);
    }
}

//TODO: dokoncz rekordy gracza i kolek :done
//TODO: zrob poruszenie sie kolko-kwadratu (poruszanie sie jednego kwadratu + poruszanie sie wielu kwadratow): done
//TODO: zrob collision detection ze scianami: done?
//TODO: zrob collision detection z podgloga: done?
//TODO: zrob collision detection z sufitem:
//TODO: zrob zmiane kierunku w zderzeniu ze sciana (masa i predkosc)
//TODO: zrob zmiane kierunku z podloga (masa i predkosc)
//TODO: zrob zmiane kierunku z sufitem (masa i predkosc)
//TODO: zrob collision detection z pilkami
//TODO: zrob zmiane kierunku z pilkami uwzgledniajac predkosc i mase


void main() {
    clrscr();
    TSimulation simulation = createElements(TSimulation.class);
    setSimulation(simulation,createBoard(200,50,1,1), createPlayer(simulation, 2, 1), createPlayer(simulation,2,1));
    drawBoard(simulation.board);
    addCircle(simulation, 20);
    startSimulation(simulation);
    ;
    readkey();
}