package cn.no7player.controller;

import ch.qos.logback.core.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

@RestController
public class UploadController {

    //跳转到上传文件的页面
    @RequestMapping(value="/gouploadimg", method = RequestMethod.GET)
    public String goUploadImg() {
        //跳转到 templates 目录下的 uploadimg.html
        return "uploadimg";
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath+fileName));
        bos.write(file);
        bos.flush();
        bos.close();
    }

    /**
     * 文件上传具体实现方法;
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
//    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
    public String handleFileUpload(@RequestPart("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径； 大家自己扩散下思维，如果我们想把图片上传到
                 * d:/files大家是否能实现呢？ 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如： 1、文件路径； 2、文件名；
                 * 3、文件格式; 4、文件大小的限制;
                 */
                String contentType = file.getContentType();
                String fileName = file.getOriginalFilename();
                System.out.println("fileName--------------" + fileName);
                System.out.println("getContentType------------" + contentType);
//                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(
//                        new File( file.getOriginalFilename())));
//                out.write(file.getBytes());
//                out.flush();
//                out.close();
                file.transferTo(new File("D:\\resource\\upload\\" + fileName));
//                String filePath = "D:\\resource\\upload\\";
//                uploadFile(file.getBytes(), filePath, fileName);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "上传成功";

        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    /**
     * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
     *
     * @param request
     * @return
     */

    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String fileName = file.getOriginalFilename();
                    System.out.println("fileName--------------" + fileName);
                    String filePath = "D:\\resource\\upload\\";
//                    uploadFile(file.getBytes(), filePath, fileName);
                    file.transferTo(new File("D:\\resource\\upload\\" + fileName));
                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "+ e.getMessage();
                }
            } else {
                return "You failed to upload " + i + " because the file was empty.";
            }

        }
        return "upload successful";

    }
}
