package softtrack.apps.receipts;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class TransferActivity extends AppCompatActivity {

    public ImageView activityTransferContainerHeaderFlashImg;
    public boolean isFlashEnabled = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        initialize();

    }

    public void initialize() {
        activityTransferContainerHeaderFlashImg = findViewById(R.id.activity_transfer_container_header_flash_img);
        activityTransferContainerHeaderFlashImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ActivityCompat.requestPermissions(TransferActivity.this, new String[]{ Manifest.permission.FLASHLIGHT }, PackageManager.PERMISSION_GRANTED);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                    String cameraId = null;
                    try {
                        isFlashEnabled = !isFlashEnabled;
                        cameraId = camManager.getCameraIdList()[0];
                        camManager.setTorchMode(cameraId, isFlashEnabled);   //Turn ON
                    } catch (CameraAccessException e) {
                        Log.d("debug" , "ошибка с фонариком");
                    }
                }
            }
        });
    }

}
