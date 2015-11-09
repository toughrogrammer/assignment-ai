/**
 * Created by loki on 2015. 11. 9..
 */
public class Node {
    public enum Direction {
        LEFT,
        TOP,
        RIGHT,
        BOTTOM;

        public static Direction determine(char c) {
            switch(c) {
                case 'A':
                    return TOP;
                case 'V':
                    return BOTTOM;
                case '<':
                    return LEFT;
                case '>':
                    return RIGHT;
            }

            return null;
        }
    }

    Node prev;
    int x, y;
    Direction dir;
    int g;

    public Node(int x, int y, Direction dir, int g) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.g = g;
    }

    public boolean isSameLocation(Node node) {
        return this.x == node.x && this.y == node.y;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", x, y, g);
    }
}