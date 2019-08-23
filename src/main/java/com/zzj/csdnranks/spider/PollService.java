package com.zzj.csdnranks.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PollService {

    @Value("${blog.address}")
    private String blogAddress;

    public Map<String,Integer> poll(){
        Map<String,Integer> numsANDranks = new HashMap<>(4);
        int nums = 0;
        int ranks = 0;
        try{
            Document document = Jsoup.connect(blogAddress).get();
            System.out.println("The Blog Title is : " +document.title());
            Element asideProfile = document.getElementById("asideProfile");
            Elements fourElements = asideProfile.getElementsByClass("grade-box clearfix");
            Elements dls = fourElements.get(0).getElementsByTag("dl");

            Element numsElement = dls.get(1);
            Elements numsDDS = numsElement.getElementsByTag("dd");
            Element numsDl = numsDDS.get(0);
            String numsString = numsDl.attr("title");
            nums = Integer.valueOf(numsString);
            Element ranksElement = dls.get(3);
            Elements ranksDDS = ranksElement.getElementsByTag("dl");
            Element ranksDl = ranksDDS.get(0);
            String ranksString = ranksDl.attr("title");
            ranks = Integer.valueOf(ranksString);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            numsANDranks.put("nums",nums);
            numsANDranks.put("ranks",ranks);
            return numsANDranks;
        }

    }
}