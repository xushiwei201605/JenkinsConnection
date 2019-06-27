package zipDocument;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.testng.annotations.Test;

public class BuildZip {
	public  BuildZip() {
		//鏋勯�犲嚱鏁�
	}
    public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){  
        boolean flag = false;  
        File sourceFile = new File(sourceFilePath);  
        FileInputStream fis = null;  
        BufferedInputStream bis = null;  
        FileOutputStream fos = null;  
        ZipOutputStream zos = null;  

        if(sourceFile.exists() == false){  
            System.out.println("寰呭帇缂╃殑鏂囦欢鐩綍锛�"+sourceFilePath+"涓嶅瓨鍦�.");  
            sourceFile.mkdir(); // 鏂板缓鐩綍
        }  
        try {  
            File zipFile = new File(zipFilePath + "\\" + fileName +".zip");  
            if(zipFile.exists()){  
                System.out.println(zipFilePath + "鐩綍涓嬪瓨鍦ㄥ悕瀛椾负:" + fileName +".zip" +"鎵撳寘鏂囦欢.");  
            }else{  
                File[] sourceFiles = sourceFile.listFiles();  
                if(null == sourceFiles || sourceFiles.length<1){  
                    System.out.println("寰呭帇缂╃殑鏂囦欢鐩綍锛�" + sourceFilePath + "閲岄潰涓嶅瓨鍦ㄦ枃浠讹紝鏃犻渶鍘嬬缉.");  
                }else{  
                    fos = new FileOutputStream(zipFile);  
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));  
                    byte[] bufs = new byte[1024*10];  
                    for(int i=0;i<sourceFiles.length;i++){  
                        //鍒涘缓ZIP瀹炰綋锛屽苟娣诲姞杩涘帇缂╁寘  
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());  
                        zos.putNextEntry(zipEntry);  
                        //璇诲彇寰呭帇缂╃殑鏂囦欢骞跺啓杩涘帇缂╁寘閲�  
                        fis = new FileInputStream(sourceFiles[i]);  
                        bis = new BufferedInputStream(fis, 1024*10);  
                        int read = 0;  
                        while((read=bis.read(bufs, 0, 1024*10)) != -1){  
                            zos.write(bufs,0,read);  
                        }  
                    }  
                    flag = true;  
                }  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        } finally{  
            //鍏抽棴娴�  
            try {  
                if(null != bis) bis.close();  
                if(null != zos) zos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            }  
        }  
    return flag;  
    }
    

}
