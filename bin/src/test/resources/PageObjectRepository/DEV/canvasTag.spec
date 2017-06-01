Page Title: CXP Check My Work Example

# Objects Definitions
==============================================
title							css		.title
title-bar               		css     .title-bar
slideBarForSelectModes	css	.select-mode
select_slideBarSelectTab	xpath	//span[text()='#{slidebarName}']
img_narrativeImageAll	xpath	(//img[@class='narrative-image'])[#{imgNo}]
blockColorForcanvas	css	.block-color.color-#{colorName}
canvasTag	css	.kineticjs-content>canvas
btn_pencilIcon	css	.ss-icon.ss-icon-gb.ss-icon-gb-pencil
btn_pointerIcon	css	.ss-icon.ss-icons-2ds.ss-icons-2ds-select_move
btn_ColorIcon	css	.ss-icon.ss-icons-color
btn_dotIcon	css	.ss-icon.ss-icons-2ds.ss-icons-2ds-new_dot
btn_fillIcon	css	.ss-icon.ss-icon-gb.ss-icon-gb-fill
btn_removeIcon	css	.ss-icon.ss-icons-texthighlighter.ss-icons-texthighlighter-remove
btn_undoIcon	css	.ss-icon.ss-icons-numberline.ss-icons-numberline-undo
btn_redoIcon	css	.ss-icon.ss-icons-numberline.ss-icons-numberline-redo
btn_clearAllbtm	css	.gb-button.clear-all
btn_ShowCorrectGraphbtm	css	.gb-button.show-correct
header_showCorrectGraph	xpath //span[text()='Show Correct Graph']
img_ViewCorrectGraph	css	.view-correct-block.view-correct-graph>.img-graph
img_ViewStudentGraph	css	.view-correct-block.view-student-graph>.img-graph
btn_tryAgain	css	.gb-button.try-again
btn_ReturnToGraph	css	.gb-button.return-graph
btn_FullExplanation	css	.gb-button.view-explanation
btn_submitButtonAtBottom	css	.ci-submit>input
hoverOncontainer	css	.container

btn_TextHorizontalIcon	xpath	//div[@class='text-palette']/span[contains(@class,'ss-icon-gb-text-horizontal')]
btn_TextVerticalIcon	xpath	//div[@class='text-palette']/span[contains(@class,'ss-icon-gb-text-vertical')]
btn_TextIcon	css	.ss-tbar-btn.btn-mode-text.tooltip.tooltipstered>span
input_activeTextOnCanvas	css	.active-text
toolTipContantOfCanvas	css	.tooltipster-content
div_infoBlockOnFullExplanation	css	.info-block
txt_infoBlock	xpath	//div[@id='explanation']/div[contains(text(),'#{text}')]
explanationBlock	css	#explanation
header_tryAgain	css	.modal-window>.header
txt_tryAgainTextInfo	css	.text-info
btn_tryAgainReset	css	.gb-button.reset-button
btn_tryAgainCancel	css	.gb-button.cancel-button
==============================================
    
@ GraphBuilder_Text
--------------------------------
canvasTag:
    image: file imgs/canvas_text.png, error 6.5%, tolerance 30
    width: 500 to 540px
    height: 390 to 430px

@ canvas_Dot
------------------------------
canvasTag:
    image: file imgs/canvasTag_3Dot.png, error 7.5%, tolerance 20

    
@ canvas_Fill_Orange
------------------------------
canvasTag:
    image: file imgs/canvasFillOrange.png, error 6.5%, tolerance 20
    width: 500 to 540px
    height: 390 to 430px
    
@ canvas_Tag
------------------------------
canvasTag:
    image: file imgs/canvasTag.png, error 10.5%, tolerance 20
    width: 500 to 540px
    height: 390 to 430px
    
@ canvasOrangeGreenDot
------------------------------
canvasTag:
    image: file imgs/canvasOrangeDot.png, error 6.5%, tolerance 20
    width: 500 to 540px
    height: 390 to 430px

@ canvasDotGreen
------------------------------
canvasTag:
    image: file imgs/canvasDotGreen.png, error 4.5%, tolerance 20
    width: 500 to 540px
    height: 390 to 430px
    
@ canvasStudentGraph
------------------------------
img_ViewStudentGraph:
    image: file imgs/img_ViewStudentGraph.png, error 4.5%, tolerance 20


@ canvasCorrectGraph
------------------------------
img_ViewCorrectGraph:
    image: file imgs/img_ViewCorrectGraph.png, error 4.5%, tolerance 20
    
@ all
------------------------------
activity-content-frame:
    component frame: cxpworkframe.spec
    
