package Start;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import javafx.scene.input.MouseEvent;

public class ImportWebPages
{

	String head = "http://";
	String fullHead = head + "www.";

	// ****************************
	// Referencje
	// ****************************

	private InputField inputField;
	private OutputField outputField;
	private Filter filter;
	private ExportCVS exportCVS;
	private DataBase dataBase;
	private Map<String, Data> outData;

	public String ImportWebPage(String html)
	{

		if (!html.contains(head))
		{
			html = head + html;
		}
		if (html.startsWith(fullHead))
		{
			try
			{
				System.out.println("Downloading web text from: " + html);
				html = Jsoup.connect(html).get().html();
			} catch (IOException e)
			{
				System.err.println(e);
			}
			String result = "";

			Document document = Jsoup.parse(html);
			document.outputSettings(new Document.OutputSettings().prettyPrint(false));// makes html() preserve linebreaks and
																						// spacing
			document.select("br").append("\\n");
			document.select("p").prepend("\\n\n");
			result = document.html().replaceAll("\\s+", " ");
			result = Jsoup.clean(result, "", Whitelist.simpleText(), new Document.OutputSettings().prettyPrint(false));
			result = result.trim().replaceAll("\\s{2,}", "\n");
			// result = result.trim().replaceAll(" +", "\n");
			return result;
		} else
		{
			return "Download error!";
		}

	}

	public void setInputField(InputField inputField)
	{
		this.inputField = inputField;
	}

	public void setOutputField(OutputField outputField)
	{
		this.outputField = outputField;
	}

	public void setFilter(Filter filter)
	{
		this.filter = filter;
	}

	public void setExportCVS(ExportCVS exportCVS)
	{
		this.exportCVS = exportCVS;
	}

	public void setDataBase(DataBase dataBase)
	{
		this.dataBase = dataBase;
	}

	public void setOutData(Map<String, Data> outData)
	{
		this.outData = outData;
	}

}
