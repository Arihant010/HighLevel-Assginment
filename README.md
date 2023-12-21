#Highlevel Assignment for Scheduling calendar

In this project I have tried to automate 2 testcases which are:
1.Successful scheduling of a calendar. I have automated the flow of scheduling meeting starting from loging in and copying the scheduling link from the group and then after the whole process I check for the successfull message
2.Checking that a meeting is assigned to a high priority member. I have made a group of 3 members with 1 having high priority.In this case I simply retrive the appointment owner of meeting created in first testcase and check if it is the high priority one.

A report will be generated with both the test results and if any one off them fails then a screenshot will be attached to it along with the error

Here is the project structure :

src/main/java: 1. abstract Components (common functions in all pages)
               2. Page Object classes (Page classes for every page included in the tests)
               3. resources (reprter class)
src/test/java: 1. sample input data for test cases in json format
               2. test components(base test and listeners)
               3. Tests
testSuits: xml file for running the test cases
reports: html report of test results 



