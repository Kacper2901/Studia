Kacper Gęśla 290168

import static java.lang.IO.println;
import static java.lang.IO.print;
import static term.term.*;    //including package to be able to use simple print()

final int MAX_INT = Integer.MAX_VALUE;
final int MAX_POINTS = 2000;


int scaleColor(int hist_min, int hist_max, int val){
    return (int)(255.0 * ((val - hist_min) / (double) (hist_max - hist_min)));
}


int[][] randMTX(int sizeX, int sizeY, int loops){
    int[][] matrix = new int[sizeY][sizeX];
    int x, y;
    int seed = 5000;

    SecureRandom secRand = new SecureRandom();
    Random rand = new Random(seed);
    for(int i = 0; i < sizeY; i++){
        for(int j = 0; j < sizeX; j++){
            matrix[i][j] = 0;
        }
    }

    for(int i = 0; i < loops; i++){
//        rand.setSeed(seed++);
//        x = rand.nextInt(sizeX);
//        y = rand.nextInt(sizeY);
        x = secRand.nextInt(sizeX);
        y = secRand.nextInt(sizeY);
        matrix[y][x]++;
    }

    return matrix;
}

void printMatrix(int[][] matrix, int sizeX, int sizeY){
    int hist_max = 0;
    int hist_min = MAX_INT;
    int green;

    for(int i = 0; i < sizeY; i++){
        for(int j = 0; j < sizeX; j++){
            if(matrix[i][j] < hist_min) hist_min = matrix[i][j];
            if(matrix[i][j] > hist_max) hist_max = matrix[i][j];
        }
    }

    for(int i = 0; i < sizeY; i++){
        for(int j = 0; j < sizeX; j++){
            green = scaleColor(hist_min,hist_max, matrix[i][j]);
            setfgcolor_rgb(10, green, 10);
            gotoxy(j + 1, i + 1);
            write('█');
        }
    }
}


void main() {
    int[][] randomMatrix = randMTX(200,50,5000000);
    printMatrix(randomMatrix,200,50);

}

