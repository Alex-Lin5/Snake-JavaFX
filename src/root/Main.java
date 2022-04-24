package root;

import javafx.application.Application;
import javafx.stage.Stage;
import root.controller.Initializer;

public class Main extends Application {

    public static final int BOARD_WIDTH = 300;
    public static final int BOARD_HEIGHT = 200;
    public static final int PANEL_WIDTH = 0;
    public static final int PANEL_HEIGHT = 50;

    public static void main(String[] args){
        Application.launch(args);
//        launch();
    }
    @Override
    public void start(Stage myStage) throws Exception{

        Initializer theInit = new Initializer(myStage);
        theInit.initialize();

//        Thread t =  new Thread(loop);
//        t.start();
//        t.join();
    }
}

