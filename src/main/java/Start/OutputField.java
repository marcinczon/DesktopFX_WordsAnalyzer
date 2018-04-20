package Start;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OutputField
{
	private TableView<Data> outputField = new TableView<Data>();
	private TableColumn<Data, Integer> counter = new TableColumn<Data, Integer>("Counter");
	private TableColumn<Data, String> source = new TableColumn<Data, String>("Source");
	private TableColumn<Data, String> translate = new TableColumn<Data, String>("Translate");

	// ****************************
	// Referencje
	// ****************************

	Map<String, Data> outData;
	Filter filter;

	public OutputField(double heigh, double width)
	{
		outputField.setEditable(true);

		counter.setCellValueFactory(new PropertyValueFactory<Data, Integer>("Counter"));
		counter.setPrefWidth(100);
		source.setCellValueFactory(new PropertyValueFactory<Data, String>("Source"));
		source.setPrefWidth(150);
		translate.setCellValueFactory(new PropertyValueFactory<Data, String>("Translate"));
		translate.setPrefWidth(150);

		outputField.getColumns().addAll(counter, source, translate);
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
			if (filter.isInFilterRange(tempData))
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
