package com.client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandRegexes {
    NICK("\\/NICK\\s?(\\w+)?"),
    JOIN("\\/JOIN (\\w+)"),
    LEAVE("\\/LEAVE (\\w+)"),
    EXIT("\\/EXIT"),
    MESSAGE_CHANNEL("@(\\w+) ([\\W\\w]+)")
    ;

    private final Pattern pattern;

    CommandRegexes(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    /**
     * Find a match for an input string, returning the captured groups. If no match was found, null will be returned.
     */
    public List<String> match(String s) {
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            return null;
        } else {
            List<String> groups = new ArrayList<>();
            int groupCount = matcher.groupCount();
            for (int i = 1; i <= groupCount; ++i) {
                groups.add(matcher.group(i));
            }
            return groups;
        }
    }

}
