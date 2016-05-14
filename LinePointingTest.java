public class LinePointingTest
{
    public static void main(String[] args)
    {
        LinePointing lp = new LinePointing();
        int[] tiles = lp.getTiles();

        System.out.println("Tile Values");
        int ctr = 0;
        for (int i : tiles) {
            System.out.print("Number " + ++ctr + ": ");
            System.out.println(i);
        }
    }
}
