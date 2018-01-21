package standa.drop;

public class Utils {
    public static double map(double n, double start1, double stop1, double start2, double stop2, boolean withinBounds){
        double newVal = (n - start1) / (stop1 - start1) * (stop2 - start2) + start2;
        if (!withinBounds) {
            return newVal;
        }
        if (start2 < stop2) {
            return constrain(newVal, start2, stop2);
        } else {
            return constrain(newVal, stop2, start2);
        }
    }

    public static double constrain(double n, double low, double high){
        return Math.max(Math.min(n, high), low);
    }
}
