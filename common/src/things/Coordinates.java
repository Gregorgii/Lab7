package things;

import java.io.Serializable;

/**
* Class that work w coordinates
*/
public class Coordinates implements Serializable {

    private Double x; //Значение поля должно быть больше -771, Поле не может быть null

    private Float y; //Поле не может быть null
    public static final int X_MIN = -771;

    public Coordinates(Double x, Float y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return The x piece of coordinate
     */
    public Double getX(){
        return x;
    }

    /**
     * @return The y piece of coordinate
     */
    public Float getY(){
        return y;
    }




    /**
     * @return The coordinates
     */
    @Override
    public String toString(){
        return "X: " + x + " Y: " + y;
    }

 
}