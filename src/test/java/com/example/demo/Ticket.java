package com.example.demo;

/**
 * @program: 多线程
 * @description: 售票．．．
 * @author: zhangshaolin
 * @create: 2018-12-20 10:22
 **/
public class Ticket implements Runnable {

    //总票数100 所有线程共享
    public int ticket = 100;

    volatile boolean stopMe = false;

    public Ticket(int ticket) {
        this.ticket = ticket;
    }

    public void stopMe() {
        this.stopMe = true;
    }

    @Override
    public void run() {

        //模拟售票
        while (true) {

            if (stopMe) {
                System.out.println(Thread.currentThread().getName() + " 停止售票: " + ticket);
                break;
            }

            //剩余票数大于0 则继续卖票
            synchronized (this) {
                if (ticket > 0) {
                    //为了让线程安全问题明显些  加入线程休眠
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " 正在售票: " + ticket--);

                    if (ticket == 1) {
                        stopMe();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Ticket task = new Ticket(100);
        Thread thread1 = new Thread(task, "窗口1");
        Thread thread2 = new Thread(task, "窗口2");
        Thread thread3 = new Thread(task, "窗口3");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("售票结束,当前剩余票数: " + task.ticket);
    }
}
