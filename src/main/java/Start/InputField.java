package Start;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.control.TextArea;

public class InputField
{
	
	private TextArea inputField;

	// ****************************
	// Referencje
	// ****************************
	
	private Map<String, Data> dataMap;

	public InputField(double heigh, double width)
	{
		inputField = new TextArea();
		inputField.setPrefSize(width, heigh);
	}

	public TextArea getInputField()
	{
		return inputField;
	}

	public String getText()
	{
		return inputField.getText();
	}
	public void clear()
	{
		inputField.clear();
	}
	private String replaceSpecialSing(String tempString)
	{
		tempString = tempString.replaceAll("ą", "a");
		tempString = tempString.replaceAll("ć", "c");
		tempString = tempString.replaceAll("ę", "e");
		tempString = tempString.replaceAll("ł", "l");
		tempString = tempString.replaceAll("ó", "o");
		tempString = tempString.replaceAll("ń", "n");
		tempString = tempString.replaceAll("ż", "z");
		tempString = tempString.replaceAll("ź", "z");
		return tempString;
	}
	public void writeAllWords()
	{
		String tempString = inputField.getText().toLowerCase();
		tempString = replaceSpecialSing(tempString);
		
		tempString = tempString.replaceAll("[^a-zA-Z]", " ").toLowerCase();
		for(int i =0 ; i < 10 ; i++)
		{
			tempString = tempString.replace(Integer.toString(i), " ");
		}
		String[] tempListWord = tempString.split("\\s");	

		dataMap.clear();

		for (String word : tempListWord)
		{
			if (!dataMap.containsKey(word))
			{
				dataMap.put(word, new Data(word));
			} else
			{
				dataMap.get(word).increseCounter();
			}
		}

	}

	public void setReferenceOutData(Map<String, Data> outData)
	{
		this.dataMap = outData;
	}

}
