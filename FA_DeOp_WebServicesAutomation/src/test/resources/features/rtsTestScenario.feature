#This feature file contains few test scenarios to verify RealTimeScore Mock API service
#Author: Nandhini
Feature: To verify rtsAPI
  @rtsAPI_Test
  Scenario Outline: Validate rtsAPI
    Given A RestXML webservice to get real time score for a claim
    And Set request xml RequestType attribute value <RequestType> and ClaimNumber attribute value <ClaimNumber>
    When request is sent and response received
    Then the response body contains 201 status.
    Examples:
      | RequestType   | ClaimNumber                                                         |
      | FULL | 0123456768