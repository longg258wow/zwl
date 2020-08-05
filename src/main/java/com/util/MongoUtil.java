package com.util;


public class MongoUtil {



 //   MongoClient mongoClient = MongoUtil.getMongoClientCheck();

 //       int size = mongoClient.getMaxBsonObjectSize();
//        System.out.println("MaxBsonObjectSize >>>" + size);
 //       System.out.println("MongoDB 服务端地址：" + mongoClient.getAddress().toString());

    // **************************       查询            ****************************
    //  mongotemplate.findById("5df84d0819328c03a58e3e91",MongoUser.class);
    //  mongotemplate.find(query,MongoUser.class);
    //   mongotemplate.findDistinct(query,"age","user",MongoUser.class,Integer.class);
    //   mongotemplate.findAll(MongoUser.class)
    //   mongotemplate.findOne(query,MongoUser.class)

//        Integer pageNum = 1;
//        Integer pageSize = 2;
    //        Criteria criteria = new Criteria();
//         criteria.orOperator(Criteria.where("age").gt(8).and("userName").is("hahaha"),Criteria.where("sex").is(1));
//         Query query = new Query(criteria);
//        Sort sort = Sort.by(Sort.Order.asc("sex"),Sort.Order.desc("age"));
//        query.with(sort);
//        query.skip((pageNum - 1) * pageSize);
//        query.limit(pageSize);
//        List<MongoUser> userList = mongotemplate.find(query,MongoUser.class);
//        for(MongoUser user:userList){
//            System.out.println("userName ="+user.getUserName());
//        }
    //  MongoUser user = mongotemplate.findById("5df84d0819328c03a58e3e91",MongoUser.class);
    //       System.out.println(user.getUserName());
//
//        List<Integer> ageList = mongotemplate.findDistinct(query,"age","user",MongoUser.class,Integer.class);
//        for(Integer age:ageList){
//            System.out.println("xxxx = "+age);
//        }

    //project:列出所有本次查询的字段，包括查询条件的字段和需要搜索的字段；
//        Criteria criteria = Criteria.where("age").gt(11);
//        Aggregation aggregation = Aggregation.newAggregation(
//                Aggregation.project("sex", "count", "age"),
//                Aggregation.match(criteria),
//                Aggregation.group("sex").first("sex").as("sex").count().as("count").sum("age").as("totalAge"),
//                Aggregation.sort( Sort.by(Sort.Order.asc("sex"))),
//                Aggregation.skip(1L),
//                Aggregation.limit(2)
//        );
//        AggregationResults<HashMap> aggregationResults = mongotemplate.aggregate(aggregation, "user", HashMap.class);
//        for (HashMap obj : aggregationResults) {
//            System.out.println(obj.toString());
//        }


    //map reduce 查询
//        String map  = "function () {for(var i=0;i<this.x.length;i++){emit(this.x[i],1);}}";
//        String reduce = "function(key, values){ var sum = 0; for(var i = 0; i<values.length;i++ )sum += values[i]; return sum; }";
//        //参数分别为：collection名，map函数的字符串，reduce函数的字符串，输出的表名[可选]，实体类
//        MapReduceResults<HashMap> results = mongotemplate.mapReduce("map_reduce", map, reduce,new MapReduceOptions().outputCollection("mr_out"), HashMap.class);
//        Iterator<HashMap> mongoCursor = results.iterator();
//        while (mongoCursor.hasNext()) {
//            HashMap dbName = mongoCursor.next();
//            System.out.println("dbName>>>" + dbName);
//        }
//        double[] poi = new double[]{1D,2D}; //地理查询
//        NearQuery near = NearQuery .near(new Point(poi[0],poi[1])) .spherical(false).num(1);
//        GeoResults<MongoUser> results = mongotemplate.geoNear(near,MongoUser.class)




