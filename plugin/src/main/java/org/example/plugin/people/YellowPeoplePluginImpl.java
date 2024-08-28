package org.example.plugin.people;

import org.springframework.stereotype.Service;

/**
 * @auther yangxiaolong
 * @create 2024/8/28
 */
@Service
public class YellowPeoplePluginImpl implements PeoplePlugin {

    @Override
    public String eat(PeoplePluginType peoplePluginType) {
        if (supports(peoplePluginType)) {
            return "馒头、大米";
        }
        return null;
    }

    @Override
    public boolean supports(PeoplePluginType peopleType) {
        return PeoplePluginType.YELLOW.equals(peopleType);
    }

}
