package pages.actions;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public class CommonPage {

	protected static String articleRetrieved;
	
	protected ArrayList<String> trustedNewsSources = new ArrayList<>(
			Arrays.asList("ABC News", "The Independent", "Daily Mail", "BBC", "The Irish Times", "Al Jazeera", "NPR",
					"Firstpost", "Sky Sports", "New York Post", "RTE", "NDTV Sports", "Manchester Evening News",
					"Daily Express", "WION", "The New York Times", "CNN", "NBC News","Metro","Daily Mail","MSN","Financial Times"));


	/**
	 * 
	 * Using JaroWinklerDistance to find the semantic similarity between the
	 * retrieved article from guardian and articles from other news sources from
	 * google search [ This is to find the accuracy of article from guardian by
	 * comparing with other news sources to find the semantic similarity value ]
	 * 
	 * @param article1
	 * @param article2
	 * @return
	 */

	public static double findSimilarity(String article1, String article2) {
		if (article1 == null && article2 == null) {
			return 1.0;
		}
		if (article1 == null || article2 == null) {
			return 0.0;
		}
		return StringUtils.getJaroWinklerDistance(article1, article2);
	}

}
