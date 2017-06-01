Page Title: Stereo Molecular Grading FITB

# Objects Definitions
==============================================
stereo-canvas                       css      canvas
stereo-molecular-pt-area			css		.ci-chem-sketch-area
clear-button                        css     .ui-button [title="Clear"]  
select-drop-down                    css      select
feedback-title                      css     .ci-feedback-title
feedback-value                      css     .ci-show-answer.ci-feedback
submit-button                       css     .ci-submit>input

@ stereomolecular
--------------------------------
stereo-canvas:
    image: file imgs/canvas.png, error 6.5%, tolerance 30
    width:360 to 375px
    height: 290 to 310px
    
stereo-molecular-pt-area:
    width: 350 to 390px
    height: 300 to 350px
        
clear-button:
    image: file imgs/clear-button.png, error 2.5%, tolerance 30
    width:10 to 15px
    height: 10 to 15px
    
select-drop-down:
    width: 130 to 150px
    height: 15 to 25px
    
stereo-molecular-pt-area:
    image: file imgs/stereomoleculargrading.png, error 6.5%, tolerance 50
    
@ submitItemAndCorrectFeedback
------------------------------
stereo-canvas:
    image: file imgs/canvas.png, error 6.5%, tolerance 50
    width:360 to 375px
    height: 290 to 310px
    
stereo-molecular-pt-area:
    image: file imgs/stereomoleculargrading.png, error 6.5%, tolerance 50
    
clear-button:
    width:10 to 15px
    height: 10 to 15px
    
select-drop-down:
    width: 130 to 150px
    height: 15 to 25px
    
submit-button:
    width: 40 to 70px
    height: 10 to 30px

feedback-title:
    text is: Feedback
    width: 1290 to 1330px
    height: 10 to 30px
    
feedback-value:
    text is: Correct
    width: 1280 to 1310px
    height: 10 to 30px

@ submitItemAndIncorrectFeedback
------------------------------

stereo-canvas:
    image: file imgs/correct-canvas.png, error 0%
    width:360 to 375px
    height: 290 to 310px

stereo-molecular-pt-area:
    image: file imgs/stereo-molecular-pt-area.png, error 1%
        
clear-button:
    image: file imgs/clear-button.png, error 0%
    width:10 to 15px
    height: 10 to 15px
    
submit-button:
    width: 40 to 70px
    height: 10 to 30px
    
select-drop-down:
    width: 130 to 150px
    height: 15 to 25px

feedback-title:
    text is: Feedback
    width: 1290 to 1330px
    height: 10 to 30px
    
feedback-value:
    text is: Incorrect
    width: 1280 to 1310px
    height: 10 to 30px