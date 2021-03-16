package collection;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Vehicle extends IdHolder implements Comparable<Vehicle> {

    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private String creationDate;//Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float enginePower; //Значение поля должно быть больше 0
    private VehicleType type; //Поле может быть null
    private FuelType fuelType; //Поле не может быть null

    public Vehicle(Integer id, String name, float xCoordinate, float yCoordinate, float enginePower, VehicleType type, FuelType fuelType) {
        super(id);
        this.name = name;
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;
        this.setCreationDate(ZonedDateTime.now());
        coordinates = new Coordinates(xCoordinate, yCoordinate);


    }

    public ZonedDateTime getCreationDate() {
        return ZonedDateTime.parse(creationDate);
    }

    public void setCreationDate(ZonedDateTime creationDate) {
       this.creationDate = DateTimeFormatter.ISO_DATE_TIME.format(creationDate);
    }

    public int compareTo(Vehicle o) {
        return this.getId() - o.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}



