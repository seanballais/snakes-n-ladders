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
                if (elemCtr == pointCtr) {
                    continue;
                }

                if (tiles[pointCtr] == tiles[elemCtr]) {
                    tiles[pointCtr] = this.generator.nextInt(100);
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
            tiles[tileCtr] = this.generator.nextInt(97) + 1;
        }

        return pointCorrection(tiles);
    }
}
