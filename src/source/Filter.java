package source;

public class Filter
{
	private int minLengthRange = 1, maxLengthRange = 99;
	private int minCounterRange = 1, maxCounterRange = 9999;
	private int column = 1;

	public boolean isInFilterRange(Data data)
	{
		if (data.isInLengthRange(getMinLengthRange(), getMaxLengthRange()) && data.isInCounterRange(getMinCounterRange(), getMaxCounterRange()))
		{
			return true;
		} else
		{
			return false;
		}
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

	public int getColumn()
	{
		return column;
	}

	public void setColumn(int column)
	{
		this.column = column;
	}
}
