package com.example.phyzics.shapes;

import com.example.phyzics.physics.PhysicsObject;

import javafx.scene.canvas.GraphicsContext;

public class Oval extends PhysicsObject {
    public double radiusX = 10.0d;
    public double radiusY = 10.0d;

    public Oval() {
        super();
    }

    public Oval(double radiusX, double radiusY) {
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(this.color);
        gc.setLineWidth(this.lineWidth);
        gc.beginPath();
        gc.arc(this.x, this.y, this.radiusX, this.radiusY, 0, 360);
        gc.closePath();
        gc.stroke();
    }

    @Override
    public double getPeakBottom() {
        return this.radiusY + this.lineWidth;
    }

    /**
     * Equation of circle:
     * (x - h)^2 + (y - k)^2 = r^2
     * 
     * When it comes to ellipse the equation is:
     * ((x - h)^2 / rx^2) + ((y - k)^2 / ry^2) = 1
     * 
     */
    @Override
    public boolean pointInBounds(double x, double y) {
        double dx = x - this.x;
        double dy = y - this.y;

        return (dx * dx) / (this.radiusX * this.radiusX) + (dy * dy) / (this.radiusY * this.radiusY) <= 1;
    }

}
