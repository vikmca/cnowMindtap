#select course
=======================================

lnkCourseName						xpath				//span[contains(text(),'#{text}')]/../../li/a[text()='Open']
lnk_takeMeToMyCourse				xpath				//a[contains(text(),"Take me to my course")]
btn_enterCnowv2Anyway				css					button[title='Enter CengageNOWv2 Anyway']
txt_chapterNum						xpath				//div[@class="stalker" and not(contains(@style,"hi"))]//table[@id="overviewTable"]//td[text()='#{chapterNum}']
input_takeResumeRetakeBtn			xpath				//div[@class='stalker' and not(contains(@style,'hi'))]//table[@id='overviewTable']//td[text()='#{chapterNum}']/..//input[@value='%{action}']
heading_assignment					css					#readyMessage>h2
btn_takeResumeRetakeAssignmentNow	css					button[value='#{text} Assignment Now']
title_assignment					css					div.assignmentTitle
btn_resumeAssignmentNo				css					input[value='No']
btn_checkMyWork						css					div.checkMyWork>button
txt_questionFeedback				css					div.ci-show-answer
txt_itemsCount						css					span.itemsCount
itemCount							css					ol.itemSelectorContainer>li
indicator_itemIncorrect				css					ol.itemSelectorContainer>li>span.incorrect	
btn_submitAssignmentForGrading		css					button[title='Submit Assignment for Grading']	
txt_assignmentFinishedHeading		css					div.pageContentBody>h2
btn_submitForGrading				css					button[title='Submit for Grading']
txt_warningPopup					xpath				//span[contains(text(),'WARNING')]
txt_questionList					xpath				(//ol[contains(@class,'itemSelectorContainer')]/li/span[@class='text'])[#{text}]
option_answer						css					input[type="radio"][value='#{text}']
rejoinder_correctAnswer				css					.ci-correct
rejoinder_incorrectAnswer			css					.ci-incorrect
choice_options						css					input[type="radio"]
seed_choice							xpath				//div[contains(@options,"seed")]
indicator_incorrectAnswer			xpath				(//ol[contains(@class,'itemSelectorContainer')]/li/span[@class='text'])[#{text}]/../span[contains(@class,'icon incorrect')]
indicator_correctAnswer				xpath				(//ol[contains(@class,'itemSelectorContainer')]/li/span[@class='text'])[#{text}]/../span[contains(@class,'icon correct')]
popup_correctAnswer					xpath				//div[@id='popup' and contains(@class,'ci-correct')]
popup_incorrectAnswer				xpath				//div[@id='popup' and contains(@class,'ci-incorrect')]
btn_grades							css					a[title="Grades"]	
btn_assignments						css					a[title="Assignments"]
options_choice						css					.ci-choice