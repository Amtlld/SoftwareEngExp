package org.example;

// House类: 代表一个房源
class House {
    private String title;
    private String roomInfo;
    private String area;
    private String currentFloor;
    private String totalFloors;
    private String orientation;
    private String buildingYear;
    private String contact;
    private String communityName;
    private String transportation;
    private String price;
    private String pricePerSqm;

    // 构造函数
    public House(String title, String roomInfo, String area, String currentFloor, String totalFloors,
                 String orientation, String buildingYear, String contact, String communityName,
                 String transportation, String price, String pricePerSqm) {
        this.title = title;
        this.roomInfo = roomInfo;
        this.area = area;
        this.currentFloor = currentFloor;
        this.totalFloors = totalFloors;
        this.orientation = orientation;
        this.buildingYear = buildingYear;
        this.contact = contact;
        this.communityName = communityName;
        this.transportation = transportation;
        this.price = price;
        this.pricePerSqm = pricePerSqm;
    }

    // 返回一个CSV格式的字符串
    public String toCsvRow() {
        return String.join(",", title, roomInfo, area, currentFloor, totalFloors, orientation,
                buildingYear, contact, communityName, transportation, price, pricePerSqm);
    }
}

