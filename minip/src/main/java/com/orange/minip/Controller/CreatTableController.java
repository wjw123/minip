package com.orange.minip.Controller;/*
 *@author orange
 *@version 1.0.0
 *@Description
 *@creatTime 2019年05月24日19:38:00
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.orange.minip.DataObject.CreatTable;
import com.orange.minip.DataObject.Response;
import com.orange.minip.Service.CreatTableService;
import com.orange.minip.Service.InformationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/table")
public class CreatTableController {

    @Autowired
    private CreatTableService creatTableService;

    @Autowired
    private InformationService informationService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private static String ACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7daa3ff763246b27&secret=06b028e85e224673db04aa0dcbbf93b7";

    @Value("${web.images-path}")
    private String filepathString;

    private Logger logger = LoggerFactory.getLogger(getClass());

    //返回的url地址
    private final static String RETURN_URL="https://statistics.fantasy512.cn/images/";


    /**
     *  保存table信息并返回tableId
     * @param tableJson
     * @return
     */
    @RequestMapping(value = "/creattable",method = RequestMethod.POST)
    public Response saveTable(@RequestBody JSONObject tableJson){
        Response response=new Response();
        Map<String,Object> map=new HashMap<>();
        String tableTitle=tableJson.getString("tableTitle");
        String tableDeadline=tableJson.getString("tableDeadline");
        String tableCreatopenid=tableJson.getString("tableCreatopenid");
        String tableContent=tableJson.getString("tableContent");
        CreatTable creatTable=new CreatTable(tableTitle,tableDeadline,tableCreatopenid,tableContent);
        creatTableService.saveTable(creatTable);
        map.put("tableId",creatTable.getTableId());
        response.setCode(0);
        response.setMsg("创建成功");
        response.setObject(map);
        return response;
    }

    /**
     * 获取填写table的总人数
     * @param tableId
     * @return
     */
    @RequestMapping(value="/getCount",method = RequestMethod.GET)
    public Response getCount(Integer tableId){
       Response response=new Response();
       response.setCode(0);
       response.setMsg("获取成功");
       response.setObject(creatTableService.getCount(tableId));
       return response;
    }

    /**
     * 请求表格字段，此处可设置浏览量增加
     * @param tableId
     * @return
     */
    @RequestMapping(value = "/getContent",method = RequestMethod.GET)
    public Response getContent(Integer tableId){
        String contentString=creatTableService.getContent(tableId).substring(1,creatTableService.getContent(tableId).length()-1);
        String[] strings=contentString.split(",");
        JSONArray jsonArray=JSONArray.parseArray(JSON.toJSONString(Arrays.asList(strings)));
        Response response=new Response();
        response.setMsg("获取成功");
        response.setCode(0);
        response.setObject(jsonArray);
        return  response;
    }


    /***
     * 获取用户所创建的所有表信息
     * @param tableCreatopenid 用户的ID
     * @return
     */
    @RequestMapping(value = "/getAllCreattable",method = RequestMethod.GET)
    public Response getAllCreattable(String tableCreatopenid){
        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功获取到用户所创建的所有表信息");
        response.setObject(creatTableService.getAllCreatetable(tableCreatopenid));
        return response;
    }

    /***
     * 获取到用户所参与的所有表信息
     * @param partOpenid
     * @return
     */
    @RequestMapping(value = "/getAllParttable",method = RequestMethod.GET)
    public Response getpartTable(String partOpenid){
        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功获取到用户所参与的所有表信息");
        response.setObject(creatTableService.getAllParttable(partOpenid));
        return response;
    }

    /***
     * 表格ID获取表格信息，修改用
     * @param tableId
     * @return
     */
    @RequestMapping(value = "/getCreateTableById",method = RequestMethod.GET)
    public Response getCreateTableById(Integer tableId){
        CreatTable creatTable=creatTableService.getCreateTableById(tableId);
        JSONObject js=new JSONObject();
        List<Map<String,Object>>data=new ArrayList<>();
        String[] names=creatTable.getTableContent().substring(1,creatTable.getTableContent().length()-1).split(",");

        for (String name:names){
            Map<String,Object>map=new HashMap<>();
            map.put("name",name);
            data.add(map);
        }

        js.put("titleArray",data);

        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功获取到表格信息");
        response.setObject(js);
        return  response;
    }

    /***
     * 修改表格数据
     * @param tableJson
     * @return
     */
    @RequestMapping(value = "/updatetable",method = RequestMethod.POST)
    public Response updateTable(@RequestBody JSONObject tableJson){
        Response response=new Response();
        Map<String,Object> map=new HashMap<>();
        String tableTitle=tableJson.getString("tableTitle");
        String tableDeadline=tableJson.getString("tableDeadline");
        Integer tableId=tableJson.getInteger("tableId");
        String tableContent=tableJson.getString("tableContent");
        CreatTable creatTable=new CreatTable(tableTitle,tableDeadline,tableId,tableContent);
        creatTableService.updateTable(creatTable);
        map.put("tableId",tableId);
        response.setCode(0);
        response.setMsg("成功修改信息");
        response.setObject(map);
        return response;
    }

    /***
     * 删除表格信息
     * @param tableId
     * @return
     */
    @RequestMapping(value = "/deletetable",method = RequestMethod.GET)
    public Response deleteTable(Integer tableId){
        int result=creatTableService.deleteTable(tableId);
        int inforesult=informationService.deleteInfoByTable(tableId);
        Response response=new Response();
        response.setCode(0);
        response.setMsg("成功删除表格信息");
        response.setObject(Integer.valueOf(result));
        return response;
    }

    /***
     * 生成二维码程序
     * @throws IOException
     */
    @RequestMapping(value = "/getWxCode",method = RequestMethod.GET)
    public String getwxcode(HttpServletResponse response,HttpServletRequest request,String tableId,String wxurl) throws IOException {
        JSONObject res=restTemplate.getForObject(ACCESS_TOKEN,JSONObject.class);
        String accessToken=(String)res.get("access_token");
        InputStream inputStream=null;
        OutputStream outputStream=null;
        File file=null;

        try{
            String url="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken;

            //生成二维码时所需要的参数
            JSONObject param=new JSONObject();
            param.put("page",wxurl);//扫描二维码进入的路径
            param.put("scene","tableId="+tableId);//进入页面时传递的参数
            param.put("width",430);//二维码的宽度
            param.put("auto_color",false);//二维码线条的颜色
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color",line_color);

            logger.info(">>>>>>调用生成网址url码生成入参:"+param.toString()+"<<<<<");
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

            //发送请求时携带的数据
            HttpEntity requestEntity = new HttpEntity(param, headers);

            //ResponseEntity<byte[]>entity=restTemplate.exchange(url, HttpMethod.POST,requestEntity,byte[].class);

            //发送请求并获取返回到的JSONObject
          // JSONObject return_json=restTemplate.postForObject(url,requestEntity,JSONObject.class);

            // logger.info(">>>>>>>>>>>>获取返回的数据:"+return_json);

            ResponseEntity<byte[]> entity = restTemplate.exchange(url,HttpMethod.POST,requestEntity,byte[].class);

            //生成微信小程序回参
            logger.info(">>>>>> 调用小程序生成微信永久小程序码URL接口回参: " + entity);

            //处理返回参数
            byte[] result=entity.getBody();
            inputStream = new ByteArrayInputStream(result);

            //生成默认的文件
            File filepath=new File(filepathString);
            if(!filepath.exists()){
                //mkdirs才能创建多级目录
                filepath.mkdirs();
            }
            file=new File(filepath+File.separator+tableId+".jpeg");
            if(!file.exists()){
                file.createNewFile();
            }

            //向默认的文件中写入路径并生成二维码
            outputStream=new FileOutputStream(file);
            inputStream = new ByteArrayInputStream(result);
            int content=0;
            byte[]buffer=new byte[1024*8];
            while  ((content = inputStream.read(buffer,0,1024)) != -1) {
                outputStream.write(buffer,0, content);
            }
            outputStream.flush();
        }catch (Exception e){
            logger.error("调用小程序接口生成二维码错误:"+e.getMessage());
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
           // getImage(filepathString+File.separator+file.getName(),request,response);
            return RETURN_URL+file.getName();
        }
    }

    //返回图片的IO流
    public void getImage(String url, HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletOutputStream out=null;
        FileInputStream ips=null;
        try {
          ips = new FileInputStream(new File(url));
          out = response.getOutputStream();

          //读取流文件
          int len = 0;
          byte[] buffer = new byte[1024 * 10];
          while ((len = ips.read(buffer)) != -1) {
              out.write(buffer, 0, len);
          }
          out.flush();
      }catch (Exception e){
          e.printStackTrace();
      }finally {
            ips.close();
            out.close();
      }
    }

}
