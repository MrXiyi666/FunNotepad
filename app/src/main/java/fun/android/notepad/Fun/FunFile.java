package fun.android.notepad.Fun;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import fun.android.notepad.App;

public class FunFile {
    public static boolean 写入文件(String path, String data){
        try {
            FileOutputStream fos = new FileOutputStream(App.app_path + path, false);
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(fos, App.encode);
            oStreamWriter.write(data);
            oStreamWriter.close();
            fos.close();
            return true;
        } catch (Exception e) {
            Log.w("写入文件", e);
            return false;
        }
    }

    public static String 读取文件(String path){
        StringBuilder sb = new StringBuilder();
        File urlFile = new File(path);
        if(!urlFile.exists()){
            return sb.toString();
        }
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile), App.encode);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            br.close();
            isr.close();
        } catch (Exception e) {
            Log.w("读取文件", e);
        }

        return sb.toString();
    }


    public static boolean 创建文件夹(String path){
        return new File(path).mkdirs();
    }

    public static boolean 删除文件(String path){
        return new File(path).delete();
    }

    public static boolean 删除文件夹(File folder) {
        if (folder == null || !folder.exists()) {
            return false;
        }
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    // 递归删除子文件夹中的内容
                    if (file.isDirectory()) {
                        删除文件夹(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        // 删除当前文件夹
        return folder.delete();
    }

    public static boolean 是否存在(String path){
        return new File(path).exists();
    }

    public static List<String> 遍历文件夹(String path){
        File file = new File(path);
        File[] fs = file.listFiles();
        List<String> list = new ArrayList<>();
        if(fs == null){
            return list;
        }
        for(int i=fs.length-1; i>=0; i--){
            list.add(fs[i].getName());
        }
        return list;
    }
}
