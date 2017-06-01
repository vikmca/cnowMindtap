Page Title: CXP Check My Work Example

# Objects Definitions
==============================================
txt_exerciseName	xpath	//div[@id='container-step']/h6[@class='theme-exercise']
txt_slideNarrativeText	xpath	//div[@id='slide-#{no}']/div[@class='narrative-text']
img_narrativeImage	xpath	//div[@id='slide-#{no}']/img[@class='narrative-image']
numberOfSlideBar	xpath	//a[@id='step-#{no}']
btn_activeStep	xpath	//a[contains(@class,'btn-step-active')]
btn_PrevSlider	css	.btn-slider.btn-prev
btn_NextSlider	css	.btn-slider.btn-next
img_narratImageOnStep	xpath	//div[@class='narrative-slide'][@style='display: block;']/img
img_narratImageOnGraph	xpath	//div[@id='container-graph']//img[@class='narrative-image']
img_narrtImageOnBuilder	xpath	//div[@id='container-builder']//img[@class='narrative-image']
==============================================

@ all
-------------------------------- 
show-api-info_button:
    text is: Show API Info
    width: 100 to 130px
    height: 10 to 35px


@ all
------------------------------
activity-content-frame:
    component frame: cxpworkframe.spec
    
