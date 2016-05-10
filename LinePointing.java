import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;
import static java.lang.Math.abs;

public class LinePointing
{
    private Random generator = new Random();
    private int pointLimit;
    private Point limit;
    private ArrayList<Point> points;

    public LinePointing()
    {
        Random seedGenerator = new Random();
        this.generator.setSeed(seedGenerator.nextInt(32000));
        this.pointLimit = 20;
        this.points = new ArrayList<Point>();
        this.limit = new Point(100, 100);
    }

    public LinePointing(int seed)
    {
        this.generator.setSeed(seed);
        this.points = new ArrayList<Point>();
        this.pointLimit = 20;
        this.limit = new Point(100, 100);
    }

    public LinePointing(int seed, int limit)
    {
        this.generator.setSeed(seed);
        this.pointLimit = limit;
        this.points = new ArrayList<Point>();
        this.limit = new Point(100, 100);
    }

    public LinePointing(int seed, int limit, int x, int y)
    {
        this.generator.setSeed(seed);
        this.pointLimit = limit;
        this.points = new ArrayList<Point>();
        this.limit = new Point(x, y);
    }

    public LinePointing(int seed, int limit, Point graphLimit)
    {
        this.generator.setSeed(seed);
        this.pointLimit = limit;
        this.points = new ArrayList<Point>();
        this.limit = graphLimit;
    }

    public void setSeed(int seed)
    {
        this.generator.setSeed(seed);
    }

    private void generatePoints()
    {
        // Generate the points of the interesecting lines
        // to get a procedurally generated board for
        // Snakes and Ladders
        int[] baseLine = new int[3];
        for (int val : baseLine) {
            // [0] refers to m
            // [1] refers to x
            // [2] refers to b
            // y = mx + b
            val = generator.nextInt(100);
        }

        for (int i = 0; i < limit.getX(); i++) {
            int[] secondLine = new int[3];
            for (int val : baseLine) {
                val = generator.nextInt(100);
            }

            // Find the intersection of the two lines
            double x = abs(secondLine[2] - baseLine[2]) / abs(baseLine[0] - secondLine[0]);
            double y = abs(baseLine[0] * x + baseLine[2]);
            points.add(new Point((int) x, (int) y));

            for (int ctr = 0; ctr < 3; ctr++) {
                // Set the base line to the second line
                baseLine[ctr] = secondLine[ctr];
            }
        }
    }

    private int[] getPoints()
    {
        // Convert the XY coordinates to board tile indexes
        this.generatePoints();
        int[] tilePoints = new int[limit];
        for (Point p : points) {
            // Get the tiles with either a ladder or snake
            int tileLoc = (int) p.getX() + (int) p.getY();
            tileLoc = (tileLoc > 99) ? 99 : tileLoc;
            tilePoints[tileLoc] = 1;
        }

        return tilePoints;
    }

    private int[] pointCorrection(int[] tiles)
    {
        // Redesignate tiles to make sure no two tiles are the same
        // in the list.
        int correctionMode = 10;
        for (int tileIndex = 0; tileIndex < limit - 1; tileIndex++) {
            for (int currTile = tileIndex + 1; currTile < limit; currTile++) {
                if (tiles[tileIndex] == tiles[currIndex]) {
                    if (tiles[currIndex] >= 0 && tiles[currIndex] < 10) {
                        tiles[currIndex] += 10;
                    } else if (tiles[currIndex] >= 90 && tiles[currIndex] < 100) {
                        tiles[currIndex] -= 10;
                    } else {
                        tiles[currIndex] += correctionMode;
                        correctionMode *= -1;
                    }
                }
            }
        }

        return tiles;
    }

    public int[] getTileDelegations()
    {
        // Delegate the tiles to either a snake point or ladder point
        int snakePoints = (int) (limit / 2);
        int ladderPoints = (int) (limit / 2);

        int[] tiles = this.pointCorrection(this.getPoints());
        for (int tile : tiles) {
            if (tile == 1) {
                boolean tileDelegated = false;
                int tileValue = 0;
                while (!tileDelegated) {
                    tileValue = generator.nextInt(1) + 2;
                    if (tileValue == 2 && ladderPoints > 0) {
                        tile = 2;
                        ladderPoints--;
                    } else if (tileValue == 3 && snakePoints > 0) {
                        tile = 3;
                        snakePoints--;
                    }

                    if (tile == 2 || tile == 3) {
                        tileDelegated = true;
                    }
                }
            }
        }

        return tiles;
    }
}
