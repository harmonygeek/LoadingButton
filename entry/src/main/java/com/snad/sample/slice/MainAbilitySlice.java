/*
 * Copyright (c) 2020 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.snad.sample.slice;

import com.snad.loadingbutton.LoadingButton;
import com.snad.sample.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.app.Context;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.Revocable;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener{

    private ComponentContainer rootLayout;
    private LoadingButton loadingButton;
    private Context context;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);

        this.context = getApplicationContext();
        rootLayout = (ComponentContainer) LayoutScatter.getInstance(this)
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

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
