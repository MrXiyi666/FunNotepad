package fun.android.notepad.Fun;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.icu.util.Output;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import fun.android.notepad.App;

public class FunFile {
    public static boolean 写入文件(String path, String data){
        try {
            FileOutputStream fos = new FileOutputStream(App.file_path + path, false);
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(fos, App.encode);
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

    public static boolean 写入文件(Uri uri, String data){
        try (OutputStream outputStream = App.activity.getContentResolver().openOutputStream(uri, "wt");
             OutputStreamWriter writer = new OutputStreamWriter(outputStream, App.encode)){
            writer.write(data);
            writer.flush();
            writer.close();
            assert outputStream != null;
            outputStream.flush();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean 保存文件(){
        if(App.uri!=null){
            if(写入文件(App.uri, App.Txt_Data)){
                Fun.mess("保存成功");
            }else{
                Fun.mess("保存失败");
            }
        }else if(App.FileName != null){
            if(写入文件(App.FileName, App.Txt_Data)){
                Fun.mess("保存成功");
            }else{
                Fun.mess("保存失败");
            }
        }else{
            Fun.mess("无法保存文件");
        }
        return false;
    }
    public static String 读取文件(String path){
        StringBuilder sb = new StringBuilder();
        File urlFile = new File(App.file_path + path);
        if(!urlFile.exists()){
            return sb +"";
        }
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(urlFile), App.encode);
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

    public static String ReadURI(Uri uri) {
        try {
            InputStream inputStream = App.activity.getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, App.encode));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            assert inputStream != null;
            inputStream.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String GetUriName(Uri uri) {
        String fileName = null;
        String[] projection = {MediaStore.Files.FileColumns.DISPLAY_NAME};
        Cursor cursor = App.activity.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME);
                fileName = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        return fileName;
    }
}
