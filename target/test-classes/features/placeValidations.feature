Feature: validating place API's

@AddPlace
Scenario Outline: verify if place is being successfully added using addplace API
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "AddplaceAPI" with "POST" HTTP request
	Then Verify the response cose is 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
	
Examples:
	|name     | language   |  address  |
	|Dinesh   | English    |  Banglore |
	#|Dinesh reddy   | Marathi   |  Chennai |
	
@Deleplace
Scenario: validating Delete Functionality
	Given Delete place payload
	When user calls "DeleteplaceAPI" with "POST" HTTP request
	Then Verify the response cose is 200
	And "status" in response body is "OK"