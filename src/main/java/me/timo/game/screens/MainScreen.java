package me.timo.game.screens;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import me.timo.game.utils.Settings;
import me.timo.game.utils.Sprite;
import me.timo.game.utils.Vector;

import java.util.ArrayList;

public class MainScreen extends Application {

    public static ArrayList<String> inputs = new ArrayList<>();
    public static ArrayList<Sprite> sprites = new ArrayList<>();
    public static Sprite player = new Sprite();
    public static Scene mainScene;
    public static GraphicsContext context;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("2D-Game");

        primaryStage.setResizable(false);
        BorderPane root = new BorderPane();
        mainScene = new Scene(root, Color.LIGHTSKYBLUE);
        primaryStage.setScene(mainScene);

        Canvas canvas = new Canvas(16*64, 12*64);
        context = canvas.getGraphicsContext2D();
        root.setCenter(canvas);

        Sprite wall = new Sprite();
        wall.setImage("wall.png");
        wall.getLocation().set(256, 128);
        sprites.add(wall);

        player.setImage("player.png");
        player.getLocation().set(64, 64);
        sprites.add(player);

        refreshInputs();
        gameLoop();

        primaryStage.show();
    }

    public void gameLoop() {
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                if(inputs.contains("W"))
                    player.velocity.add(0, -Settings.playerSpeed);
                if(inputs.contains("A"))
                    player.velocity.add(-Settings.playerSpeed, 0);
                if(inputs.contains("S"))
                    player.velocity.add(0, Settings.playerSpeed);
                if(inputs.contains("D"))
                    player.velocity.add(Settings.playerSpeed, 0);

                player.velocity.multiply(1/60.0);

                for (Sprite sprite : sprites) {
                    if(sprite != player) {
                        if(player.isTouching(sprite)) {
                            player.setVelocity(new Vector(-player.velocity.x, -player.velocity.y));
                        }
                    }
                };

                player.getLocation().add(player.velocity);

                context.setFill(Color.LIGHTSKYBLUE);
                context.fillRect(0,0, 16*64, 12*64);

                sprites.forEach(sprite -> {
                    sprite.render(context);
                });
            }
        }.start();
    }

    public void refreshInputs() {
        mainScene.setOnKeyPressed((KeyEvent event) -> {
            String key = event.getCode().toString();
            if(!inputs.contains(key))
                inputs.add(key);
        });

        mainScene.setOnKeyReleased((KeyEvent event) -> {
            String key = event.getCode().toString();
            inputs.remove(key);
        });
    }

}
