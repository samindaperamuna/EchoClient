package com.gerrrry.echoclient.data;

import redis.clients.jedis.Jedis;

public class DBConnect {

    private static DBConnect instance;
    private static Jedis jedis;

    private DBConnect(String host, int port) {
	jedis = new Jedis(host, port);
    }

    public static DBConnect getInstance(String host, int port) {
	if (instance == null) {
	    instance = new DBConnect(host, port);
	}

	return instance;
    }

    public String getValue(String key) {
	return jedis.get(key);
    }
}
