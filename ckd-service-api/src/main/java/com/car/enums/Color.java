package com.car.enums;

/**
 * Created by admin on 2016/11/25.
 */
public enum Color {
    BLANK("白色", 1),  BLACK("黑色",2),BROWN("咖啡色",3), ORANGE("橙色",4),GRAY("灰色",5), PURPLE("紫色",6),RED("红色",7), GREEN("绿色", 8),BLUE("蓝色",9),SILVER("银色",10),CHAMPAGNE("香槟色",11), YELLO("黄色", 12),BROKENCOLOUR("多彩色",13),OTHER("其他",14);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法

    /**
     * 获取颜色名称
     * @param index
     * @return
     */
    public static String getName(int index) {
        for (Color c : Color.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    /**
     * 获取颜色ID
     * @param colorName
     * @return
     */
    public static Integer getColorId(String colorName) {
        for (Color c : Color.values()) {
            if (colorName.equals(c.getName())) {
                return c.getIndex();
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

}
