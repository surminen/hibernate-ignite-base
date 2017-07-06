package com.msurminen;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.cache.Cache.Entry;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import com.msurminen.user.DBUser;

@SuppressWarnings("javadoc")
public class HelloWorld
{
    public static void main(String[] args) throws IgniteException
    {
        try (Ignite ignite = Ignition.start("/ignite/examples/config/example-ignite.xml"))
        {
            // Configure and create the cache.
            CacheConfiguration<Long, DBUser> cfg = new CacheConfiguration<Long, DBUser>();
            cfg.setIndexedTypes(Long.class, DBUser.class);
            cfg.setName("myCache");
            IgniteCache<Long, DBUser> cache = ignite.getOrCreateCache(cfg);

            // Put some values in the cache
            cache.put(1L, addUser(101, "Superman", "JLA"));
            cache.put(2L, addUser(102, "Batman", "JLA"));
            cache.put(3L, addUser(103, "Joker", "Hydra"));
            cache.put(4L, addUser(104, "Penguin", "Hydra"));

            // Get some values in a crude fashion
            System.out.println(((DBUser) cache.get(1L)).getUsername());
            System.out.println(((DBUser) cache.get(2L)).getUsername());

            // Create a query for retrieving usernames, filtered on userid(s)
            String sql = "select * from DBUser where DBUser.userId>=?";
            SqlQuery<Long, DBUser> query1 = new SqlQuery<>(DBUser.class, sql);

            // Use the query
            List<Entry<Long, DBUser>> res = cache.query(query1.setArgs(103)).getAll();
            res.stream().map(x -> x.getValue()).map(x -> x.getUsername() + " @ " + x.getCreatedBy())
                    .map(x -> "name: " + x).forEach(System.out::println);
        }
    }

    private static DBUser addUser(int userid, String name, String createdBy)
    {
        DBUser user = new DBUser();
        user.setUserId(userid);
        user.setUsername(name);
        user.setCreatedBy(createdBy);
        user.setCreatedDate(new Date());
        return user;
    }
}