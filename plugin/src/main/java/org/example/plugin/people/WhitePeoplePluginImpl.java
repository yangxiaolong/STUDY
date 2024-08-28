package org.example.plugin.people;

import org.springframework.stereotype.Service;

/**
 * @auther yangxiaolong
 * @create 2024/8/28
 */
@Service
public class WhitePeoplePluginImpl implements PeoplePlugin {
    @Override
    public String eat(PeoplePluginType peoplePluginType) {
        if (supports(peoplePluginType)) {
            return "面包";
        }
        return null;
    }

    @Override
    public boolean supports(PeoplePluginType peopleType) {
        return PeoplePluginType.WHITE.equals(peopleType);
    }
}

