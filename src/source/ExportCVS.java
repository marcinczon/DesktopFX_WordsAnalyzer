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
	// ****************************
	// Referencje
	// ****************************
	
	private Map<String, Data> dataMap;
	private Filter filter;

	public void setReferenceDataMap(Map<String, Data> outData)
	{
		this.dataMap = outData;
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

		Set<String> keys = dataMap.keySet();
		StringBuilder sb = new StringBuilder();
		Data tempData;
		for (String key : keys)
		{
			tempData = dataMap.get(key);
			if (tempData != null)
				if (filter.isInFilterRange(tempData))
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
