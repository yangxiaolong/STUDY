package org.example.plugin.people;

import org.springframework.plugin.core.Plugin;

/**
 * @auther yangxiaolong
 * @create 2024/8/28
 */
public interface PeoplePlugin extends Plugin<PeoplePluginType> {
    String eat(PeoplePluginType peoplePluginType);
}