package pl.kes.algorithms;

import java.util.HashMap;
import java.util.Map;

class Logger {

    Map<String, Integer> msgs;

    public Logger() {
        msgs = new HashMap<>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer time = msgs.get(message);
        if (time == null || time <= timestamp) {
            msgs.put(message, timestamp + 10);
            return true;
        } else {
            return false;
        }
    }
}