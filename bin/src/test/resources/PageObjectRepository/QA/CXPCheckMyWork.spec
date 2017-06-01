Page Title: CXP Check My Work Example

# Objects Definitions
==============================================
title							css		.title
title-bar               		css     .title-bar
show-scores_button      		css     .assignment-button.show-scores
review_button                  	css     .assignment-button.review-button
completed_button               	css     .assignment-button.review-completed-button
completed-final-grading_button 	css		.assignment-button.review-completed-final-grading-button
show-api-info_button			css     .assignment-button.show-input-and-output
new-test_button					css     .assignment-button.exit-button
cxp-work-subit                  css     .submit-some-cxp-work
activity-content-frame          css     iframe#easyXDM_activityService_target_provider  
==============================================

@ all
--------------------------------
title:
    near:   title-bar 0px top
    text is: Activity Service Test Page
    width: 1000 to 1400px
    height: 10 to 20px
    
title-bar:
    near:   title 0px bottom
    width: 1000 to 1400px
    height: 80 to 100px
    
show-scores_button:
    text is: Show Scores
    width: 105 to 125px
    height: 20 to 35px
	
review_button:
    text is: Review
    width: 50 to 100px
    height: 10 to 35px
    
completed_button:
    text is: Completed
    width: 50 to 110px
    height: 10 to 35px
    
completed-final-grading_button:
    text is: Completed (Final Grading)
    width: 200 to 250px
    height: 10 to 35px
    
show-api-info_button:
    text is: Show API Info
    width: 100 to 130px
    height: 10 to 35px
    
new-test_button:
    text is: New Test
    width: 75 to 100px
    height: 10 to 35px

@ all
------------------------------
activity-content-frame:
    component frame: cxpworkframe.spec
    