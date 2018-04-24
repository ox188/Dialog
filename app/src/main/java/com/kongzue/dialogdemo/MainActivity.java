package com.kongzue.dialogdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.kongzue.dialog.listener.DialogLifeCycleListener;
import com.kongzue.dialog.listener.InputDialogOkButtonClickListener;
import com.kongzue.dialog.v2.DialogSettings;
import com.kongzue.dialog.ProgressbarDialog;
import com.kongzue.dialog.v2.InputDialog;
import com.kongzue.dialog.v2.MessageDialog;
import com.kongzue.dialog.v2.SelectDialog;
import com.kongzue.dialog.v2.TipDialog;
import com.kongzue.dialog.v2.WaitDialog;

import static com.kongzue.dialog.v2.DialogSettings.THEME_DARK;
import static com.kongzue.dialog.v2.DialogSettings.THEME_LIGHT;
import static com.kongzue.dialog.v2.DialogSettings.TYPE_IOS;
import static com.kongzue.dialog.v2.DialogSettings.TYPE_KONGZUE;
import static com.kongzue.dialog.v2.DialogSettings.TYPE_MATERIAL;

public class MainActivity extends AppCompatActivity {

    private MainActivity me;

    private int colorId;        //主题颜色

    private RadioGroup grp;
    private RadioButton rdoMaterial;
    private RadioButton rdoKongzue;
    private RadioButton rdoIos;
    private RadioGroup grpDialogTheme;
    private RadioButton rdoDialogThemeLight;
    private RadioButton rdoDialogThemeDark;
    private Button btnMsg;
    private Button btnInput;
    private Button btnSelect;
    private RadioGroup grpTipTheme;
    private RadioButton rdoTipThemeLight;
    private RadioButton rdoTipThemeDark;
    private Button btnPsg;
    private Button btnTipOk;
    private Button btnTipWarning;
    private Button btnTipError;

    private void initViews() {
        grp = (RadioGroup) findViewById(R.id.grp);
        rdoMaterial = (RadioButton) findViewById(R.id.rdo_material);
        rdoKongzue = (RadioButton) findViewById(R.id.rdo_kongzue);
        rdoIos = (RadioButton) findViewById(R.id.rdo_ios);
        grpDialogTheme = (RadioGroup) findViewById(R.id.grp_dialog_theme);
        rdoDialogThemeLight = (RadioButton) findViewById(R.id.rdo_dialog_theme_light);
        rdoDialogThemeDark = (RadioButton) findViewById(R.id.rdo_dialog_theme_dark);
        btnMsg = (Button) findViewById(R.id.btn_msg);
        btnInput = (Button) findViewById(R.id.btn_input);
        btnSelect = (Button) findViewById(R.id.btn_select);
        grpTipTheme = (RadioGroup) findViewById(R.id.grp_tip_theme);
        rdoTipThemeLight = (RadioButton) findViewById(R.id.rdo_tip_theme_light);
        rdoTipThemeDark = (RadioButton) findViewById(R.id.rdo_tip_theme_dark);
        btnPsg = (Button) findViewById(R.id.btn_psg);
        btnTipOk = (Button) findViewById(R.id.btn_tip_ok);
        btnTipWarning = (Button) findViewById(R.id.btn_tip_warning);
        btnTipError = (Button) findViewById(R.id.btn_tip_error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        me = this;

        initViews();
        initDatas();
        initEvent();
    }

    private void initDatas() {
        DialogSettings.type = TYPE_MATERIAL;
        DialogSettings.tip_theme = THEME_DARK;
        DialogSettings.dialog_theme = THEME_LIGHT;

        DialogSettings.dialog_title_text_size = -1;     //设置对话框标题文字大小，<=0不启用
        DialogSettings.dialog_message_text_size = -1;   //设置对话框内容文字大小，<=0不启用
        DialogSettings.dialog_button_text_size = -1;    //设置对话框按钮文字大小，<=0不启用
        DialogSettings.tip_text_size = -1;              //设置提示框文字大小，<=0不启用
        DialogSettings.ios_normal_button_color = -1;    //设置iOS风格默认按钮文字颜色，=-1不启用

        MessageDialog.show(me, "欢迎", "欢迎使用Kongzue家的对话框，此案例提供常用的几种对话框样式。\n如有问题可以在https://github.com/kongzue/Dialog提交反馈");
    }

    private ProgressbarDialog progressbarDialog;

    private void initEvent() {

        grpDialogTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rdoDialogThemeDark.getId() == checkedId) {
                    DialogSettings.dialog_theme = THEME_DARK;
                }
                if (rdoDialogThemeLight.getId() == checkedId) {
                    DialogSettings.dialog_theme = THEME_LIGHT;
                }
            }
        });

        grpTipTheme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rdoTipThemeDark.getId() == checkedId) {
                    DialogSettings.tip_theme = THEME_DARK;
                }
                if (rdoTipThemeLight.getId() == checkedId) {
                    DialogSettings.tip_theme = THEME_LIGHT;
                }
            }
        });

        grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rdoMaterial.getId() == checkedId) {
                    DialogSettings.type = TYPE_MATERIAL;
                }
                if (rdoKongzue.getId() == checkedId) {
                    DialogSettings.type = TYPE_KONGZUE;
                }
                if (rdoIos.getId() == checkedId) {
                    DialogSettings.type = TYPE_IOS;
                }
            }
        });

        //输入对话框
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputDialog.show(me, "设置昵称", "设置一个好听的名字吧", new InputDialogOkButtonClickListener() {
                    @Override
                    public void onClick(Dialog dialog, String inputText) {
                        Toast.makeText(me, "您输入了：" + inputText, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //消息对话框
        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageDialog.show(me, "消息提示框", "用于提示一些消息", "知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });

        //选择对话框
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDialog.show(me, "提示", "请做出你的选择", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(me, "您点击了确定按钮", Toast.LENGTH_SHORT).show();
                    }
                }, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(me, "您点击了取消按钮", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //调用等待提示框
        btnPsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaitDialog.show(me, "载入中...").setCanCancel(true);
            }
        });

        //完成提示
        btnTipOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipDialog.show(me, "完成", TipDialog.SHOW_TIME_SHORT, TipDialog.TYPE_FINISH);
            }
        });

        //警告提示
        btnTipWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipDialog.show(me, "请输入密码", TipDialog.SHOW_TIME_SHORT, TipDialog.TYPE_WARNING);
            }
        });

        //错误提示
        btnTipError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipDialog.show(me, "禁止访问", TipDialog.SHOW_TIME_LONG, TipDialog.TYPE_ERROR);
            }
        });
    }

}
