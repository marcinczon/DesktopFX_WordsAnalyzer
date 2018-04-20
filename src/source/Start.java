package source;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application
{
	//**********************************************************************************************
	// 1. Dodaj export do pliku np excel i pdf
	// 2. Sporobj zrobic baze slow offline, sciagnie z bazy danych porowananie i dopisanie
	// 3. Odczytaj z pliku strony www, pobierz z wszystkich slowa i automatycznie zapisz do bazy
	// 4. Rozbij na w¹tki 
	// 5. Jak sciaga strone to rozbij tak aby by³o widac slowa w InputField
	// 6. Dopisz funkcje testujace 
	//**********************************************************************************************
	
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

		INPUT_FIELD = new InputField(GUI.getPaneLeft().getPrefHeight(), GUI.getPaneLeft().getPrefWidth());
		INPUT_FIELD.setReferenceOutData(DATA_MAP);

		OUTPUT_FIELD = new OutputField(GUI.getPaneRight().getPrefHeight(), GUI.getPaneRight().getPrefWidth());
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
