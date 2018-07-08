@LogIn
Feature: Twitter LogIn 

Background: Home page is launched
Given User navigates to Twitter home page

Scenario: User posts a new tweet

Given User logs in with "username" and "password"
When User posts a new "tweet"
Then a new message should be posted
And after all the post should be deleted
