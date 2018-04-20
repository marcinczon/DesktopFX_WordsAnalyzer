package Start;

public class Filter
{
	private int minLengthRange = 3;
	private int maxLengthRange = 99;
	private int minCounterRange = 1;
	private int maxCounterRange = 9999;
	private FilterSetting filterSetting;

	public Filter()
	{
		filterSetting = FilterSetting.KOLUMN_SOURCE;
	}

	public void setKolumn(FilterSetting filterSetting)
	{
		this.filterSetting = filterSetting;
	}

	public boolean isInLengthRange(Data data)
	{
		if (filterSetting.equals(FilterSetting.KOLUMN_SOURCE))
		{
			return minLengthRange <= data.getSourceWordLength() && data.getSourceWordLength() <= maxLengthRange;
		} else if (filterSetting.equals(FilterSetting.KOLUMN_TRANSLATE))
		{
			return minLengthRange <= data.getTranslateWordLength() && data.getTranslateWordLength() <= maxLengthRange;
		} else
		{
			return false;
		}
	}

	public boolean isInCounterRange(Data data)
	{
		return minCounterRange <= data.getCounter() && data.getCounter() <= maxCounterRange;
	}

	public boolean isInFilterRange(Data data)
	{
		return (isInCounterRange(data) && isInLengthRange(data));
	}

	public void setMinLengthRange(int minRange)
	{
		this.minLengthRange = minRange;
	}

	public void setMaxLengthRange(int maxRange)
	{
		this.maxLengthRange = maxRange;
	}

	public void setMinCounterRange(int minRange)
	{
		this.minCounterRange = minRange;
	}

	public void setMaxCounterRange(int maxRange)
	{
		this.maxCounterRange = maxRange;
	}

	public int getMinLengthRange()
	{
		return minLengthRange;
	}

	public int getMaxLengthRange()
	{
		return maxLengthRange;
	}

	public int getMinCounterRange()
	{
		return minCounterRange;
	}

	public int getMaxCounterRange()
	{
		return maxCounterRange;
	}
}
