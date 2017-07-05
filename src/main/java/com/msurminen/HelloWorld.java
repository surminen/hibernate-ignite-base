package com.msurminen;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;


@SuppressWarnings("javadoc")
public class HelloWorld
{
    public static void main(String[] args) throws IgniteException
    {
        try (Ignite ignite = Ignition.start("c:/ignite/examples/config/example-ignite.xml"))
        {
            // Put values in cache.
            IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCache");
            cache.put(1, "Hello");
            cache.put(2, "World!");
            // Get values from cache
            // Broadcast 'Hello World' on all the nodes in the cluster.
            ignite.compute().broadcast(() -> System.out.println(cache.get(1) + " " + cache.get(2)));
        }
    }
}