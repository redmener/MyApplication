package procressbar.zk.com.mapleadtest.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import procressbar.zk.com.mapleadtest.R;

public class MainActivity extends AppCompatActivity {


    private Button mBtnSelectMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
         mBtnSelectMap = (Button) findViewById(R.id.btn_selectMap);
        mBtnSelectMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        MapSelectDialog dialog = new MapSelectDialog(this,R.style.testDialog);
        dialog.show();

    }


}
