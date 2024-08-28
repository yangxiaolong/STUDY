package org.example.plugin.people;

/**
 * @auther yangxiaolong
 * @create 2024/8/28
 */
public enum PeoplePluginType {

    YELLOW("YELLOW"),
    WHITE("WHITE");

    private String type;

    PeoplePluginType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
