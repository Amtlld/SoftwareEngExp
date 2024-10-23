package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// HouseScraper类: 负责抓取网页和解析房源信息
class HouseScraper {
    private static final String BASE_URL = "https://sh.esf.fang.com/house/i3%d/";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3";
    private int pages;

    public HouseScraper(int pages) {
        this.pages = pages;
    }

    // 抓取网页并返回房源列表
    public List<House> scrape() {
        List<House> houseList = new ArrayList<>();
        for (int pageNum = 1; pageNum <= pages; pageNum++) {
            String url = String.format(BASE_URL, pageNum);
            System.out.println("正在抓取第 " + pageNum + " 页: " + url);

            try {
                Document doc = Jsoup.connect(url)
                        .userAgent(USER_AGENT)
                        .timeout(10000)
                        .get();

                Elements elements = doc.select("dl.clearfix");
                for (Element element : elements) {
                    try {
                        House house = parseHouse(element);
                        houseList.add(house);
                        System.out.println("抓取成功: " + house.toCsvRow());
                    } catch (Exception e) {
                        System.out.println("解析房源信息出错: " + e.getMessage());
                    }
                }

                // 避免频繁请求导致封禁
                Thread.sleep(2000);

            } catch (IOException | InterruptedException e) {
                System.out.println("请求出错: " + e.getMessage());
            }
        }
        return houseList;
    }

    // 解析单个房源信息
    private House parseHouse(Element element) {
        String title = element.select("span.tit_shop").text().trim();
        String[] telShop = element.select("p.tel_shop").text().split("\\|");
        String roomInfo = telShop[0].trim();
        String area, orientation, buildingYear, currentFloor, totalFloors;


        area = telShop[1].replace("㎡","").trim();
        String floorInfo = telShop[2];
        orientation = (telShop.length > 3) ? telShop[3].trim() : "N/A";
        buildingYear = (telShop.length > 4) ? telShop[4].trim() : "N/A";


        String[] floors = floorInfo.split("（共");
        currentFloor = floors[0].trim();
        totalFloors = floors[1].replace("层）", "").trim();


        String contact = element.select("span.people_name").text().trim();
        String communityName = element.select("p.add_shop a").text().trim();
        String transportation = element.select("p.clearfix.label span").text().trim();
        String price = element.select("dd.price_right span").get(0).text().replace("万","").trim();
        String pricePerSqm = element.select("dd.price_right span").get(1).text().replace("元/㎡","").trim();

        return new House(title, roomInfo, area, currentFloor, totalFloors,
                orientation, buildingYear, contact, communityName, transportation,
                price, pricePerSqm);
    }
}