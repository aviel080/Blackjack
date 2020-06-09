package controller;

import model.Statistic;
import model.User;

public class StatisticController {
	private static StatisticController statisticController = null;
	private User user;
	private StatisticController() {
	}
	public static StatisticController BuildStatisticController(User user)
	{	
		if (statisticController == null)
			statisticController = new StatisticController();
		statisticController.user = user;
		return statisticController;
	}
	public boolean statisticsController(String choose)
	{
		if(choose.equals("clear"))
		{
			Statistic.getUserStatistic(user.getuserId()).clearStatistics();
			return true;
		}
		return false;
	}
}
