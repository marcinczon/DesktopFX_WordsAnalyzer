package source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import com.sun.glass.events.KeyEvent;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GUI
{
	double width;
	double heigh;

	Pane paneMain;

	ScrollPane scrollPaneLeft;
	ScrollPane scrollPaneRight;

	Pane paneLeft;
	Pane paneRight;
	Pane paneTop;
	Pane paneBottom;

	HBox hboxBottom1;
	HBox hboxBottom2;
	HBox hboxBottom3;
	HBox hboxBottom4;
	VBox vboxBottom1;
	VBox vboxBottom2;

	TextField inputText;
	BorderPane boarderPane;

	// Dodatkowe elementy
	Button buttonAnalyze;
	Button buttonUpdate;
	Button buttonDownloadWeb;
	Button buttonTakeGoogleCode;
	Button buttonTranslate;

	Label couterLabel;
	Label textLabel1;
	Label textLabel2;

	TextField minWordLength;
	TextField maxWordLength;
	TextField minCounter;
	TextField maxCounter;
	TextField webTextField;
	TextField googleCodeTextField;

	ToggleGroup groupFilterChoose;
	RadioButton radioSource;
	RadioButton radioTranslate;

	// Referencje do innych klas
	InputField inputField;
	OutputField outputField;
	Map<String, OutData> outData;

	public GUI(double width, double heigh)
	{
		scrollPaneLeft = new ScrollPane();
		scrollPaneRight = new ScrollPane();

		paneMain = new Pane();

		paneLeft = new Pane();
		paneRight = new Pane();
		paneTop = new Pane();
		paneBottom = new Pane();
		hboxBottom1 = new HBox();
		hboxBottom2 = new HBox();
		hboxBottom3 = new HBox();
		hboxBottom4 = new HBox();
		vboxBottom1 = new VBox();
		vboxBottom2 = new VBox();


		boarderPane = new BorderPane();

		this.width = width;
		this.heigh = heigh;

		this.GUIinitialize();
		
		// ******************
		// Top Pane
		// ******************

		buttonDownloadWeb = new Button("Take Web Text");
		buttonDownloadWeb.setLayoutX(10);
		buttonDownloadWeb.setLayoutY(5);
		buttonDownloadWeb.setPrefSize((width / 2) - 10, 40);

		webTextField = new TextField("http://www.google.com");
		webTextField.setLayoutX(10);
		webTextField.setLayoutY(50);
		webTextField.setPrefSize((width / 2) - 10, 40);
		webTextField.setAlignment(Pos.CENTER_RIGHT);

		buttonTakeGoogleCode = new Button("Update code");
		buttonTakeGoogleCode.setLayoutX(width / 2);
		buttonTakeGoogleCode.setLayoutY(5);
		buttonTakeGoogleCode.setPrefSize((width / 4) - 2, 40);

		buttonTranslate = new Button("Translate");
		buttonTranslate.setLayoutX(width - width / 4);
		buttonTranslate.setLayoutY(5);
		buttonTranslate.setPrefSize((width / 4) - 2, 40);

		googleCodeTextField = new TextField("Google Code");
		googleCodeTextField.setLayoutX(width / 2);
		googleCodeTextField.setLayoutY(50);
		googleCodeTextField.setPrefSize((width / 2) - 2, 40);
		googleCodeTextField.setAlignment(Pos.CENTER_RIGHT);

		paneTop.getChildren().addAll(buttonDownloadWeb, webTextField, buttonTakeGoogleCode, googleCodeTextField, buttonTranslate);

		// ******************
		// Bottom Pane
		// ******************

		couterLabel = new Label(String.format("%4d", 0));
		couterLabel.setStyle("-fx-font: normal  50px 'serif' ");
		couterLabel.setTextFill(Color.BLUE);
		couterLabel.setLayoutX(width/2+300);
		couterLabel.setLayoutY(30);

		String style = "-fx-font: normal 18px 'Calibri' ";
		buttonAnalyze = new Button("Analyze");
		buttonAnalyze.setPrefWidth(100);
		buttonUpdate = new Button("Update");
		buttonUpdate.setPrefWidth(100);
		textLabel1 = new Label("Length:    ");
		textLabel1.setStyle(style);

		minWordLength = new TextField("1");
		minWordLength.setPrefSize(100, 15);
		minWordLength.setAlignment(Pos.BASELINE_RIGHT);

		maxWordLength = new TextField("99");
		maxWordLength.setPrefSize(100, 15);
		maxWordLength.setAlignment(Pos.BASELINE_RIGHT);

		// ************************
		// *** Counter Filter
		// ************************
		textLabel2 = new Label("Counter:  ");
		textLabel2.setStyle(style);
		minCounter = new TextField("1");
		minCounter.setPrefSize(100, 15);
		minCounter.setAlignment(Pos.BASELINE_RIGHT);
		maxCounter = new TextField("9999");
		maxCounter.setAlignment(Pos.BASELINE_RIGHT);
		maxCounter.setPrefSize(100, 15);

		hboxBottom1.setAlignment(Pos.BASELINE_RIGHT);
		hboxBottom1.setSpacing(1);
		hboxBottom1.getChildren().addAll(buttonAnalyze, buttonUpdate);
		hboxBottom2.getChildren().addAll(textLabel1, minWordLength, maxWordLength);
		hboxBottom3.getChildren().addAll(textLabel2, minCounter, maxCounter);

		vboxBottom1.setSpacing(5);
		
		groupFilterChoose = new ToggleGroup();
		radioSource = new RadioButton("Source");
		radioTranslate = new RadioButton("Translate");
		radioSource.setSelected(true);
		radioSource.setToggleGroup(groupFilterChoose);
		radioTranslate.setToggleGroup(groupFilterChoose);
		
		
		vboxBottom2.setSpacing(10);
		vboxBottom2.setLayoutX(width/2-120);
		vboxBottom2.setLayoutY(paneBottom.getLayoutY()+45);
		
		vboxBottom2.getChildren().addAll(radioSource,radioTranslate);
		
		vboxBottom1.getChildren().addAll(hboxBottom1, hboxBottom2, hboxBottom3);
		paneBottom.getChildren().addAll(vboxBottom1,vboxBottom2,couterLabel);

		
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

			@Override
			public void handle(MouseEvent event)
			{
				inputField.writeAllWords();
				outputField.updateTable(1);
				couterLabel.setText(String.format("%4d", outData.size()));
			}
		});

		buttonUpdate.setOnMouseClicked(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				outputField.setMinLengthRange(Integer.parseInt(minWordLength.getText()));
				outputField.setMaxLengthRange(Integer.parseInt(maxWordLength.getText()));
				outputField.setMinCounterRange(Integer.parseInt(minCounter.getText()));
				outputField.setMaxCounterRange(Integer.parseInt(maxCounter.getText()));
				if(radioSource.isSelected())
				{
					outputField.updateTable(1);
					System.out.println("Radio source");
				}
				else if(radioTranslate.isSelected())
				{
					outputField.updateTable(2);
					System.out.println("Radio translate");
				}
				couterLabel.setText(String.format("%d", outData.size()));
			}
		});

		buttonDownloadWeb.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				String html = null;
				try
				{
					html = Jsoup.connect(webTextField.getText()).get().html();
				} catch (IOException e)
				{
					// System.err.println(e);
				}
				String result = "";

				Document document = Jsoup.parse(html);
				document.outputSettings(new Document.OutputSettings().prettyPrint(false));// makes html() preserve linebreaks and
																							// spacing
				document.select("br").append("\\n");
				document.select("p").prepend("\\n\\n");
				result = document.html().replaceAll("\\s+", " ");
				// result = result.replaceAll("\\S","");
				result = Jsoup.clean(result, "", Whitelist.simpleText(), new Document.OutputSettings().prettyPrint(false));

				System.out.println(result);
				inputField.inputField.appendText(result);
			}
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
		if (outputField != null)
		{
			System.out.println("Range are initialized.");
			outputField.setMinLengthRange(Integer.parseInt(minWordLength.getText()));
			outputField.setMaxLengthRange(Integer.parseInt(maxWordLength.getText()));
			outputField.setMinCounterRange(Integer.parseInt(minCounter.getText()));
			outputField.setMaxCounterRange(Integer.parseInt(maxCounter.getText()));
		}
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

	public void GUIsetReferenceOutData(Map<String, OutData> outData)
	{
		this.outData = outData;
	}

}
