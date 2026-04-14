package dsaa.lab01;

public class Drawer {
    private static void drawPiramidWithExtraDots(int height, int extraDots){
        int lastLineLength = 2*(height - 1) + 1;
        for(int i = 0; i < height; i++){

            int starsAmmount = 2 * i + 1;
            int dotAmmount = (lastLineLength - starsAmmount) / 2;

            drawLine(extraDots + dotAmmount, '.');
            drawLine(starsAmmount, 'X');
            drawLine(extraDots + dotAmmount, '.');

            System.out.println();

        }
    }

	private static void drawLine(int n, char ch) {
		for(int i = 0; i < n; i ++){
            System.out.print(ch);
        }
	}

	public static void drawPyramid(int n) {
        drawPiramidWithExtraDots(n, 0);
	}

	public static void drawChristmassTree(int n) {
        int currHeight = 1;
        int lastRowLength = 2*n - 1;
        int currRowLength;
        int extraDots;

        while(currHeight <= n){
            currRowLength = 2*currHeight - 1;
            extraDots = (lastRowLength - currRowLength)/2;
            drawPiramidWithExtraDots(currHeight, extraDots);
            currHeight++;
        }
    }

    public static void drawLetterU(int n){
        int dotAmmount = n -2;

        for (int i = 0; i < n-1; i ++){
            System.out.print("X");
            drawLine(dotAmmount, '.');
            System.out.println("X");
        }

        drawLine(n, 'X');
        System.out.println();
    }
}

