public class Piece implements Cloneable {
    public Boolean eaten;
    public int positionX;
    public int positionY;
    public String label;
    public Boolean moved;
    public int power;
    public Boolean enemy;
    public int number;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    Piece(Boolean eaten, int positionX, int positionY, String label, Boolean moved, int power, Boolean enemy,
            int number) {
        this.eaten = eaten;
        this.positionX = positionX;
        this.positionY = positionY;
        this.label = label;
        this.moved = moved;
        this.power = power;
        this.enemy = enemy;
        this.number = number;
    }

    public void display() {
        System.out.println();
    }
}
