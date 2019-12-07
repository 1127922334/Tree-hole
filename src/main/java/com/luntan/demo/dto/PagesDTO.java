package com.luntan.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PagesDTO {

    private List<QuestionDTO> questionDTOS;
    private  boolean showNext=true;
    private  boolean Previous=true;
    private  boolean FirstPage=true;
    private  boolean showEndPage=true;
    private  Integer now_page;
    private  List<Integer> page_number =new ArrayList<>();
    private  Integer total_Page=0;//展示的页数

    public void set_total_Page(Integer count, Integer size){
        if(count%size==0){
            total_Page = count/size;
        }else{
            total_Page = count/size+1;
        }
    }
    public void set_page_dto(Integer count, Integer page) {

            now_page=page;
            page_number.add(page);
            //添加页码
                for (int i=1;i<=3;i++){
                    if(page-i>0){
                        page_number.add(0,page-i);//每次都添加到首位0的位置
                    }
                    if (page+i<=total_Page){
                        page_number.add(page+i);
                    }
                }
            //是否展示上一页
            if (page==1){
                Previous =false;
            }else {
                Previous = true;
            }
            //是否展示下一页
            if(page==total_Page){
                showNext=false;
            }else {
                showNext=true;
            }
            //判断是否展示第一页
            if (page_number.contains(1)){
                //显示的页数是否包含第一页)
                FirstPage =false;
            }else {
                FirstPage = true;
            }
            //判断是否展示最后页
            if (page_number.contains(total_Page)){
                showEndPage = false;
            }else {
                showEndPage = true;
            }
    }
}
