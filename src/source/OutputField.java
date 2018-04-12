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
	Map<String, OutData> outData;
	Filter filter;


	TableView<OutData> outputField = new TableView<OutData>();
	TableColumn<OutData, Integer> Counter = new TableColumn<OutData, Integer>("Counter");
	TableColumn<OutData, String> Source = new TableColumn<OutData, String>("Source");
	TableColumn<OutData, String> Translate = new TableColumn<OutData, String>("Translate");

	public OutputField(double heigh, double width)
	{
		outputField.setEditable(true);

		Counter.setCellValueFactory(new PropertyValueFactory<OutData, Integer>("Counter"));
		Counter.setPrefWidth(100);
		Source.setCellValueFactory(new PropertyValueFactory<OutData, String>("Source"));
		Source.setPrefWidth(150);
		Translate.setCellValueFactory(new PropertyValueFactory<OutData, String>("Translate"));
		Translate.setPrefWidth(150);

		outputField.getColumns().addAll(Counter, Source, Translate);
		outputField.setPrefSize(width, heigh);
	}

	public TableView<OutData> getOutputField()
	{
		return outputField;
	}

	public void updateTable()
	{
		Set<String> keys = outData.keySet();
		outputField.getItems().clear();

		OutData tempData;
		for (String key : keys)
		{
			tempData = outData.get(key);
			tempData.setColumnToFilter(filter.getColumn());
			if (tempData.isInLengthRange(filter.getMinLengthRange(), filter.getMaxLengthRange()) && tempData.isInCounterRange(filter.getMinCounterRange(), filter.getMaxCounterRange()))
			{
				outputField.getItems().add(tempData);
			}
		}

	}

	public void translateTable(String Language)
	{
		Set<String> keys = outData.keySet();
		OutData tempData;
		for (String key : keys)
		{
			tempData = outData.get(key);
			tempData.setTranslateWord(GoogleTranslator.Translate(tempData.getSource(), Language));
		}
	}

	public void setReferenceOutData(Map<String, OutData> outData)
	{
		this.outData = outData;
	}

	public void setReferenceFilter(Filter filter)
	{
		this.filter = filter;
	}


}