    // **************************        插入            ****************************
    // insert：主键如果重复则报错 save 主键重复时则修改  save只是单体不能批量
//        mongotemplate.insert(user);
//        mongotemplate.insert(userList, User.class);
//        mongotemplate.insert(userList, "user");
//        mongotemplate.insert(user,"user");
    //mongotemplate.insertAll(userList);
    // mongotemplate.save(user,"user")
//        List<MongoUser> userList = new ArrayList<>();
//        MongoUser user= new MongoUser();
//        user.setUserName("猪八戒");
//        user.setSex(1);
//        user.setAge(14444);
//        MongoUser user2= new MongoUser();
//        user2.setUserName("猪八戒2");
//        user2.setSex(1);
//        user2.setAge(14444);
//        userList.add(user);
//        userList.add(user2);
//    //    mongotemplate.insertAll(userList);
//        //批量插入  和insert(userList, User.class); 都是批量插入
//        BulkOperations ops = mongotemplate.bulkOps(BulkOperations.BulkMode.UNORDERED,"user");
//        ops.insert(user);
//        ops.insert(user2);
//        ops.insert(userList);
//        ops.execute();
    // **************************        修改            ****************************
//        mongotemplate.updateFirst(query, update, "user"); //修改第一条匹配的
//        mongotemplate.updateMulti(query, update, "user"); //修改所有匹配的
//        mongotemplate.upsert(query, update, "user");  //有则修改 无则新增 只修改第一条
//        mongotemplate.findAndModify(query,update,MongoUser.class); //先查找在修改 返回修改前数据
    //       mongotemplate.findAndReplace(query,new MongoUser(),"user"); 用某个对象去替换
    //         Query query = new Query();
    //         query.addCriteria(Criteria.where("sex").is(1));  //_
    ///  Update update = Update.update("userName", "打土匪");
    //     UpdateResult upsert = mongotemplate.updateFirst(query, update, "user"); //修改第一条匹配的
    //    UpdateResult upsert = mongotemplate.updateMulti(query, update, "user"); //修改所有匹配的
    //     UpdateResult upsert = mongotemplate.upsert(query, update, "user");  //有则修改 无则新增 只修改第一条
    //    System.out.println(upsert);
    //      MongoUser user = mongotemplate.findAndModify(query,update,MongoUser.class);
    //    System.out.println(user.getUserName());

    //批量修改
//        Query query = Query.query(Criteria.where("id").in("asasa"));
//        Update update = Update.update("is_delete", 1);
//        BulkOperations ops = mongotemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, MongoUser.class);
//        ops .updateMulti(query, update).execute();



    // **************************        删除            ****************************
//        mongotemplate.remove(query,"user");
//        mongotemplate.remove(user2,"user");
//        mongotemplate.findAllAndRemove(query, MongoUser.class);
    //       mongotemplate.findAndRemove(query, MongoUser.class);
//        Query query = Query.query(Criteria.where("sex").is(1));
//        MongoUser user2= new MongoUser();
//        user2.setId("5de8b7c05294c17e48ce256d");
//        user2.setUserName("打土匪");
//     //   DeleteResult deleteResult = mongotemplate.remove(query,"user");
//        DeleteResult deleteResult = mongotemplate.remove(user2,"user") ;//  remove(query , User.class);
//        System.out.println(deleteResult);
//        List<MongoUser> mongoUserList = mongotemplate.findAllAndRemove(query, MongoUser.class);
//        for (MongoUser mongoUser : mongoUserList) {
//            System.out.println("xxx = "+mongoUser.getUserName());
//        }


//        MongoIterable<String> mongoIterable = mongoClient.listDatabaseNames();
    //       MongoCursor<String> mongoCursor = mongoIterable.iterator();
//        while (mongoCursor.hasNext()) {
//            String dbName = mongoCursor.next();
//            System.out.println("dbName>>>" + dbName);
//        }


    //    MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

    //1、创建集合
//        CreateCollectionOptions options = new CreateCollectionOptions();
//        options.capped(true); //如果为 true，则创建固定大小集合当达到最大值时，它会自动覆盖最早的文档。必须制定size
//        options.sizeInBytes(10000); //为固定集合指定一个最大值，以千字节计（KB）
//        options.maxDocuments(100);//指定固定集合中包含文档的最大数量。
//        mongoDatabase.createCollection("list", options);

