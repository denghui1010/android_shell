package com.ldh.shell;

import com.example.printdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class PrintActivity extends Activity implements OutputListener {
  private EditText et_input;
  private TextView tv_output;
  private ShellUtil shellUtil;
  private ScrollView sv_output;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shell);
    initView();
    shellUtil = new ShellUtil();
    shellUtil.newShell();
  }

  private void initView() {
    et_input = (EditText) findViewById(R.id.et_input);
    tv_output = (TextView) findViewById(R.id.tv_output);
    sv_output = (ScrollView) findViewById(R.id.sv_output);
    sv_output.setScrollbarFadingEnabled(false);
    sv_output.setScrollContainer(true);
    tv_output.setText(Constant.DIRECTORY + "\n");
  }

  public void execute(View view) {
    String string = et_input.getText().toString();
    shellUtil.setOutputListener(this);
    shellUtil.execute(string + " && pwd && echo");
    et_input.setText("");
  }

  @Override
  public void onOneLineOutput(String string) {
    tv_output.append(string);
    sv_output.post(new Runnable() {
      @Override
      public void run() {
        sv_output.smoothScrollTo(0, tv_output.getHeight());
      }
    });
  }

  @Override
  protected void onDestroy() {
    shellUtil.closeShell();
    super.onDestroy();
  }

}
