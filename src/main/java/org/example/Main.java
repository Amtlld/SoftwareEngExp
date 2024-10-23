package org.example;

import java.util.List;


// 主类: 负责启动程序
public class Main {
    public static void main(String[] args) {
        HouseScraper scraper = new HouseScraper(5); // 创建爬虫实例
        List<House> houses = scraper.scrape(); // 获取房源信息

        CsvWriter csvWriter = new CsvWriter("house_info.csv"); // 创建CSV写入实例
        csvWriter.write(houses); // 写入数据到CSV
    }
}