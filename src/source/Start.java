package source;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application
{
	static GUI gui;
	static InputField inputField;
	static OutputField outputField;
	static Map<String, OutData> outData = new HashMap<>();

	static int heigh = 800;
	static int width = 800;

	public static void main(String[] args)
	{
		gui = new GUI(heigh, width);

		inputField = new InputField(gui.paneLeft.getPrefHeight(), gui.paneLeft.getPrefWidth());
		inputField.setReferenceOutData(outData);

		outputField = new OutputField(gui.paneRight.getPrefHeight(), gui.paneRight.getPrefWidth());
		outputField.setReferenceOutData(outData);

		gui.GUIsetReferenceData(inputField, outputField);
		gui.GUIsetReferenceOutData(outData);

		gui.addToLeftPane(inputField.getInputField());
		gui.addToRighttPane(outputField.getOutputField());

		launch();
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		Scene scene = new Scene(gui.getPaneMain(), width, heigh);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

}
