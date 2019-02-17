package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.originRuler;

/**
 * @author Saif Jame
 * @author Philip Aquilina
 */
public class pic extends Application {
	@Override
	public void start(Stage primary) throws Exception {
		originRuler.start(primary);
	}
	@Override
	public void stop(){
        originRuler.putdiagram();
	}
	public static void main(String[] args) {
		launch(args);
	}
}