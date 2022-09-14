Feature: Validate the news from guardian website is accurate
	Accuracy for guardian news article is tested by verifying the news accuracy
	from trusted news sources using semantic similarity with
	Jaro WinklerDistance
	
Background:
Given User is on the Guardian Homepage
And Accept Cookies From Guardian


@TestGuardianFromGoogle
Scenario Outline: Verify that live news reported by guardian is accurate from Google Search

When Fetch the "<ArticleNumber>" from homepage	
And Launch google seach page
And Enter article retrieved from guardian in search field
And Click google news tab on Search Results page
Then Verify guardian news article is accurate from google search results
Examples:
|ArticleNumber|
|1|
|2|


@TestGuardianFromBing
Scenario Outline: Verify that live news reported by guardian is accurate from Bing Search

When Fetch the "<ArticleNumber>" from homepage	
And Launch bing seach page
And Enter article retrieved from guardian in bing search field
And Click bing news tab on Search Results page
Then Verify guardian news article is accurate from bing search results
Examples:
|ArticleNumber|
|1|
|2|


