package dsaa.lab01;

public class Drawer {
	private static void drawLine(int n, char ch) {
		for(int i = 0; i < n; i ++){
            System.out.print(ch);
        }
	}


	public static void drawPyramid(int n) {
        int dotAmmount;
        int starsAmmount;
        int lastLineLength = 2*(n - 1) + 1;

		for(int i = 0; i < n; i++){

            starsAmmount = 2 * i + 1;
            dotAmmount = (lastLineLength - starsAmmount) / 2;

            drawLine(dotAmmount, '.');
            drawLine(starsAmmount, 'X');
            drawLine(dotAmmount, '.');

            System.out.println();

        }

	}

    public static void drawPyramid2(int n) {
        int dotAmmount;
        int starsAmmount;
        int lastLineLength = 2*(n - 1) + 1;

        for(int i = 0; i < n; i++){

            starsAmmount = 2 * i + 1;
            dotAmmount = (lastLineLength - starsAmmount) / 2;

            drawLine(dotAmmount, '.');
            drawLine(starsAmmount, 'X');
            drawLine(dotAmmount, '.');

        }

    }

	
	public static void drawChristmassTree(int n) {
        int lastLineLength = 2 * (n - 1) + 1;
        int currLineLength;
        int additionalDotsAmmount;
        for (int i = 1; i <= n ; i++) {
            currLineLength = 2 * i + 1;
            additionalDotsAmmount = (lastLineLength - currLineLength) / 2;
            drawLine(additionalDotsAmmount, '.');
            drawPyramid2(i);
            drawLine(additionalDotsAmmount, '.');
            System.out.println();
        }
    }
}

