package fun.android.notepad.Fun;

import android.app.Activity;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import java.util.List;


public class 权限申请 {
    public 权限申请(Activity activity){
        XXPermissions.with(activity)
                .permission(Permission.READ_MEDIA_IMAGES)
                .permission(Permission.READ_MEDIA_VIDEO)
                .permission(Permission.READ_MEDIA_AUDIO)
                .permission(Permission.MANAGE_EXTERNAL_STORAGE)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (!all) {
                            Fun.mess("获取部分权限成功，但部分权限未正常授予");
                        }
                        //Fun.mess(activity, "获取读写权限成功");
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            Fun.mess( "被永久拒绝授权，请手动授予读写权限");
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(activity, permissions);
                        } else {
                            Fun.mess("获取读写权限失败");
                        }
                    }
                });
    }


}