public class LinePointingTest
{
    public static void main(String[] args)
    {
        LinePointing lp = new LinePointing();
        int[] tiles = lp.getTileDelegations();

        System.out.println("Tile Values");
        for (int i : tiles) {
            System.out.println(i);
        }
    }
}
