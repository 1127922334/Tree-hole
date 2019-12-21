package com.luntan.demo.cache;

import com.luntan.demo.dto.TagDTO;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tagcache {
    public static List<TagDTO> gettags(){
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO tagDTO = new TagDTO();
        tagDTO.setCatrgoryName("开发语言");
        tagDTO.setTags(Arrays.asList("JavaScript","java","C","C++","HTML5","css","Python","GoLang","PHP","HTML","node.js","objective-c","typescript","shell","swift"));
        TagDTO  tagDTO1 = new TagDTO();
        tagDTO1.setCatrgoryName("开发框架");
        tagDTO1.setTags(Arrays.asList("laravel","spring","SpringBoot",
                "Spring Cloud","SpringMVC","express","django","flask","yii","ruby-on-rails","tornado","koa","struts"));
        TagDTO tagDTO2 = new TagDTO();
        tagDTO2.setCatrgoryName("服务器");
        tagDTO2.setTags(Arrays.asList("linux","naginx","docker","apache","Ubuntu","Centos","tomcat","hadoop","windows"));
        TagDTO tagDTO3 = new TagDTO();
        tagDTO3.setCatrgoryName("数据库");
        tagDTO3.setTags(Arrays.asList("mysql","redis","mongodb","sql","数据库","json","elasticsearch","nosql","memcached","postgresql","sqlite","mariadb"));
        TagDTO tagDTO4  = new TagDTO();
        tagDTO4.setCatrgoryName("运维");
        tagDTO4.setTags(Arrays.asList("linux","nginx","docker","apache","centos","ubuntu","服务器","负载均衡","运维","ssh","vagrant","容器","jenkins","devops","debian","fabric"));
        TagDTO tagDTO5 = new TagDTO();
        tagDTO5.setCatrgoryName("人工智能");
        tagDTO5.setTags(Arrays.asList("算法","机器学习","人工智能","深度学习","数据挖掘","tensorflow","神经网络","图像识别","自然语言处理","人脸识别","机器人","pytorch","自动驾驶"));
        TagDTO tagDTO6 = new TagDTO();
        tagDTO6.setCatrgoryName("其他");
        tagDTO6.setTags(Arrays.asList("程序员","http","segmentfault","https","安全","websocket","restful","xss","区块链","csrf","graphql","rpc","比特币","以太坊","udp","智能合约"));
        TagDTO tagDTO7 = new TagDTO();
        tagDTO7.setCatrgoryName("前端");
        tagDTO7.setTags(Arrays.asList("javascript","前端","vue.js","css","html","html5","node.js","react.js","jquery","css3","es6","typescript","chrome","npm","bootstrap","sass","less","chrome-devtools","firefox","angular","coffeescript","safari","postcss","postman","fiddler","webkit","yarn","firebug","edge"));
        TagDTO tagDTO8 = new TagDTO();
        tagDTO8.setCatrgoryName("后端");
        tagDTO8.setTags(Arrays.asList("php","java","node.js","python","c++","c","golang","spring","django","flask","springboot","后端","c#","swoole","ruby","asp.net","ruby-on-rails","scala","rust","lavarel","爬虫"));
        tagDTOS.add(tagDTO);
        tagDTOS.add(tagDTO2);
        tagDTOS.add(tagDTO1);
        tagDTOS.add(tagDTO3);
        tagDTOS.add(tagDTO4);
        tagDTOS.add(tagDTO5);
        tagDTOS.add(tagDTO6);
        tagDTOS.add(tagDTO7);
        tagDTOS.add(tagDTO8);
        return tagDTOS;
    }


    public  static String isTagValues(String tag){
        String[] spilt = StringUtils.split(tag,",");
        List<TagDTO> tagDTOList = gettags();
        //取出第二层的值
        List<String> taglist  = tagDTOList.stream().flatMap(tags -> tags.getTags().stream()).collect(Collectors.toList());
       // 返回输入的标签不存在标签库的数据
        String NoList = Arrays.stream(spilt).filter(t -> !taglist.contains(t)).collect(Collectors.joining(","));
        return NoList;
    }
}
