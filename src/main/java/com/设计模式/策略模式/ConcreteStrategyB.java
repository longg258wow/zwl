package com.设计模式.策略模式;

public class ConcreteStrategyB implements Strategy{
    @Override
    public void algorithmLogic() {
        // 赢
        System.out.println("第一场：下等马vs上等马  第二场：上等马vs中等马  第三场：中等马vs下等马  赛果：赢！");
    }
}
