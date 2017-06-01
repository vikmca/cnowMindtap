Page Title: CXP Check My Work Example

# Objects Definitions
==============================================
title							css		.title
title-bar               		css     .title-bar
slideBarForSelectModes	css	.select-mode

select_slideBarSelectTab	xpath	.//*[text()='#{slidebarName}']
canvasTag	css	.kineticjs-content>canvas
btn_clearAllbtm	css	.gb-button.clear-all
btn_showCorrectButton	css	.gb-button.show-correct
hoverOncontainer	css	.container-crop
==============================================

@ all
--------------------------------
title:
    near:   title-bar 0px top
    text is: Activity Service Test Page
    width: 1000 to 1400px
    height: 10 to 20px
    
completed-final-grading_button:
    text is: Completed (Final Grading)
    width: 200 to 250px
    height: 10 to 35px
    
show-api-info_button:
    text is: Show API Info
    width: 100 to 130px
    height: 10 to 35px


@ all
------------------------------
activity-content-frame:
    component frame: cxpworkframe.spec
    
