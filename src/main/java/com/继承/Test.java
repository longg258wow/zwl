package com.继承;

public class Test {

    public static Parent getP() {
        return new Son();
    }


    public static void main(String[] args) {
        Parent p = new Parent();
        //AbParent abParent =  new AbParent();
        p.huahua();
//        p.setName("p");
        Son son = new Son();
        son.huahua();;
//
//        son.setName("son");
//        son.name="1111";
//        System.out.println(p.getName()+" "+son.getName()+" "+  son.name);

//        Parent p = new Son();
//        ((Son) p).age = "13";

    //    Son son = (Son) p.getP();


//        List<Parent> list = new ArrayList<>();
//        Parent parentA = new Parent();
//        Parent parentB = new Parent();
//        parentA.setName("aaaa");
//        parentB.setName("bbbb");
//        list.add(parentA);
//        list.add(parentB);
//        List<Son> list2 =  (List<Son>)(Object)list;
//        for (Son son:list2){
//            System.out.println(son.getName());
//        }

    }
}
