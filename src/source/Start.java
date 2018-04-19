package source;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application
{
	// Dodaj export do pliku np excel i pdf
	// Sporobj zrobic baze slow offline
	static GUI GUI;
	static InputField INPUT_FIELD;
	static OutputField OUTPUT_FIELD;
	static Filter FILTER = new Filter();
	static ExportCVS EXPORT_CSV = new ExportCVS();
	static ExportDataBase EXPORT_DATA_BASE = new ExportDataBase();
	
	static Map<String, Data> DATA_MAP = new HashMap<>();


	static int HEIGH = 800;
	static int WIDTH = 800;

	public static void main(String[] args)
	{
		GUI = new GUI(HEIGH, WIDTH);

		INPUT_FIELD = new InputField(GUI.paneLeft.getPrefHeight(), GUI.paneLeft.getPrefWidth());
		INPUT_FIELD.setReferenceOutData(DATA_MAP);

		OUTPUT_FIELD = new OutputField(GUI.paneRight.getPrefHeight(), GUI.paneRight.getPrefWidth());
		OUTPUT_FIELD.setReferenceOutData(DATA_MAP);
		OUTPUT_FIELD.setReferenceFilter(FILTER);
		
		EXPORT_CSV.setReferenceDataMap(DATA_MAP);
		EXPORT_CSV.setReferenceFilter(FILTER);
		
		EXPORT_DATA_BASE.setReferenceDataMap(DATA_MAP);
		EXPORT_DATA_BASE.setRefrenceFilter(FILTER);

		GUI.GUIsetReferenceData(INPUT_FIELD, OUTPUT_FIELD);
		GUI.GUIsetReferenceOutData(DATA_MAP);
		GUI.GUIsetReferenceFilter(FILTER);
		GUI.GUIsetReferenceExportCVS(EXPORT_CSV);
		GUI.GUIsetReferenceExportDataBase(EXPORT_DATA_BASE);

		GUI.addToLeftPane(INPUT_FIELD.getInputField());
		GUI.addToRighttPane(OUTPUT_FIELD.getOutputField());

		launch();
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		Scene scene = new Scene(GUI.getPaneMain(), WIDTH, HEIGH);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

}