    //2、删除集合
    //      mongoDatabase.getCollection("list").drop();

    //3、 查询集合
    //   MongoCollection<Document> collection = mongoDatabase.getCollection("user");

    //4、  插入文档
    //     Document document = new Document("name","张三") .append("sex",1) .append("age", 18);
    //    collection.insertOne(document);
//        List<Document> list = new ArrayList<>();
//        for (int i = 1; i <= 3; i++) {
//            Document document = new Document("name", "张三"+i).append("sex", 1).append("age", 18);
//            list.add(document);
//        }
//        collection.insertMany(list);

    // 5、修改文档
//        Bson filter = Filters.eq("name", "张三1");
//        Document document = new Document("$set", new Document("age", 100));
//        collection.updateMany(filter, document);
    //   collection.updateOne(filter, document);

    // 5   删除文档
    //     Bson filter = Filters.eq("age",18);
    //      collection.deleteOne(filter);
    //   collection.deleteMany(filter);

    ///查询
    //  Comparison eq 等于  ne:不等于  lt 小于  lte 小于等于  gt 大于  gte 大于等于  nin 不在 in 在
    //  Logical  and  并  or或  not 不     nor  并且不
    //  Element exists  匹配具有指定字段的文档  type   如果字段是指定类型 则选择文档
    //  Evaluation expr    允许在查询语言中使用聚合表达式。

    //   Bson filter1 = Filters.eq("name", "张三1");
    ///     Bson filter2 = Filters.lt("age",33);  //小于  Filters.not(filter2);  Filters.nor(filter1,filter2);Filters. exists("name");
    //       Bson filter3 =Filters.type("name","string");

    //      Bson filter3 = Filters.in("_id", new ObjectId("5de880dcbfe383706983a6c2"), new ObjectId("5de880dcbfe383706983a6c3"));  //小于
    //      Bson filterx = Filters.eq("_id", new ObjectId("5de880dcbfe383706983a6c2"));  //小于

    //   Bson filter4 = Filters.and(filter1,filter2);
    //   Bson filter5 = Filters.or(filter4,filter3) ;

    //     Bson sortJson = new Document().append("age", 1);//-1是倒叙
    //分页 ： skip(1).limit(2);  排序 sortJson  .sort(sortJson).skip(1).limit(2);
    //    FindIterable<Document> findIterable = collection.find(filter3).sort(sortJson).skip(0).limit(3);
    //    MongoCursor cursor = findIterable.iterator();
    //    while (cursor.hasNext()) {
    //        System.out.println(cursor.next());
//            Document out1 = (Document) cursor.next();//创
//            System.out.println(out1.getObjectId("_id"));
//            System.out.println(out1.getString("name"));
    //   }

    //2 索引
//        collection.createIndex(new Document("sex", 1)); //创建索引
//        collection.createIndex(new Document("name",1),new IndexOptions().unique(true));//创建唯一索引
//        ListIndexesIterable<Document> indexList = collection.listIndexes();//查询所有索引
//        for (Document document : indexList) {
//            System.out.println(document.toJson());
//        }
    //coll.dropIndexes();//删除索引
    //coll.dropIndex("validata_1");//根据索引名删除某个索引
//        Document sub_match = new Document();
//        sub_match.put("age", 18);
//
//        Document sub_group = new Document();
//        sub_group.put("_id", "$sex");
//        sub_group.put("count", new Document("$sum", "$age")); //sum  avg min max
//
//        Document match = new Document("$match", sub_match);
//        Document group = new Document("$group", sub_group);
//        List<Document> list = new ArrayList<>();
//        list.add(match);
//        list.add(group);
//        AggregateIterable<Document> result =  collection.aggregate(list);
//        MongoCursor cursor = result.iterator();
//        while (cursor.hasNext()) {
//             System.out.println(cursor.next());
//        //    Document out1 = (Document) cursor.next();//创
//         //   System.out.println(out1.getObjectId("_id"));
//         //   System.out.println(out1.getString("name"));
//
//        }
}
