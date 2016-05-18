import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;
import static java.lang.Math.abs;

public class PointGeneration
{
    private Random generator = new Random();
    private int pointLimit = 12;

    public PointGeneration()
    {
        Random seedGenerator = new Random();
        this.generator.setSeed(seedGenerator.nextInt(32000));
        this.pointLimit = 12;
    }

    public int[] pointCorrection(int[] tiles)
    {
        // Make sure there is no duplicate tiles
        for (int pointCtr = 0; pointCtr < pointLimit; pointCtr++) {
            for (int elemCtr = 0; elemCtr < pointLimit; elemCtr++) {
                System.out.println("PVal: " + tiles[pointCtr] + "| SVal: " + tiles[elemCtr]);
                if (elemCtr == pointCtr) {
                    continue;
                }

                if (tiles[pointCtr] == tiles[elemCtr]) {
                    System.out.println("Something equal");
                    if (tiles[pointCtr] % 2 >= 0 &&
                        tiles[pointCtr] % 2 < 9) {
                        tiles[pointCtr]++;
                    } else if (tiles[pointCtr] % 2 == 9) {
                        tiles[pointCtr]--;
                    }
                }
            }
        }

        return tiles;
    }

    public int[] getTiles()
    {
        int[] tiles = new int[pointLimit];
        int position = 0;
        for (int tileCtr = 0; tileCtr < pointLimit; tileCtr++, position++) {
            position = (position % 3) + 1;
            tiles[tileCtr] = this.generator.nextInt(30) + (10 * position);
        }

        return pointCorrection(tiles);
    }
}
