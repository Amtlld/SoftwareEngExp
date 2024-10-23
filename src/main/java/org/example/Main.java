package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


// 主类: 负责启动程序
public class Main {
    public static void main(String[] args) {
        HouseScraper scraper = new HouseScraper(5); // 创建爬虫实例
        List<House> houses = scraper.scrape(); // 获取房源信息

        CsvWriter csvWriter = new CsvWriter("house_info.csv"); // 创建CSV写入实例
        csvWriter.write(houses); // 写入数据到CSV
    }
}