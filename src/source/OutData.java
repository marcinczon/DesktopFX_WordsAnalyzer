package source;

import java.util.Comparator;

public class OutData
{

	private int Counter = 1;
	private int SourceWordLength = 0;
	private int TranslateWordLength = 0;
	private int ColumnToFilter = 1; // 1 - source 2 - translated
	private String Source = "";
	private String Translate = "";

	OutData(String source)
	{
		this.Source = source;
		this.Translate = "";
		InitParameters();
	}

	OutData(String source, String translate)
	{
		this.Source = source;
		this.Translate = translate;
		InitParameters();
	}

	OutData(int counter, String source, String translate)
	{
		this.Counter = counter;
		this.Source = source;
		this.Translate = translate;
		InitParameters();
	}

	private void InitParameters()
	{
		SourceWordLength = Source.length();
		TranslateWordLength = Translate.length();
	}

	public boolean isInLengthRange(int min, int max)
	{
		if (ColumnToFilter == 1)
		{
			return min <= SourceWordLength && SourceWordLength <= max;
		} else if (ColumnToFilter == 2)
		{
			return min <= TranslateWordLength && TranslateWordLength <= max;
		} else
			return false;
	}

	public boolean isInCounterRange(int min, int max)
	{
		return min <= Counter && Counter <= max;
	}

	public void increseCounter()
	{
		Counter++;
	}

	public int getCounter()
	{
		return Counter;
	}

	public void setTranslateWord(String translateWord)
	{
		this.Translate = translateWord;
		TranslateWordLength = translateWord.length();
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Source == null) ? 0 : Source.hashCode());
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
		OutData other = (OutData) obj;
		if (Source == null)
		{
			if (other.Source != null)
				return false;
		} else if (!Source.equals(other.Source))
			return false;
		return true;
	}

	public String getSource()
	{
		return Source;
	}

	public String getTranslate()
	{
		return Translate;
	}

	public void setColumnToFilter(int columnToFilter)
	{
		ColumnToFilter = columnToFilter;
	}

	@Override
	public String toString()
	{
		return "OutData [Counter=" + Counter + ", Source=" + Source + ", Translate=" + Translate + "]";
	}

	public static class SourceComparator implements Comparator<OutData>
	{
		@Override
		public int compare(OutData data1, OutData data2)
		{
			return data1.Source.compareTo(data2.Source);
		}
	}

	public static class TranslateComparator implements Comparator<OutData>
	{
		@Override
		public int compare(OutData data1, OutData data2)
		{
			return data1.Translate.compareTo(data2.Translate);
		}
	}

	public static class CounterComparator implements Comparator<OutData>
	{
		@Override
		public int compare(OutData data1, OutData data2)
		{
			return Integer.toString(data1.Counter).compareTo(Integer.toString(data2.Counter));
		}
	}

}