package com.hncj.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.hncj.entity.User;
import com.hncj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${upload.dir}")
    private String realPath;

    /**
     * 导出excel
     */

    @RequestMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<User> users = userService.findAll();
        users.forEach(user->{
            user.setPhoto(realPath+"/"+user.getPhoto());
        });

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户列表信息", "sheetone"), User.class, users);
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("用户列表.xls","UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();

        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        log.info(String.valueOf(users.size()));
        model.addAttribute("users",users);
        return "index";
    }

    @RequestMapping("/import")
    public String importExcel(MultipartFile excelFile) throws Exception {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        params.setTitleRows(1);
        List<User> users = ExcelImportUtil.importExcel(excelFile.getInputStream(), User.class, params);

        userService.saveAll(users);
        return "redirect:/user/findAll";
    }


}
