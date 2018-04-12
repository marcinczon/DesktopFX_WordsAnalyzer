package source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class ExportCVS
{
	private String path = "Data.csv";
	private PrintWriter pw;
	private StringBuilder sb;

	private Map<String, OutData> outData;
	private Filter filter;

	public void setReferenceOutData(Map<String, OutData> outData)
	{
		this.outData = outData;
	}

	public void setReferenceFilter(Filter filter)
	{
		this.filter = filter;
	}

	public boolean export() throws IOException
	{
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showSaveDialog(null);
		FileWriter fileWriter = new FileWriter(file);

		Set<String> keys = outData.keySet();
		StringBuilder sb = new StringBuilder();
		OutData tempData;
		for (String key : keys)
		{
			tempData = outData.get(key);
			tempData.setColumnToFilter(filter.getColumn());
			if(tempData!=null)
			if (tempData.isInLengthRange(filter.getMinLengthRange(), filter.getMaxLengthRange()) && tempData.isInCounterRange(filter.getMinCounterRange(), filter.getMaxCounterRange()))
			{
				System.out.println("CSV: " + tempData.toString());
				sb.append(String.format("%8d", tempData.getCounter()));
				sb.append(',');
				sb.append(String.format("%20s", tempData.getSource()));
				sb.append(',');
				sb.append(String.format("%20s", tempData.getTranslate()));
				sb.append('\n');
			}

		}
		fileWriter.write(sb.toString());
		fileWriter.close();

		return false;
	}

}
