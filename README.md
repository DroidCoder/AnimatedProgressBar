
![alt Analytics](https://ga-beacon.appspot.com/UA-63373429-3/AnimatedProgressBar/readme)

AnimatedProgressBar
===================

**Android library for Animated progress bars.**  
*Includes sample application.*

Main Features
-------------
1. **Simple to use**
    + *Just 4 custom attributes can be passed through code or layout.xml*
    + *It can be customized as every common progessbar* 
2. **Resource friendly - even with multiple instances**, 
    + *No matter how many instances are animating concurrently, there is low usage on device's resources (memory & cpu)* 
3. **Compact library**
    + *aar file less than 65kb*


![alt screenshot](https://raw.githubusercontent.com/DroidCoder/AnimatedProgressBar/master/Docs%20and%20pics/screenshot_1.png)

Attributes' usage
-----------------


| Class | Attribute | type | default | behavior |
| :----: | :----: | :----: | :----: | :----: |
| progressbar | max | int | 100 | control of speed |
| animatedprogressbar | animate_when_first_show | boolean | true | animation starts on attached to window |
| animatedprogressbar | animation_loop | boolean | false | when progress reach max reset to 0 and animate again |
| animatedprogressbar | animate_secondary_process | boolean | true | secondary process drawable is also animated |
| animatedprogressbar | animate_secondary_process_step | int | 1 | when progess = secondary add step to secondary |
| progressbar | progessDrawable | resource | device style | custom drawable to modify style of animatedprogressbar |




License
-------
Copyright (c) 2014, [Athanasios Karpouzis] (http://droid-coder.com) All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.


THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
