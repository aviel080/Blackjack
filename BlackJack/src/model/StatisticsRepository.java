package model;

import java.util.Set;

public class StatisticsRepository {
	private static StatisticsRepository statisticsRepository;
	private FileManager<Statistic> fileManager;
	private Set<Statistic> Statistics;
	@SuppressWarnings("unchecked")
	private StatisticsRepository()
	{
		fileManager = new FileManager<Statistic>("statistics.dat");
		try {
		Statistics = (Set<Statistic>) fileManager.read();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	public static StatisticsRepository BuildStatisticsRepository()
	{	
		if (statisticsRepository == null)
			statisticsRepository = new StatisticsRepository();
		return statisticsRepository;
	}
	public void Update(Statistic Statistic)
	{
		try{
		Statistics.remove(Statistic);//old
		Statistics.add(Statistic);//new
		fileManager.write(Statistics);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	public Set<Statistic> getStatistics()
	{
		return Statistics;
	}
}
