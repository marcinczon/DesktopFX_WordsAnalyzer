package source;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.control.TextArea;

public class InputField
{
	TextArea inputField;

	// Referencje do innych klas
	Map<String, Data> outData;

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

	public void writeAllWords()
	{
		String tempString = inputField.getText().toLowerCase();
		tempString = tempString.replaceAll("�", "a");
		tempString = tempString.replaceAll("�", "c");
		tempString = tempString.replaceAll("�", "e");
		tempString = tempString.replaceAll("�", "l");
		tempString = tempString.replaceAll("�", "o");
		tempString = tempString.replaceAll("�", "n");
		
		tempString = tempString.replaceAll("[^a-zA-Z]", " ").toLowerCase();
		for(int i =0 ; i < 10 ; i++)
		{
			tempString = tempString.replace(Integer.toString(i), " ");
		}
		String[] tempListWord = tempString.split("\\s");	

		outData.clear();

		for (String word : tempListWord)
		{
			if (!outData.containsKey(word))
			{
				outData.put(word, new Data(word));
			} else
			{
				outData.get(word).increseCounter();
			}
		}

	}

	public void setReferenceOutData(Map<String, Data> outData)
	{
		this.outData = outData;
	}

}
