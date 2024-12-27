package com.example.phyzics.physics;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import lombok.Data;

/**
 * World will contain all the objects in it, and will be responsible for
 * rendering and animation of the objects of the world.
 */
@Data
public class World {
    public List<PhysicsObject> objects = new ArrayList<>();
    public static final int WIDTH = 1800, HEIGHT = 1000;
    private PhysicsObject selectedObject = null;

    public double gravity = 9.8d;

    private final Canvas canvas;
    private final GraphicsContext ctx;

    private double mousePrevX = 0;
    private double mousePrevY = 0;

    public World() {
        canvas = new Canvas(World.WIDTH, World.HEIGHT);
        ctx = canvas.getGraphicsContext2D();

        new AnimationTimer() {
            private long lastTime = System.nanoTime();
            private static final double NANO_TO_SECONDS = 1.0 / 1e9;

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) * NANO_TO_SECONDS;
                lastTime = now;
                tick(deltaTime);
            }
        }.start();

        canvas.setOnMouseClicked(e -> {
            var x = e.getX();
            var y = e.getY();

            var obj = getBoundingObject(x, y);
            if (obj != null && obj.onClickListener != null) {
                obj.onClickListener.onClick(obj);
            }
        });

        canvas.setOnMousePressed(e -> {
            var x = e.getX();
            var y = e.getY();

            mousePrevX = x;
            mousePrevY = y;

            selectedObject = getBoundingObject(x, y);
        });

        canvas.setOnMouseReleased(eh -> {
            selectedObject = null;
            mousePrevX = 0;
            mousePrevY = 0;
        });

        canvas.setOnMouseDragged(e -> {
            double x = e.getX();
            double y = e.getY();
            double dx = x - mousePrevX;
            double dy = y - mousePrevY;

            mousePrevX = x;
            mousePrevY = y;

            if (selectedObject != null && selectedObject.draggable) {
                selectedObject.setPos(selectedObject.x + dx, selectedObject.y + dy);
            }
        });
    }

    private PhysicsObject getBoundingObject(double x, double y) {
        for (PhysicsObject obj : objects) {
            if (obj.pointInBounds(x, y)) {
                return obj;
            }
        }
        return null;
    }

    private void tick(double deltaTime) {
        ctx.setFill(Color.BLACK);
        ctx.fillRect(0, 0, World.WIDTH, World.HEIGHT);
        // ctx.clearRect(0, 0, World.WIDTH, World.HEIGHT);
        for (PhysicsObject obj : objects) {

            if (obj.gravityEnabled && this.selectedObject != obj) {
                applyGravity(obj, deltaTime);
            }

            obj.update(deltaTime);
            obj.draw(ctx);
        }
    }

    public void applyGravity(PhysicsObject obj, double deltaTime) {
        if (obj.y + obj.getPeakBottom() + obj.getNextPosDy(deltaTime) >= World.HEIGHT) {
            double prevVy = obj.vy;
            double nextVy = -(obj.vy * obj.bounce);

            if (Math.abs(prevVy - nextVy) < 0.001) {
                obj.vy = 0.0d;
                return;
            }

            obj.vy = nextVy;
            return;
        }

        obj.vy += this.gravity;
    }

    public void add(PhysicsObject obj) {
        objects.add(obj);
    }
}
