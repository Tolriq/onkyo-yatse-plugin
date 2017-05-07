/*
 * Copyright 2015 Tolriq / Genimee.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.danielhstahl.plugin.avreceiver.onkyo;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import 	android.text.Html;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tv.yatse.plugin.avreceiver.api.AVReceiverCustomCommandsAppCompatActivity;

public class CustomCommandsActivity extends AVReceiverCustomCommandsAppCompatActivity {
    @BindView(R.id.custom_command_title)
    TextView mViewTitle;
    @BindView(R.id.url_to_commands)
    TextView mViewUrl;
    @BindView(R.id.custom_command_param1)
    TextView mViewParam1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_commands);

        //TextView textView =(TextView)findViewById(R.id.url_to_commands);
        /**/
        ButterKnife.bind(this);
        if (isEditing()) {
            mViewTitle.setText(pluginCustomCommand.title());
            mViewParam1.setText(pluginCustomCommand.param1());
            mViewUrl.setClickable(true);
            mViewUrl.setMovementMethod(LinkMovementMethod.getInstance());
            //String text = "<a href='http://michael.elsdoerfer.name/onkyo/'>Get codes here.</a>";
            //mViewUrl.setText(Html.fromHtml(text));


            //mViewUrl.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @OnClick({R.id.btn_save, R.id.btn_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                cancelAndFinish();
                break;
            case R.id.btn_save:
                // Custom command source field must always equals to plugin uniqueId !!
                pluginCustomCommand.source(getString(R.string.plugin_unique_id));
                pluginCustomCommand.title(String.valueOf(mViewTitle.getText()));
                pluginCustomCommand.param1(String.valueOf(mViewParam1.getText()));
                saveAndFinish();
                break;
        }
    }

}
