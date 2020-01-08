package Pro_Final_POO_Bejeweled_Code;

import java.util.Random;

public class Game {

    static int[][] marray = null;
    static Random generator = new Random();
    private static int pieceNum = 7;
    public int pointNum;
    static int[][] marrayaux = null;
    private boolean repeatFound;

    Game() {
        marray = new int[8][8];
        marrayaux = new int[8][8];

        pointNum = 0;

        Random myRandom = new Random();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int auxVar = myRandom.nextInt(pieceNum) + 1;
                marray[i][j] = auxVar;
                marrayaux[i][j] = auxVar;
            }
        }
        
    }

    public void printing() {
        System.out.println("\n\n** Original Array: **");
        System.out.print("ij ");
        for (int j = 0; j < 8; j++) {
            System.out.print(j + " ");
        }
        for (int i = 0; i < 8; i++) {
            System.out.println("");
            System.out.print("" + i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + marray[i][j]);
            }
        }
        System.out.println("\n\n** Auxiliar Array: **");
        System.out.print("ij ");
        for (int j = 0; j < 8; j++) {
            System.out.print(j + " ");
        }
        for (int i = 0; i < 8; i++) {

            System.out.println("");
            System.out.print("" + i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + marrayaux[i][j]);
            }
        }
        System.out.println("\nSCORE=" + pointNum + "\n\n");
    }

    boolean lineRepeat() {
        boolean rep = false;
        marrayaux = marray.clone();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                int repCount = 0;
                int ii = i + 1;
                while (ii < 8) {
                    if (marray[i][j] == marray[ii][j]) {

                        repCount++;

                    } else {
                        break;
                    }
                    ii++;
                }
                if (repCount >= 2) {
                    for (int eraser = i; eraser < ii; eraser++) {
                        marrayaux[eraser][j] = 0;
                        pointNum += 10;
                        System.out.println(pointNum);

                    }
                    rep = true;
                }

            }

        }
        return rep;
    }

    boolean columnRepeat() {
        boolean rep = false;
        marrayaux = marray.clone();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 6; j++) {
                int repCount = 0;
                int jj = j + 1;
                while (jj < 8) {
                    if (marray[i][j] ==marray[i][jj]) {

                        repCount++;

                    } else {
                        break;
                    }
                    jj++;
                }
                if (repCount >= 2) {
                    for (int eraser = j; eraser < jj; eraser++) {
                        marrayaux[i][eraser] = 0;
                        pointNum += 10;

                    }
                    rep = true;
                }

            }

        }
        return rep;
    }

     void fillTop() {
        Random myRandom = new Random();

        for (int j = 0; j < 8; j++) {
            int i = 0;
            while (i < 8 && marrayaux[i][j] == 0) {
                marrayaux[i][j] = myRandom.nextInt(pieceNum) + 1;
                i++;
            }

        }

    }

    void zeroColumn(int i, int j, int repCount) {
        for (int jj = j; jj < (j + repCount); jj++) {
            marrayaux[i][jj] = 0;
        }
    }

    void zeroLine(int i, int j, int repCount) {
        for (int ii = i; ii < (i + repCount); ii++) {
            marrayaux[ii][j] = 0;
        }
    }

    public void dropSys() {
        for (int j = 0; j < 8; j++) {
            for (int i = 7; i >= 0; i--) {
                if (marrayaux[i][j] == 0) {
                    int ii = i - 1;
                    while (ii >= 0 && marrayaux[ii][j] == 0) {
                        ii--;
                    }
                    if (ii >= 0) {
                        marrayaux[i][j] = marrayaux[ii][j];
                        marrayaux[ii][j] = 0;
                    }
                }

            }
        }
        System.out.println("Before filling the top line");
        this.printing();

        System.out.println("After filling the top line");
        fillTop();
        this.copyToAuxArray();
        this.printing();
    }

    public void copyToAuxArray() {
        for (int i = 0; i < 8; i++) {
            System.arraycopy(marrayaux[i], 0, marray[i], 0, 8);
        }
    }



    public void movePiece(int px1, int py1, int px2, int py2) {
    int aux;
        if ((Math.abs(px1-px2)+Math.abs(py1-py2))!=1) {
            System.out.println("Invalid move!");
            return;
        }

         aux = marray[px1][py1];
        marray[px1][py1] = marray[px2][py2];
        marray[px2][py2] = aux;

        boolean moveFound=(this.lineRepeat() || this.columnRepeat());
       this.checkRepeat();
        if (!moveFound) {
            aux = marray[px1][py1];
            marray[px1][py1] = marray[px2][py2];
            marray[px2][py2] = aux;
            System.out.println("Invalid move s!!");
        }

        this.dropSys();
        this.fillTop();
        while(this.columnRepeat() || this.lineRepeat()){
            this.dropSys();
            this.fillTop();
        }
    }

    public boolean getRepetitionFound() {
        return repeatFound;
    }

    public void checkRepeat() {
        repeatFound = false;
        printing();
        while (this.columnRepeat() || this.lineRepeat()) {
            repeatFound = true;
            this.dropSys();
            this.fillTop();

        }
    }

}
