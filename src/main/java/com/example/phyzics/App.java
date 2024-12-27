package com.example.phyzics;

import com.example.phyzics.physics.World;
import com.example.phyzics.shapes.Oval;
import com.example.phyzics.shapes.Rectangle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    World world;

    @Override
    public void start(Stage stage) {
        world = new World();

        drawShapes();

        StackPane root = new StackPane(world.getCanvas());
        stage.setScene(new Scene(root));

        stage.setWidth(World.WIDTH + 50);
        stage.setHeight(World.HEIGHT + 50);
        stage.setTitle("Phyzics");

        stage.show();
    }

    private void drawShapes() {
        Rectangle rec = new Rectangle();
        world.add(rec);

        Rectangle rec2 = new Rectangle(500, 0, 300, 300);
        rec2.setMass(5);
        rec2.setOnClickListener(obj -> {
            System.out.println("Clicked Rectangle 2");
        });
        world.add(rec2);

        Oval oval = new Oval(100, 100);
        oval.setPos(1000, 0);
        oval.setOnClickListener(obj -> System.out.println("Clicked Oval"));
        oval.setMass(2);
        oval.setBounce(0.8f);
        world.add(oval);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
