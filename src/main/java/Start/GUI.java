package Start;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class GUI
{
	// ****************************
	// Elementy interfejsu
	// ****************************

	private double width = 800;
	private double heigh = 800;

	private ScrollPane scrollPaneLeft = new ScrollPane();
	private ScrollPane scrollPaneRight = new ScrollPane();

	private BorderPane boarderPane = new BorderPane();

	private Pane paneMain = new Pane();
	private Pane paneLeft = new Pane();
	private Pane paneRight = new Pane();
	private Pane paneTop = new Pane();
	private Pane paneBottom = new Pane();

	private HBox hboxBottom1 = new HBox();
	private HBox hboxBottom2 = new HBox();
	private HBox hboxBottom3 = new HBox();

	private VBox vboxBottom1 = new VBox();
	private VBox vboxBottom2 = new VBox();

	private Button buttonAnalyze = new Button("Analyze");
	private Button buttonUpdate = new Button("Update");
	private Button buttonDownloadWeb = new Button("Take Web Text");
	private Button buttonTranslate = new Button("Translate");
	private Button buttonExportCSV = new Button("Export CSV");
	private Button buttonExportDB = new Button("Export DB");
	private Button buttonImportDB = new Button("Import DB");

	private Label couterLabel = new Label(String.format("%4d", 0));
	private Label textLabel1 = new Label("Length:    ");
	private Label textLabel2 = new Label("Counter:  ");

	private TextField minWordLength = new TextField("1");
	private TextField maxWordLength = new TextField("99");
	private TextField minCounter = new TextField("3");
	private TextField maxCounter = new TextField("999");
	private TextField webTextField = new TextField("http://www.google.com");
	static TextField googleCodeTextField = new TextField("Google Code");
	private TextField setLanguage = new TextField("Language");

	private Alert alert;

	private ToggleGroup groupFilterChoose = new ToggleGroup();
	private RadioButton radioSource = new RadioButton("Source");
	private RadioButton radioTranslate = new RadioButton("Translate");

	// ****************************
	// Referencje
	// ****************************

	private InputField inputField;
	private OutputField outputField;
	private Filter filter;
	private ExportCVS exportCVS;
	private DataBase dataBase;
	private ImportWebPages importWebPages;
	private Map<String, Data> outData;

	public GUI(double width, double heigh)
	{

		this.width = width;
		this.heigh = heigh;

		this.GUIinitialize();

		// ******************
		// Top Pane
		// ******************

		buttonDownloadWeb.setLayoutX(10);
		buttonDownloadWeb.setLayoutY(5);
		buttonDownloadWeb.setPrefSize((width / 2) - 10, 40);

		webTextField.setLayoutX(10);
		webTextField.setLayoutY(50);
		webTextField.setPrefSize((width / 2) - 10, 40);
		webTextField.setAlignment(Pos.CENTER_RIGHT);

		setLanguage.setAlignment(Pos.CENTER_RIGHT);
		setLanguage.setLayoutX(width / 2);
		setLanguage.setLayoutY(50);
		setLanguage.setPrefSize((width / 4) - 2, 40);

		buttonTranslate.setLayoutX(width / 2);
		buttonTranslate.setLayoutY(5);
		buttonTranslate.setPrefSize((width / 2) - 2, 40);

		googleCodeTextField.setLayoutX(width / 2 + width / 4);
		googleCodeTextField.setLayoutY(50);
		googleCodeTextField.setPrefSize((width / 4) - 2, 40);
		googleCodeTextField.setAlignment(Pos.CENTER_RIGHT);

		paneTop.getChildren().addAll(buttonDownloadWeb, webTextField, setLanguage, googleCodeTextField, buttonTranslate);

		// ******************
		// Bottom Pane
		// ******************

		couterLabel.setStyle("-fx-font: normal  50px 'serif' ");
		couterLabel.setTextFill(Color.BLUE);
		couterLabel.setLayoutX(width / 2 + 300);
		couterLabel.setLayoutY(30);

		String style = "-fx-font: normal 18px 'Calibri' ";
		buttonAnalyze.setPrefWidth(100);
		buttonUpdate.setPrefWidth(100);
		buttonExportCSV.setPrefWidth(100);
		buttonExportDB.setPrefWidth(100);
		buttonImportDB.setPrefWidth(100);

		textLabel1.setStyle(style);

		minWordLength.setPrefSize(100, 15);
		minWordLength.setAlignment(Pos.BASELINE_RIGHT);

		maxWordLength.setPrefSize(100, 15);
		maxWordLength.setAlignment(Pos.BASELINE_RIGHT);

		// ************************
		// *** Counter Filter
		// ************************
		textLabel2.setStyle(style);
		minCounter.setPrefSize(100, 15);
		minCounter.setAlignment(Pos.BASELINE_RIGHT);
		maxCounter.setAlignment(Pos.BASELINE_RIGHT);
		maxCounter.setPrefSize(100, 15);

		hboxBottom1.setAlignment(Pos.BASELINE_RIGHT);
		hboxBottom1.setSpacing(1);
		hboxBottom1.getChildren().addAll(buttonAnalyze, buttonUpdate, buttonExportCSV, buttonImportDB, buttonExportDB);
		hboxBottom2.getChildren().addAll(textLabel1, minWordLength, maxWordLength);
		hboxBottom3.getChildren().addAll(textLabel2, minCounter, maxCounter);

		vboxBottom1.setSpacing(5);

		radioSource.setSelected(true);
		radioSource.setToggleGroup(groupFilterChoose);
		radioTranslate.setToggleGroup(groupFilterChoose);

		vboxBottom2.setSpacing(10);
		vboxBottom2.setLayoutX(width / 2 - 120);
		vboxBottom2.setLayoutY(paneBottom.getLayoutY() + 45);

		vboxBottom2.getChildren().addAll(radioSource, radioTranslate);

		vboxBottom1.getChildren().addAll(hboxBottom1, hboxBottom2, hboxBottom3);
		paneBottom.getChildren().addAll(vboxBottom1, vboxBottom2, couterLabel);

		this.GUIinitializeButton();
	}

	private void GUIinitialize()
	{
		scrollPaneLeft.setContent(paneLeft);
		scrollPaneRight.setContent(paneRight);

		boarderPane.setLeft(scrollPaneLeft);
		boarderPane.setRight(scrollPaneRight);
		boarderPane.setTop(paneTop);
		boarderPane.setBottom(paneBottom);

		paneTop.setPrefSize(width, 100);
		paneBottom.setPrefSize(width, 100);
		paneLeft.setPrefSize(width / 2, heigh - (paneTop.getPrefHeight() + paneBottom.getPrefHeight()));
		paneRight.setPrefSize(width / 2, heigh - (paneTop.getPrefHeight() + paneBottom.getPrefHeight()));

		paneMain.getChildren().add(boarderPane);
		paneMain.setPrefWidth(width);
		paneMain.setPrefHeight(heigh);

	}

	private void GUIinitializeButton()
	{
		buttonAnalyze.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				inputField.writeAllWords();
				outputField.updateTable();
				couterLabel.setText(String.format("%4d", outData.size()));
			}
		});

		buttonUpdate.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				UpdateTable();
				couterLabel.setText(String.format("%4d", outData.size()));

			}
		});
		buttonExportCSV.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				try
				{
					exportCVS.export();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		buttonExportDB.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				showExportTextDialog();
			}
		});
		buttonImportDB.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				showImportTextDialog();
			}
		});

		buttonDownloadWeb.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				inputField.clear();
				inputField.getInputField().appendText(importWebPages.ImportWebPage(webTextField.getText()));
			}
		});

		buttonTranslate.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				outputField.translateTable(setLanguage.getText());
				UpdateTable();
			}
		});

		minWordLength.textProperty().addListener((observable, oldValue, newValue) ->
		{
			if (!newValue.matches("[0-9]+"))
			{
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Minimum Length");
				alert.setContentText("Enter only numbers !");
				alert.showAndWait();
				minWordLength.setText(oldValue);
			} else
			{
				filter.setMinLengthRange(Integer.parseInt(minWordLength.getText()));
			}
		});
		maxWordLength.textProperty().addListener((observable, oldValue, newValue) ->
		{
			if (!newValue.matches("[0-9]+"))
			{
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Maximum Length");
				alert.setContentText("Enter only numbers !");
				alert.showAndWait();
				maxWordLength.setText(oldValue);
			} else
			{
				filter.setMaxLengthRange(Integer.parseInt(maxWordLength.getText()));
			}
		});
		minCounter.textProperty().addListener((observable, oldValue, newValue) ->
		{
			if (!newValue.matches("[0-9]+"))
			{
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Minimum Counter");
				alert.setContentText("Enter only numbers !");
				alert.showAndWait();
				minCounter.setText(oldValue);
			} else
			{
				filter.setMinCounterRange(Integer.parseInt(minCounter.getText()));
			}
		});
		maxCounter.textProperty().addListener((observable, oldValue, newValue) ->
		{
			if (!newValue.matches("[0-9]+"))
			{
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Maximum Counter");
				alert.setContentText("Enter only numbers !");
				alert.showAndWait();
				maxCounter.setText(oldValue);
			} else
			{
				filter.setMaxCounterRange(Integer.parseInt(maxCounter.getText()));
			}
		});

		groupFilterChoose.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
		{
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle)
			{
				if (groupFilterChoose.getSelectedToggle() != null)
				{
					if (radioSource.isSelected())
					{
						filter.setKolumn(FilterSetting.KOLUMN_SOURCE);

					} else if (radioTranslate.isSelected())
					{
						filter.setKolumn(FilterSetting.KOLUMN_TRANSLATE);
					}
				}
			}
		});

	}

	private void UpdateTable()
	{
		outputField.updateTable();
	}

	private void showExportTextDialog()
	{

		TextInputDialog dialogExportDB = new TextInputDialog("OracleDB_Words");

		dialogExportDB.setTitle("Export DB");
		dialogExportDB.setHeaderText("Enter table name:");
		dialogExportDB.setContentText("Name:");

		Optional<String> result = dialogExportDB.showAndWait();

		result.ifPresent(name ->
		{
			dataBase.Connect(name);
			dataBase.createTable(name);
			dataBase.exportTable();
		});
	}

	private void showImportTextDialog()
	{

		TextInputDialog dialogExportDB = new TextInputDialog("OracleDB_Words");

		dialogExportDB.setTitle("Export DB");
		dialogExportDB.setHeaderText("Enter table name:");
		dialogExportDB.setContentText("Name:");

		Optional<String> result = dialogExportDB.showAndWait();

		result.ifPresent(name ->
		{
			dataBase.Connect(name);
			dataBase.importTable();
			outputField.updateTable();
		});
	}

	public void GUIsetReferenceData(InputField inputField, OutputField outputField)
	{
		if (inputField != null && outputField != null)
		{
			this.inputField = inputField;
			this.outputField = outputField;
		} else
		{
			System.err.println("GUI Reference Failed");
		}
	}

	public void GUIsetReferenceFilter(Filter filter)
	{
		this.filter = filter;
	}

	public Pane getPaneMain()
	{
		return paneMain;
	}

	public void addToLeftPane(Node shape)
	{
		paneLeft.getChildren().add(shape);
	}

	public void addToRighttPane(Node shape)
	{
		paneRight.getChildren().add(shape);
	}

	public void addToTopPane(Node shape)
	{
		paneTop.getChildren().add(shape);
	}

	public void addToBottomPane(Node shape)
	{
		paneBottom.getChildren().add(shape);
	}

	public void GUIsetReferenceOutData(Map<String, Data> outData)
	{
		this.outData = outData;
	}

	public void GUIsetReferenceExportCVS(ExportCVS exportCVS)
	{
		this.exportCVS = exportCVS;
	}

	public void GUIsetReferenceExportDataBase(DataBase exportDataBase)
	{
		this.dataBase = exportDataBase;
	}

	public TextField getGoogleCodeTextField()
	{
		return googleCodeTextField;
	}

	public void setGoogleCodeTextField(TextField googleCodeTextField)
	{
		this.googleCodeTextField = googleCodeTextField;
	}

	public Pane getPaneLeft()
	{
		return paneLeft;
	}

	public void setPaneLeft(Pane paneLeft)
	{
		this.paneLeft = paneLeft;
	}

	public Pane getPaneRight()
	{
		return paneRight;
	}

	public void setPaneRight(Pane paneRight)
	{
		this.paneRight = paneRight;
	}

	public Pane getPaneTop()
	{
		return paneTop;
	}

	public void setPaneTop(Pane paneTop)
	{
		this.paneTop = paneTop;
	}

	public Pane getPaneBottom()
	{
		return paneBottom;
	}

	public void setPaneBottom(Pane paneBottom)
	{
		this.paneBottom = paneBottom;
	}

	public void setPaneMain(Pane paneMain)
	{
		this.paneMain = paneMain;
	}

	public void GUIsetReferenceImportWebPages(ImportWebPages importWebPages)
	{
		this.importWebPages = importWebPages;
	}

}
