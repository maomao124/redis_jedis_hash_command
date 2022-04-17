import org.junit.jupiter.api.*;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Project name(项目名称)：redis_jedis_hash_command
 * Package(包名): PACKAGE_NAME
 * Class(类名): Redis
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/4/17
 * Time(创建时间)： 14:26
 * Version(版本): 1.0
 * Description(描述)：Hash（哈希散列）是 Redis 基本数据类型之一，它以字符串映射表的形式来进行存储
 * <p>
 * <p>
 * 命令	        说明
 * HDEL	        用于删除一个或多个哈希表字段
 * HEXISTS	    用于确定哈希字段是否存在
 * HGET	        获取存储在 key 中的哈希字段的值
 * HGETALL	    获取存储在 key 中的所有哈希字段值
 * HINCRBY	    为存储在 key 中的哈希表指定字段做整数增量运算
 * HKEYS	    获取存储在 key 中的哈希表的所有字段
 * HLEN	        获取存储在 key 中的哈希表的字段数量
 * HSET	        用于设置存储在 key 中的哈希表字段的值
 * HVALS	    用于获取哈希表中的所有值
 */


public class Redis
{
    static Jedis jedis;

    @BeforeEach
    void setUp()
    {
        System.out.println("----");
    }

    @AfterEach
    void tearDown()
    {
        System.out.println("----");
    }

    @BeforeAll
    static void beforeAll()
    {
        jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");
        System.out.println("打开");
    }

    @AfterAll
    static void afterAll()
    {
        System.out.println("关闭");
        jedis.close();
    }

    @Test
    void hset()
    {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");

        System.out.println(jedis.hset("map1", map));

        System.out.println(jedis.hset("map1", "key6", "value6"));
    }

    @Test
    void hget()
    {
        System.out.println(jedis.hget("map1", "key2"));
        //不存在
        System.out.println(jedis.hget("map2", "key2"));
        System.out.println(jedis.hget("map1", "key44"));
    }

    @Test
    void hgetAll()
    {
        System.out.println(jedis.hgetAll("map1"));
        //不存在
        System.out.println(jedis.hgetAll("map2"));
    }

    @Test
    void hdel()
    {
        //不存在
        long hdel = jedis.hdel("map1", "key0");
        System.out.println(hdel);
        //单个
        System.out.println(jedis.hdel("map1", "key1"));
        System.out.println(jedis.hdel("map1", "key2", "key3", "key4"));
    }

    @Test
    void hexists()
    {
        System.out.println(jedis.hexists("map1", "key5"));
        //不存在
        System.out.println(jedis.hexists("map2", "key5"));
        System.out.println(jedis.hexists("map1", "key9"));
    }

    @Test
    void hincrbyBefore()
    {
        System.out.println(jedis.hset("map1", "key7", "5000"));
    }

    @Test
    void hincrby()
    {
        System.out.println(jedis.hincrBy("map1", "key7", 200));
        System.out.println(jedis.hget("map1", "key7"));
    }

    @Test
    void hincrbyFloat()
    {
        System.out.println(jedis.hincrByFloat("map1", "key7", 56.25));
        System.out.println(jedis.hincrByFloat("map1", "key7", -3.78));
    }

}
