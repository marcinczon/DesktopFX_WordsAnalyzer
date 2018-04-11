package source;
public class OutData implements Comparable<OutData>
{

	private int Counter = 1;
	private int SourceWordLength = 0;
	private int TranslateWordLength = 0;
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
		return min<=SourceWordLength && SourceWordLength<=max;
	}
	public boolean isInCounterRange(int min, int max)
	{
		return min<=Counter && Counter<=max;
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
	public int compareTo(OutData outData)
	{
		return this.Source.compareToIgnoreCase(outData.Source);
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

}