package collection;

/**
 * class for future implementation
 * (literally anything that can have id)
 */
public class IdHolder {
    private Integer id;//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    public IdHolder(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
