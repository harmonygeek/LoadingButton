# LoadingButton

LoadingButton is a custom view that shows and hides a ProgressBar with text. 

![LoadingButton](https://github.com/snadjafi/LoadingButton/blob/master/images/screenshot.png)

## Sample Usage

```xml
   <com.snad.loadingbutton.LoadingButton
        ohos:id="$+id:first"
        ohos:height="match_content"
        ohos:width="match_parent"
        ohos:background_element="#BE6E46"
        app:pbLoadingText="$string:loading"
        app:pbProgressColor="#F9D8FB"
        app:pbText="$string:click_me"
        app:pbTextColor="#8A4FFF"
        app:pbTextSize="26fp"
        />
```

```java
   @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        
        ComponentContainer rootLayout = (ComponentContainer) LayoutScatter.getInstance(this)
                .parse(ResourceTable.Layout_ability_main, null, false);

        rootLayout.findComponentById(ResourceTable.Id_first).setClickedListener(this);
        rootLayout.findComponentById(ResourceTable.Id_second).setClickedListener(this);

        LoadingButton button = new LoadingButton(this);
        button.setText("Press");
        button.setLoadingText("wait...");

        ShapeElement shapeElement = new ShapeElement();
        shapeElement.setRgbColor(new RgbColor(0xFF, 0, 0));
        shapeElement.setShape(ShapeElement.RECTANGLE);
        button.setBackground( shapeElement);
        button.setClickedListener(this);

        ((ComponentContainer)rootLayout.findComponentById(ResourceTable.Id_root)).addComponent( button);

        super.setUIContent( rootLayout);
    }
    	
    @Override
    public void onClick(Component component) {

        ((LoadingButton) component).showLoading();
        TaskDispatcher dispatcher = context.getUITaskDispatcher();
        Revocable revocable = dispatcher.delayDispatch(new Runnable() {
            @Override
            public void run() {
                ((LoadingButton) component).showButtonText();
            }
        }, 2000);

    }
```

##License

    Copyright 2015 Shervin Najafi
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
