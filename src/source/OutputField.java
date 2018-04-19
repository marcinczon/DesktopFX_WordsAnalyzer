package source;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OutputField
{

	// Referencje do innych klas
	Map<String, Data> outData;
	Filter filter;


	TableView<Data> outputField = new TableView<Data>();
	TableColumn<Data, Integer> Counter = new TableColumn<Data, Integer>("Counter");
	TableColumn<Data, String> Source = new TableColumn<Data, String>("Source");
	TableColumn<Data, String> Translate = new TableColumn<Data, String>("Translate");

	public OutputField(double heigh, double width)
	{
		outputField.setEditable(true);

		Counter.setCellValueFactory(new PropertyValueFactory<Data, Integer>("Counter"));
		Counter.setPrefWidth(100);
		Source.setCellValueFactory(new PropertyValueFactory<Data, String>("Source"));
		Source.setPrefWidth(150);
		Translate.setCellValueFactory(new PropertyValueFactory<Data, String>("Translate"));
		Translate.setPrefWidth(150);

		outputField.getColumns().addAll(Counter, Source, Translate);
		outputField.setPrefSize(width, heigh);
	}

	public TableView<Data> getOutputField()
	{
		return outputField;
	}

	public void updateTable()
	{
		Set<String> keys = outData.keySet();
		outputField.getItems().clear();

		Data tempData;
		for (String key : keys)
		{
			tempData = outData.get(key);
			tempData.setColumnToFilter(filter.getColumn());
			if(filter.isInFilterRange(tempData))
			{
				outputField.getItems().add(tempData);
			}
		}

	}

	public void translateTable(String Language)
	{
		Set<String> keys = outData.keySet();
		Data tempData;
		for (String key : keys)
		{
			tempData = outData.get(key);
			tempData.setTranslateWord(GoogleTranslator.Translate(tempData.getSource(), Language));
		}
	}

	public void setReferenceOutData(Map<String, Data> outData)
	{
		this.outData = outData;
	}

	public void setReferenceFilter(Filter filter)
	{
		this.filter = filter;
	}


}
