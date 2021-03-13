package collection;

public class Coordinates {
    public Coordinates(float x,float y) {
        this.x = x;
        this.y = y;
    }
    private Float x; //Значение поля должно быть больше -958, Поле не может быть null
    private Float y; //Поле не может быть null

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }
}
