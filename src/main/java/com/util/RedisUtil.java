package com.util;

public class RedisUtil {

    // ValueOperations：对redis字符串类型数据操作
    // HashOperations：对hash类型的数据操作
    //  ListOperations：对链表类型的数据操作
    //  SetOperations：对无序集合类型的数据操作
    //   ZSetOperations：对有序集合类型的数据操作

    //键操作
    // redisTemplate.hasKey("aaa");  // 判断key是否存在
    // redisTemplate.keys("zwl:name*");//模糊查询
    /// redisTemplate.expire("aaa",10, TimeUnit.SECONDS );  //设置过期时间
    //  redisTemplate.expireAt("aaa",new Date());            //设置过期时间
//        redisTemplate.persist("aaa");//取消过期日期
//        redisTemplate.type("aaaa");//返回元素类型
//        redisTemplate.getExpire("aaaa",TimeUnit.SECONDS);//返回过期时间
//        redisTemplate.delete("aaa");//删除
//        redisTemplate.move("aaa",1);//移库

    //String
//        redisTemplate.opsForValue().set("key","val",10,TimeUnit.MINUTES); //插入
//        redisTemplate.opsForValue().setIfAbsent("key","val",10,TimeUnit.MINUTES); //不存在时插入
//        redisTemplate.opsForValue().setIfPresent("key","val");//存在时修改
//        redisTemplate.opsForValue().get("key");//查询
//        redisTemplate.opsForValue().getAndSet("key","newVal");//先get再set
//        redisTemplate.opsForValue().append("key","newStr");//追加 无则创建
//        redisTemplate.opsForValue().increment("key",12); //增加
//        redisTemplate.opsForValue().decrement("key",12);//减少

    //MAP
//        redisTemplate.opsForHash().put("map","mapK","mapV");//添加一条map值
//        redisTemplate.opsForHash().putAll("map",new HashMap());
//        redisTemplate.opsForHash().putIfAbsent("map","mapK","mapV");//不存在时添加
//        redisTemplate.opsForHash().get("map","mapK"); //获得单个属性值
//        redisTemplate.opsForHash().multiGet("map",new ArrayList());//获得多个属性值
//        redisTemplate.opsForHash().entries("map");//获得map
//        redisTemplate.opsForHash().hasKey("map","mapK");//判断map中属性是否存在
//        redisTemplate.opsForHash().size("map");//返回map中属性数量
//        redisTemplate.opsForHash().keys("map");//返回所有字段名字
//        redisTemplate.opsForHash().values("map");//返回所有字段值
//        redisTemplate.opsForHash().delete("map","hk1","hk2");//删除map属性
//        redisTemplate.opsForHash().increment("map","k",11);//指定属性增加值
//        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan("map", ScanOptions.NONE); // XXXs迭代hash里面的元素
//        while (cursor.hasNext()){
//            Map.Entry<Object,Object> entry = cursor.next();
//            System.out.println("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
//        }
    //List
//        redisTemplate.opsForList().rightPush("L","1","2"); //向右插入
//        redisTemplate.opsForList().rightPushAll("k",new ArrayList());//向右插入
//        redisTemplate.opsForList().rightPushIfPresent("k",new ArrayList());//不存在不执行 存在则之心
//        redisTemplate.opsForList().leftPush("L","1","2"); //向左插入
//        redisTemplate.opsForList().size("map"); //list长度
//        redisTemplate.opsForList().range("map",1,3); // 返回存储在 key 的列表里指定范围内的元素。 start 和 end
//        redisTemplate.opsForList().set("map",3,"asasas");//在指定位置设值
//        redisTemplate.opsForList().index("map",3);//返回指定位置元素
//        redisTemplate.opsForList().remove("map",3,"xxxx");//移除list里签3次出现的xxx
//        redisTemplate.opsForList().trim("map",3,3);//修剪(trim)一个已存在的 list 先截取start前面的 形成listB 在截取listB end后面的
//        redisTemplate.opsForList().leftPop("map");//返回并移除list里第一个元素
//        redisTemplate.opsForList().leftPush("key","val","insertVal");//把insertVal插入val前面
//        redisTemplate.opsForList().leftPop("key",10,TimeUnit.SECONDS);//取出列表的第一个元素并产生一个多长时间的阻塞

    //Set
//        redisTemplate.opsForSet().add("key","11,","2");//增加
//        redisTemplate.opsForSet().size("k");//大小
//        redisTemplate.opsForSet().members("k");//所有成员
//        redisTemplate.opsForSet().remove("k","11","222");//删除指定成员
//        redisTemplate.opsForSet().isMember("k","1");//判断是否成员
//        redisTemplate.opsForSet().pop("k",3);//随机删除一个或多个随机元素
//        redisTemplate.opsForSet().randomMembers("k",2);//随机返回n个元素但不删除
//        redisTemplate.opsForSet().scan("k",ScanOptions.NONE);//迭代
//
//    //zset
//        redisTemplate.opsForZSet().add("k","val",1);//插入元素
//        redisTemplate.opsForZSet().add("k", new LinkedHashSet() );//插入元素
//
//        redisTemplate.opsForZSet().range("k",1,3);//按分值（序号）排序后得到区间内成员 返回字符串
//        redisTemplate.opsForZSet().reverseRange("k",1,3);//同上 倒叙
//        redisTemplate.opsForZSet().rangeWithScores("k",1,3);//按分值（序号）排序后得到区间内成员 返回ASCII码和分值
//        redisTemplate.opsForZSet().reverseRangeWithScores("k",1,3);//同上 倒叙
//
//        redisTemplate.opsForZSet().rangeByScore("k",1,3);//返回key的有序集合中的分数在min和max之间的所有元素
//        redisTemplate.opsForZSet().reverseRangeByScore("k",1,3);//同上倒叙
//
//        redisTemplate.opsForZSet().rangeByScoreWithScores("k",1,3);// 同75 只是返回ASCII码和分值
//        redisTemplate.opsForZSet().reverseRangeByScoreWithScores("k",1,3);
//
//        redisTemplate.opsForZSet().rangeByScore("k",1,3,1,2); //同上 offset：开始位  count：筛选数量
//        redisTemplate.opsForZSet().reverseRangeByScore("k",1,3,1,1);//同上倒叙
//
//        redisTemplate.opsForZSet().rangeByScoreWithScores("k",1,3,2,3);// 同75 只是返回ASCII码和分值
//        redisTemplate.opsForZSet().reverseRangeByScoreWithScores("k",1,31,1,1);
//
//        redisTemplate.opsForZSet().removeRangeByScore("k",1,3);//移除得分区间内的成员
//        redisTemplate.opsForZSet().size("k");
//        redisTemplate.opsForZSet().rank("k","o");//集合中成员排名
//        redisTemplate.opsForZSet().reverseRank("k","o");//同上倒叙
//        redisTemplate.opsForZSet().score("k","o");//发挥成员分数
//        redisTemplate.opsForZSet().count("k",1,30);//返回分值区间内成员数量
//        redisTemplate.opsForZSet().remove("k","o1","o2");//移除指定元素
//        redisTemplate.opsForZSet().incrementScore("k","o1",3);//给有序set的score上增加增量score
//        redisTemplate.opsForZSet().scan("ke",ScanOptions.NONE);

}
