package com.tianma.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

/**
 * Created by zuowenxia on 2017/4/18.
 */
@Controller
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @GetMapping("/")
    public String getIndex(ModelAndView modelAndView) {
        return "home";
    }

    @GetMapping("/index.html")
    public String getHome(ModelAndView modelAndView) {
        return "screen/index";
    }

    @RequestMapping("/404")
    public ModelAndView getNotFoundPage() {
        logger.debug("Getting Not Found page");
        return new ModelAndView("/screen/common/404");
    }

    @RequestMapping("/505")
    public ModelAndView get505ErrorPage() {
        logger.debug("Getting Error page");
        return new ModelAndView("/screen/common/505");
    }

    @RequestMapping(value = "/api/upload.html", method = RequestMethod.POST)
    public @ResponseBody
    String upload(MultipartFile file) {
        if (file != null) {
            try {
                FileUtils.writeByteArrayToFile(new File("/tmp/" + file.getOriginalFilename()), file.getBytes());
                return "文件上传成功!";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "文件上传失败!";
    }

    @RequestMapping(value = "/api/uploads.html", method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile[] files) {
        if (files != null) {
            try {
                for(MultipartFile file : files) {
                    FileUtils.writeByteArrayToFile(new File("/tmp/" + file.getOriginalFilename()), file.getBytes());
                }
                return "文件上传成功!";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "文件上传失败!";
    }

    @RequestMapping("/download/{filename}")
    @ResponseBody
    public String downloadFile(HttpServletResponse response, @PathVariable("filename") String filename) throws IOException {
        File file = null;

        if(filename == null || filename.trim().length() == 0) {
            return "请求资源名不能为空!!";
        }
        file = new File("/tmp/" + filename);

        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.print(file.getName());
            return errorMessage;
        }

        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : "+mimeType);
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() +"\""));
        response.setContentLength((int)file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());

        return "成功下载文件";
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody String exceptionHandler() {
        return "exception";
    }

}
