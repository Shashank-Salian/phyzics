package com.example.phyzics.shapes;

import com.example.phyzics.physics.PhysicsObject;

import javafx.scene.canvas.GraphicsContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
// @Gravity
public class Rectangle extends PhysicsObject {
    public double width = 200, height = 200;

    public Rectangle() {
        super();
        this.width = 200;
        this.height = 200;
    }

    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(this.color);
        gc.setLineWidth(this.lineWidth);
        gc.strokeRect(this.x, this.y, this.width, this.height);
    }

    public double getGrossHeight() {
        return this.height + this.lineWidth;
    }

    public double getGrossWidth() {
        return this.width + this.lineWidth;
    }

    @Override
    public double getPeakBottom() {
        return this.height + this.lineWidth;
    }

    @Override
    public boolean pointInBounds(double x, double y) {
        return x >= this.x && x <= this.x + this.getGrossWidth() && y >= this.y && y <= this.y + this.getGrossHeight();
    }

}
