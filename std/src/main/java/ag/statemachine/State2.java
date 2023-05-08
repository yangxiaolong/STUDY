package ag.statemachine;

public class State2 {

    public static void main(String[] args) {
        boolean b = canMeasureWater(11, 7, 14);
        System.out.println(b);
    }

    public static boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        return z % gcd(x, y) == 0;
    }

    public static int gcd(int x, int y) {
        int remainder = x % y;
        while (remainder != 0) {
            x = y;
            y = remainder;
            remainder = x % y;
        }
        return y;
    }
}