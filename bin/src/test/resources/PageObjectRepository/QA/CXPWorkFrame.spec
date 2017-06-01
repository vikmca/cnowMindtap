Page Title: CXP Check My Work Example

# Objects Definitions
==============================================
cxp-work-submit                 css     .submit-some-cxp-work  
content-frame                   css     iframe
page-button						css		.pagebutton.pressed.readOnly
q4-doc                          css     .dock
window-viewport                 css     .window-viewport
page-buttons					css		.pagebutton
table-input-box                 css     .q4-table-entry-Journal-no_document_number tr:nth-child($) td:nth-child(%) input
ledger-accounts-link        classname    q4-table-entry-account-title 
ledger-input-box                css     .q4-table-entry tbody tr:nth-child($) td:nth-child(%) input
submit-q4-work				    css     .submit-some-q4-work
q4-feedback-header              css     .show-q4-item-div .ci-feedback-title
q4-feedback-response            css     .show-q4-item-div .ci-feedback-body .ci-show-answer
cxp-work                        css     table
cxp-work-select                 css     table tbody tr:nth-child($) td:nth-child(%) select
cxp-work-inputbox               css     table tbody tr:nth-child($) td:nth-child(%) input
cxp-work-subit                  css     .submit-some-cxp-work
q4-work-frame                   css     iframe  
==============================================

@ all
------------------------------
q4-work-frame:
    component frame: q4workframe.spec
    


@ submitWorkAndFeedback
------------------------------    
submit-q4-work:
    width: 90 to 125px
    height: 15 to 25px
    
q4-feedback-header:
    text is: Feedback
    width: 1270 to 1300px
    height: 10 to 30px
    
q4-feedback-response:
    text is: Partially correct
    width: 1260 to 1280px
    height: 10 to 30px