package jac.fsd02.foodorder.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
@Controller
@RequestMapping("/toPhoto")
public class PhotoController {
    public static void uploadFile(byte[] file,String filePath,String fileName)throws Exception{
        File targetFile = new File(filePath);//给出路径进行操作
        if (targetFile.exists()){//判断是否存在
            targetFile.mkdirs();//不存在，自动创建
        }
        //字符输出流，向文件写入
        //定位文件
        FileOutputStream fileOutputStream = new FileOutputStream(filePath+"/"+fileName);
        fileOutputStream.write(file);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file, Model model){
        JSONObject jsonObject = new JSONObject();
        //文件名称            1970到现在的毫秒数           文件名称
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        //文件绝对路径
        String filePath = "src\\main\\resources\\static\\img\\";
        if (jsonObject.isEmpty()){//判断是否上传
            jsonObject.put("success","0");//失败输出0
            jsonObject.put("fileName","");
        }
        try {
            //调用定义的方法，进行操作
            uploadFile(file.getBytes(),filePath,fileName);
            jsonObject.put("success",1);
            jsonObject.put("fileName",fileName);
            jsonObject.put("xfileName","/pics/"+fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("xfileName",filePath+fileName);
        return jsonObject;
    }

}
