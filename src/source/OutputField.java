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
	int minLengthRange, maxLengthRange;
	int minCounterRange, maxCounterRange;

	TableView<OutData> outputField = new TableView<OutData>();
	TableColumn<OutData, Integer> Counter = new TableColumn<OutData, Integer>("Counter");
	TableColumn<OutData, String> Source = new TableColumn<OutData, String>("Source");
	TableColumn<OutData, String> Translate = new TableColumn<OutData, String>("Translate");

	public OutputField(double heigh, double width)
	{
		outputField.setEditable(true);

		Counter.setCellValueFactory(new PropertyValueFactory<OutData, Integer>("Counter"));
		Source.setCellValueFactory(new PropertyValueFactory<OutData, String>("Source"));
		Translate.setCellValueFactory(new PropertyValueFactory<OutData, String>("Translate"));

		outputField.getColumns().addAll(Counter, Source, Translate);
		outputField.setPrefSize(width, heigh);
	}

	public TableView<OutData> getOutputField()
	{
		return outputField;
	}

	public void updateTable(int byColumn)
	{
		Set<String> keys = outData.keySet();
		outputField.getItems().clear();

		OutData tempData;
		for (String key : keys)
		{
			tempData = outData.get(key);
			tempData.setColumnToFilter(byColumn);
			if (tempData.isInLengthRange(minLengthRange, maxLengthRange) && tempData.isInCounterRange(minCounterRange, maxCounterRange))
			{
				outputField.getItems().add(tempData);
			}
		}

	}

	public void setReferenceOutData(Map<String, OutData> outData)
	{
		this.outData = outData;
	}

	public void setMinLengthRange(int minRange)
	{
		this.minLengthRange = minRange;
	}

	public void setMaxLengthRange(int maxRange)
	{
		this.maxLengthRange = maxRange;
	}
	public void setMinCounterRange(int minRange)
	{
		this.minCounterRange = minRange;
	}

	public void setMaxCounterRange(int maxRange)
	{
		this.maxCounterRange = maxRange;
	}
}
