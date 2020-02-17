package p;
//2020-01-21
import java.awt.*;
import java.util.ArrayList;
 
public class Planet{
   
    private double x,y;
    private double vx,vy;
    private double ax=0,ay=0;
    double mass; int radius; Color color;
   
    public Planet(double x, double y, double vx, double vy,
    		      double mass, int radius, Color color) {
        this.x = x; this.y = y;
        this.vx = vx; this.vy = vy;
        this.mass = mass; this.radius = radius; this.color = color;
    }
 
    // Calculates the new position in the x and y direction
    // given the new velocity and acceleration
    void updatePosition(double dt) {
        this.x += this.vx * dt + (this.ax * Math.pow(dt, 2)/(2));
        this.y += this.vy * dt + (this.ay * Math.pow(dt, 2)/(2));
    }
 
    // Calculates the new velocity in the x and y direction
    // given the new acceleration
    void updateVelocity(double ax, double ay, double dt) {
        this.vx += ax * (dt/2);
        this.vy += ay * (dt/2);
    }
   
    public double getX() {
        return x;
    }
 
    public void setX(double x) {
        this.x = x;
    }
 
    public double getY() {
        return y;
    }
 
    public void setY(double y) {
        this.y = y;
    }
 
    public void setVx(double vx) {
        this.vx = vx;
    }
 
    public void setVy(double vy) {
        this.vy = vy;
    }
 
    public double getMass() {
        return mass;
    }
   
    public double getAx() {
        return ax;
    }
   
    public void addAx(double ax) {
        this.ax += ax;
    }
   
    public double getAy() {
        return ay;
    }
   
    public void setAx(double ax) {
        this.ax = ax;
    }
   
    public void setAy(double ay) {
        this.ay = ay;
    }
   
    public void addAy(double ay) {
        this.ay += ay;
    }
 
    public void setMass(double mass) {
        this.mass = mass;
    }
 
    public int getRadius() {
        return radius;
    }
 
    public void setRadius(int radius) {
        this.radius = radius;
    }
 
    public Color getColor() {
        return color;
    }
 
    public void setColor(Color color) {
        this.color = color;
    }
}