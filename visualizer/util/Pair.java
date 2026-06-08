package visualizer.util;

public class Pair<L, R> {

    private final L left; 
    private final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public static <T extends Comparable<T>> Pair<T, T> ofOrdered(T a, T b) {
        return (a.compareTo(b) <= 0) ? new Pair<>(a, b) : new Pair<>(b, a);
    }

    @Override public boolean equals(Object o) {
        if(this == o)
            return true;
        
        if(!(o instanceof Pair))
            return false;

        Pair<?,?> other = (Pair<?,?>) o;
        return left.equals(other.left) && right.equals(other.right);
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }
}