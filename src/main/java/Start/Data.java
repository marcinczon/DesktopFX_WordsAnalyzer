package Start;

import java.util.Comparator;

public class Data
{
	// ****************************
	// Private
	// ****************************
	
	private int counter = 1;
	private int sourceWordLength = 0;
	private int translateWordLength = 0;
	private String source = "null";
	private String translate = "null";

	// ****************************
	// 
	// ****************************
	
	Data(String source)
	{
		this.source = source;
		InitParameters();
	}

	Data(String source, String translate)
	{
		this.source = source;
		this.translate = translate;
		InitParameters();
	}

	Data(int counter, String source, String translate)
	{
		if (counter > 1)
			this.counter = counter;
		if (source != null)
		{
			this.source = source;
		}
		if (translate != null)
		{
			this.translate = translate;
		}
		InitParameters();
	}

	private void InitParameters()
	{
		sourceWordLength = source.length();
		translateWordLength = translate.length();
	}

	public void increseCounter()
	{
		counter++;
	}
	public void increseCounter(int counter)
	{
		this.counter=this.counter+counter;
	}

	public int getCounter()
	{
		return counter;
	}

	public void setTranslateWord(String translateWord)
	{
		this.translate = translateWord;
		translateWordLength = translateWord.length();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (source == null)
		{
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

	public String getSource()
	{
		return source;
	}

	public String getTranslate()
	{
		return translate;
	}

	@Override
	public String toString()
	{
		return "OutData [Counter=" + counter + ", Source=" + source + ", Translate=" + translate + "]";
	}

	public static class SourceComparator implements Comparator<Data>
	{
		public int compare(Data data1, Data data2)
		{
			return data1.source.compareTo(data2.source);
		}
	}

	public static class TranslateComparator implements Comparator<Data>
	{
		public int compare(Data data1, Data data2)
		{
			return data1.translate.compareTo(data2.translate);
		}
	}

	public static class CounterComparator implements Comparator<Data>
	{
		public int compare(Data data1, Data data2)
		{
			return Integer.toString(data1.counter).compareTo(Integer.toString(data2.counter));
		}
	}

	public int getSourceWordLength()
	{
		return sourceWordLength;
	}

	public int getTranslateWordLength()
	{
		return translateWordLength;
	}

}