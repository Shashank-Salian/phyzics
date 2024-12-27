package com.example.phyzics.physics;

import com.example.phyzics.utils.ObjectClickListener;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public abstract class PhysicsObject {
    protected double x, y;
    protected double vx, vy;
    protected boolean gravityEnabled = true;
    protected int lineWidth = 1;
    protected double mass = 1.0d;
    protected float bounce = 0.5f;
    protected Color color = Color.WHITE;
    protected ObjectClickListener onClickListener;
    protected boolean draggable = true;

    public PhysicsObject() {
        x = 100;
        y = 0;
        vx = 0.0d;
        vy = 0.0d;
    }

    public PhysicsObject(double x, double y) {
        this.x = x;
        this.y = y;
        this.vx = 0.0d;
        this.vy = 0.0d;
    }

    public void update(double delta) {

        this.setX(this.getX() + this.getNextPosDx(delta));
        this.setY(this.getY() + this.getNextPosDy(delta));
    }

    /**
     * Calculates and returns the X distance the object will move in the next frame
     * 
     * @param delta
     * @return
     */
    public double getNextPosDx(double delta) {
        return vx * mass * delta;
    }

    /**
     * Calculates and returns the Y distance the object will move in the next frame
     * 
     * @param delta
     * @return
     */
    public double getNextPosDy(double delta) {
        return vy * mass * delta;
    }

    public void setPos(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public abstract void draw(GraphicsContext gc);

    public abstract double getPeakBottom();

    /**
     * @param x X coordinate of the point
     * @param y Y coordinate of the point
     * @return true if the point is in the bounds of the ellipse
     */
    public abstract boolean pointInBounds(double x, double y);
}
