package com.多线程.BlockQueue;


class PushThread extends Thread {
    BlockQueue blockQueue;
    //    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                blockQueue.push();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PopThread extends Thread {
    BlockQueue blockQueue;

    //    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                blockQueue.pop();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 阻塞队列
 *
 *
 */
public class BlockQueue {
    public int size;
    public Integer[] queue;
    public Integer index;
    public Integer num = 0;


    public BlockQueue(int size) {
        this.index = 0;
        this.size = size;
        queue = new Integer[size];
    }

    public void pop() throws InterruptedException {
        synchronized (queue) {
            if (index.equals(0)) {
                System.out.println("空了" + index);
                queue.wait();
            }

            for (int i = 0; i < index; i++) {
                if (i == index - 1) { //最后一位
                    queue[i] = null;
                } else {
                    queue[i] = queue[i + 1];
                }
            }
            index--;
            System.out.print("pop后情况：");
            for (int i = 0; i < queue.length; i++) {
                System.out.print(queue[i] + ",");
            }
            System.out.println();
            queue.notify();
        }

    }


    public void push() throws InterruptedException {
        synchronized (queue) {
            if (index.equals(size)) {
                System.out.println("满了");
                queue.wait();
            }
            queue[index] = num;
            index++;
            num++;
            System.out.print("push后情况：");
            for (int i = 0; i < queue.length; i++) {
                System.out.print(queue[i] + ",");
            }
            System.out.println();
            queue.notify();
        }
    }


    public static void main(String[] args) throws Exception {
        BlockQueue queue = new BlockQueue(3);
        PushThread pushThread = new PushThread();
        pushThread.blockQueue = queue;
        pushThread.start();

        PopThread popThread = new PopThread();
        popThread.blockQueue = queue;
        popThread.start();



    }
}
