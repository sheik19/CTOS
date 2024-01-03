Author: "Sheik"
@Sprint_1_Features
Feature: To find the risk_segmentmentation of the user user request
  

  @sprint_1
  Scenario Outline: To check the risk of user when reg_profiles
    Given Connect the DFB system
    When passing the <User_enquiry_Request> to the DFP system
    Then check the status code 200 and check <Risk> of the given user
    
    Examples: 
      | name  | User_enquiry_Request | Risk    |
      | TC1   | risk_segmentation_1  | High    |
      | TC2   | risk_segmentation_2  | Low     |
    

  