import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;

public class PointGeneration
{
    private Random generator = new Random();
    private int pointLimit = 15;

    public PointGeneration()
    {
        Random seedGenerator = new Random();
        this.generator.setSeed(seedGenerator.nextInt(32000));
        this.pointLimit = 15;
    }

    public int[] pointCorrection(int[] tiles)
    {
        // Make sure there is no duplicate tiles
        for (int pointCtr = 0; pointCtr < pointLimit; pointCtr++)
		{
            for (int elemCtr = 0; elemCtr < pointLimit; elemCtr++)
			{
                if (elemCtr == pointCtr)
                    continue;

                if (tiles[pointCtr] == tiles[elemCtr] ||
                    getFirstDigit(tiles[pointCtr]) == getFirstDigit(tiles[elemCtr])) {
                    tiles[pointCtr] = this.generator.nextInt(96) + 3;
                }
            }
        }

        return tiles;
    }

    private int getFirstDigit(int number)
    {
        return Integer.parseInt(Integer.toString(number).substring(0, 1));
    }

    public int[] getTiles()
    {
        int[] tiles = new int[pointLimit];
        int position = 0;

        for (int tileCtr = 0; tileCtr < pointLimit; tileCtr++, position++)
            tiles[tileCtr] = this.generator.nextInt(96) + 3;

        return pointCorrection(tiles);
    }
}
