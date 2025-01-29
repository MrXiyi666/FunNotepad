package fun.android.notepad.Fun;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import fun.android.notepad.App;

public class FunFile {
    public static boolean 写入文件(String path, String data){
        try {
            FileOutputStream fos = new FileOutputStream(App.file_path + path, false);
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            oStreamWriter.write(data);
            oStreamWriter.close();
            fos.close();
            Fun.mess( "成功啦", 500);
            return true;
        } catch (Exception e) {
            Log.w("写入文件", e);
            return false;
        }
    }

    public static String 读取文件(String path){
        StringBuilder sb = new StringBuilder();
        File urlFile = new File(App.file_path + path);
        if(!urlFile.exists()){
            return sb +"";
        }
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            isr.close();
        } catch (Exception e) {
            Log.w("读取文件", e);
        }
        return  sb+"";
    }
}
