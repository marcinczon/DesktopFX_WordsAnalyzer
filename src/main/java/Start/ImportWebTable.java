package Start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.stage.FileChooser;

public class ImportWebTable
{
	private ArrayList<String> webTable = new ArrayList<>();
	private String websString;

	public ArrayList<String> ImportWebTable() throws IOException
	{
		webTable.clear();

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(null);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		String webs = null;
		websString = "";
		while ((webs = bufferedReader.readLine()) != null)
		{
			webTable.add(webs);
			websString = websString + "\n" + webs;
			System.out.println(webs.toString());
		}

		return webTable;
	}

	public String getWebsString()
	{
		return websString;
	}
}
