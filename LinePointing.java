import java.awt.Point;
import java.util.Random;
import java.util.ArrayList;
import static java.lang.Math.abs;

public class LinePointing
{
    private Random generator = new Random();
    private int pointLimit = 20;
    private Point limit;
    private ArrayList<Point> points;
    private int limitVal;

    public LinePointing()
    {
        Random seedGenerator = new Random();
        this.generator.setSeed(seedGenerator.nextInt(32000));
        this.pointLimit = 20;
        this.points = new ArrayList<Point>();
        this.limit = new Point(100, 100);
        this.limitVal = (int) ((limit.x + limit.y) / 2);
    }

    public LinePointing(int seed)
    {
        this.generator.setSeed(seed);
        this.points = new ArrayList<Point>();
        this.pointLimit = 20;
        this.limit = new Point(100, 100);
        this.limitVal = (int) ((limit.x + limit.y) / 2);
    }

    public LinePointing(int seed, int limit)
    {
        this.generator.setSeed(seed);
        this.pointLimit = limit;
        this.points = new ArrayList<Point>();
        this.limit = new Point(100, 100);
        this.limitVal = 100;
    }

    public LinePointing(int seed, int limit, int x, int y)
    {
        this.generator.setSeed(seed);
        this.pointLimit = limit;
        this.points = new ArrayList<Point>();
        this.limit = new Point(x, y);
        this.limitVal = (int) ((x + y) / 2);
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
        // [0] refers to m
        // [1] refers to b
        // y = mx + b
        int[] baseLine = new int[2];
        baseLine[0] = generator.nextInt(88) + 10;
        baseLine[1] = generator.nextInt(88) + 10;

        for (int i = 0; i < pointLimit; i++) {
            int[] secondLine = new int[2];
            secondLine[0] = generator.nextInt(88) + 10;
            secondLine[1] = generator.nextInt(88) + 10;

            // Find the intersection of the two lines
            int dividend = 0;
            if ((baseLine[0] - secondLine[0]) == 0) {
                dividend = 1;
            } else {
                dividend = baseLine[0] - secondLine[0];
            }

            int x = abs(secondLine[1] - baseLine[1]) / dividend;
            int y = abs(baseLine[0] * x + baseLine[1]);

            if (pointInList(x, y)) {
                i--;
                continue;
            } else {
                points.add(new Point(x, y));
            }

            for (int ctr = 0; ctr < 2; ctr++) {
                // Set the base line to the second line
                baseLine[ctr] = secondLine[ctr];
            }
        }
    }

    private boolean pointInList(int x, int y)
    {
        // Checks if a point is already in the list
        for (Point p : points) {
            if ((int) p.x == x && (int) p.y == y) {
                return true;
            }
        }

        return false;
    }

    private boolean tileInList(int[] tilePoints, int tileValue)
    {
        // Check if a tile is already in a list
        for (int tile : tilePoints) {
            if (tile == tileValue) {
                return true;
            }
        }

        return false;
    }

    private boolean sameRow(int a, int b)
    {
        // Check if a tile is on the same row
        int aFirstDigit = getIntFirstDigit(a);
        int bFirstDigit = getIntFirstDigit(b);
        if (aFirstDigit == bFirstDigit) {
            return true;
        }

        return false;
    }

    private int getIntFirstDigit(int i)
    {
        // Limited to two digit numbers
        if (i < 10) {
            return i % 10;
        }

        return i / 10;
    }

    private int[] pointCorrection(int[] tiles, int indexLimit)
    {
        // Redesignate tiles to decrease the chances of
        // two tiles being both a snake and a ladder point, and
        // decrease the chances of pair tiles being on the same
        // row.
        int correctionMode = 10;
        for (int tileIndex = 0; tileIndex < indexLimit - 1; tileIndex++) {
            for (int currTile = tileIndex + 1; currTile < indexLimit; currTile++) {
                if (tiles[tileIndex] == tiles[currTile] ||
                    sameRow(tiles[tileIndex], tiles[currTile])) {
                    if (tiles[currTile] >= 0 && tiles[currTile] < 10) {
                        tiles[currTile] += 10;
                    } else if (tiles[currTile] >= 90 && tiles[currTile] < 100) {
                        tiles[currTile] -= 10;
                    } else {
                        tiles[currTile] += correctionMode;
                        correctionMode *= -1;
                    }
                }

                if (tiles[currTile] == 0 || tiles[currTile] == 1) {
                    tiles[currTile] = 2;
                }
            }
        }

        return tiles;
    }

    public int[] getTiles()
    {
        // Convert the XY coordinates to board tile indexes
        this.generatePoints();
        int[] tiles = new int[pointLimit];

        int tileIndex = 0;
        for (Point p : points) {
            // Get the tiles with either a ladder or snake
            int tileLoc = (int) p.x + (int) p.y;
            if (tileLoc > 99) {
                tileLoc = 99;
            } else if (tileLoc == 1) {
                tileLoc = 2;
            }

            tiles[tileIndex] = tileLoc;
            tileIndex++;
        }

        return pointCorrection(tiles, pointLimit);
    }
}
