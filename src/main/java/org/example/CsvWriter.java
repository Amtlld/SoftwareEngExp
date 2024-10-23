package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// CsvWriter类: 负责将房源信息写入CSV文件
class CsvWriter {
    private static final List<String> CSV_HEADER_LIST = Arrays.asList(
            "标题", "房屋属性", "面积/㎡", "所在楼层", "总楼层", "朝向", "建造年份",
            "联系人", "小区名称", "附近交通", "总价格/万元", "单价/(元/㎡)"
    );
    private String filePath;

    public CsvWriter(String filePath) {
        this.filePath = filePath;
    }

    // 写入房源信息到CSV
    public void write(List<House> houses) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append(String.join(",", CSV_HEADER_LIST)).append("\n");
            for (House house : houses) {
                writer.append(house.toCsvRow()).append("\n");
            }
            System.out.println("数据抓取完成，结果已保存至 " + filePath);
        } catch (IOException e) {
            System.out.println("写入CSV文件出错: " + e.getMessage());
        }
    }
}
