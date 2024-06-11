package utils;

import java.io.Serializable;

public class Vector2 implements Serializable {

    public double x;

    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this(0, 0);
    }

    public Vector2 copy(){
        return new Vector2(x, y);
    }
    
    public void set(double setter) {
        this.x = setter;
        this.y = setter;
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 vec2) {
        this.x = vec2.x;
        this.y = vec2.y;
    }
    
    public void plus(double addend) {
        this.x += addend;
        this.y += addend;
    }

    public void plus(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void plus(Vector2 vec2) {
        this.x += vec2.x;
        this.y += vec2.y;
    }

    public Vector2 plusCopy(double addend) {
        return new Vector2(
            this.x + addend,
            this.y + addend
        );
    }

    public Vector2 plusCopy(double x, double y) {
        return new Vector2(
            this.x + x,
            this.y + y
        );
    }

    public Vector2 plusCopy(Vector2 vec2) {
        return new Vector2(
            this.x + vec2.x,
            this.y + vec2.y
        );
    }

    public void minus(double subtrahend) {
        this.x -= subtrahend;
        this.y -= subtrahend;
    }

    public void minus(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void minus(Vector2 vec2) {
        this.x -= vec2.x;
        this.y -= vec2.y;
    }

    public Vector2 minusCopy(double subtrahend) {
        return new Vector2(
            this.x - subtrahend,
            this.y - subtrahend
        );
    }

    public Vector2 minusCopy(double x, double y) {
        return new Vector2(
            this.x - x,
            this.y - y
        );
    }
    
    public Vector2 minusCopy(Vector2 vec2) {
        return new Vector2(
            this.x - vec2.x,
            this.y - vec2.y
        );
    }
    
    public void multiply(double multiplicator) {
        this.x *= multiplicator;
        this.y *= multiplicator;
    }

    public void multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
    }

    public void multiply(Vector2 vec2) {
        this.x *= vec2.x;
        this.y *= vec2.y;
    }
    
    public Vector2 multiplyCopy(double multiplicator) {
        return new Vector2(
            this.x * multiplicator,
            this.y * multiplicator
        );
    }

    public Vector2 multiplyCopy(double x, double y) {
        return new Vector2(
            this.x * x,
            this.y * y
        );
    }
    
    public Vector2 multiplyCopy(Vector2 vec2) {
        return new Vector2(
            this.x * vec2.x,
            this.y * vec2.y
        );
    }
    
    public void divide(double divider) {
        this.x /= divider;
        this.y /= divider;
    }

    public void divide(double x, double y) {
        this.x /= x;
        this.y /= y;
    }

    public void divide(Vector2 vec2) {
        this.x /= vec2.x;
        this.y /= vec2.y;
    }
    
    public Vector2 divideCopy(double divider) {
        return new Vector2(
            this.x / divider,
            this.y / divider
        );
    }

    public Vector2 divideCopy(double x, double y) {
        return new Vector2(
            this.x / x,
            this.y / y
        );
    }
    
    public Vector2 divideCopy(Vector2 vec2) {
        return new Vector2(
            this.x / vec2.x,
            this.y / vec2.y
        );
    }

    public int[] toInt() {
        return new int[] {
            (int) this.x,
            (int) this.y
        };
    }

    public Vector2 inverse() {
        return new Vector2(
            - this.x,
            - this.y
        );
    }

    public double distance(Vector2 vec2) {
        return Math.sqrt(
            Math.pow(this.x - vec2.x, 2) +
            Math.pow(this.y - vec2.y, 2)
        );
    }

    public double sqrMagnitude() {
        return this.x * this.x + this.y * this.y;
    }
    
    public Vector2 normalize() {
        double magnitude = Math.sqrt(sqrMagnitude());
        if(magnitude == 0){
            return new Vector2();
        }
        return new Vector2(x / magnitude, y / magnitude);
    }

    @Override
    public String toString() {
        return "Vector2 [x=" + x + ", y=" + y + "]";
    }

}
