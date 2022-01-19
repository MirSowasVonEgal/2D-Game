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
import me.timo.game.entitys.World;
import me.timo.game.utils.Settings;
import me.timo.game.utils.Sprite;
import me.timo.game.utils.Vector;

import java.util.ArrayList;

public class MainScreen extends Application {

    public static ArrayList<String> inputs = new ArrayList<>();
    public static ArrayList<Sprite> sprites = new ArrayList<>();
    public static Sprite player = new Sprite();
    public static Sprite colliderX = new Sprite();
    public static Sprite colliderY = new Sprite();
    public static Scene mainScene;
    public static GraphicsContext context;
    public static World defaultWorld;

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

        player.setImage("player.png");
        player.getLocation().set(64, 64);
        sprites.add(player);

        colliderX.setColor(Color.RED);
        colliderX.setSize(2, 65);
        colliderX.setType("COLLIDER");

        colliderY.setColor(Color.RED);
        colliderY.setSize(65, 2);
        colliderY.setType("COLLIDER");

        refreshInputs();
        gameLoop();

        createWorld();
        loadWorld();

        primaryStage.show();
    }

    public void createWorld() {
        defaultWorld = new World("Default");
        Sprite wall = new Sprite();
        wall.setImage("wall.png");
        wall.setType("WALL");
        defaultWorld.setBlock(new Vector(0, 0), wall);
        defaultWorld.setBlock(new Vector(1, 0), wall);
        defaultWorld.setBlock(new Vector(2, 0), wall);
        defaultWorld.setBlock(new Vector(3, 0), wall);
    }

    public void loadWorld() {
        defaultWorld.blocks.forEach(block -> {
            sprites.add(block.getSprite());
        });
    }

    public void gameLoop() {
        new AnimationTimer(){
            @Override
            public void handle(long now) {
                if(inputs.contains("W")) {
                    player.velocity.add(0, -Settings.playerSpeed);
                    colliderY.setLocation(new Vector(player.getLocation().x, player.getLocation().y-1));
                    colliderY.setName("Top");
                    sprites.add(colliderY);
                }
                if(inputs.contains("A")) {
                    player.velocity.add(-Settings.playerSpeed, 0);
                    colliderX.setLocation(new Vector(player.getLocation().x - 1, player.getLocation().y));
                    colliderX.setName("Left");
                    sprites.add(colliderX);
                }
                if(inputs.contains("S")) {
                    player.velocity.add(0, Settings.playerSpeed);
                    colliderY.setLocation(new Vector(player.getLocation().x, player.getLocation().y + player.getBoundary().height+1));
                    colliderY.setName("Bottom");
                    sprites.remove(colliderY);
                    sprites.add(colliderY);
                }
                if(inputs.contains("D")) {
                    player.velocity.add(Settings.playerSpeed, 0);
                    colliderX.setLocation(new Vector(player.getLocation().x + player.getBoundary().width+ 1, player.getLocation().y));
                    colliderX.setName("Right");
                    sprites.remove(colliderX);
                    sprites.add(colliderX);
                }

                player.velocity.multiply(1/60.0);

                for (Sprite sprite : sprites) {
                    if(sprite.getType().equals("WALL")) {
                        if(colliderY.isTouching(sprite)) {
                            if(player.velocity.y < 0 && colliderY.getName().equals("Top")) {
                                player.velocity.y = 0;
                            } else if(player.velocity.y > 0 && colliderY.getName().equals("Bottom")) {
                                player.velocity.y = 0;
                            }
                        } else if(colliderX.isTouching(sprite)) {
                            if(player.velocity.x < 0 && colliderX.getName().equals("Left")) {
                                player.velocity.x = 0;
                            } else if(player.velocity.x > 0 && colliderX.getName().equals("Right")) {
                                player.velocity.x = 0;
                            }
                        }
                    }
                };

                player.getLocation().add(player.velocity);

                context.setFill(Color.LIGHTSKYBLUE);
                context.fillRect(0,0, 16*64, 12*64);

                sprites.forEach(sprite -> {
                    sprite.render(context);
                });

                sprites.remove(colliderY);
                sprites.remove(colliderX);
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
