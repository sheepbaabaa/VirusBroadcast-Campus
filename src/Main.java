import javax.swing.*;

import java.util.List;
import java.util.Random;

/**
 * 模拟程序主入口
 *
 * @author
 * @comment GinRyan
 * @修改：Zhuoliu
 * @时间：2021年3月3日
 * @修改主要内容：修改初始化初始感染者函数
 */
public class Main {

    public static void main(String[] args) {
        initHospital();
        initPanel();
        initInfected();
    }

    /**
     * 初始化画布
     */
    private static void initPanel() {
        MyPanel p = new MyPanel();
        Thread panelThread = new Thread(p);
        JFrame frame = new JFrame();
        frame.add(p);
        frame.setSize(Constants.CITY_WIDTH + hospitalWidth + 300, Constants.CITY_HEIGHT+50);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("新冠校园传播模拟");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelThread.start();//开启画布线程，即世界线程，接着看代码的下一站可以转MyPanel.java
    }

    private static int hospitalWidth;

    /**
     * 初始化医院参数
     */
    private static void initHospital() {
        hospitalWidth = Hospital.getInstance().getWidth();
    }

    /**
     * 修改初始化初始感染者
     */
    private static void initInfected() {
        List<Person> people = PersonPool.getInstance().getPersonList();//获取所有的市民
        for (int i = 0; i < Constants.ORIGINAL_COUNT; i++) {
            Person person;
            do {
                person = people.get(new Random().nextInt(people.size() - 1));//随机挑选一个市民
            } while (person.isInfected()||                                          //如果该市民已经被感染，重新挑选
                    (Constants.IS_SINGLE&&person.campus!=Constants.CAMPUS_NUMBER));//如果是单个学校爆发疫情则需为该学校人员
            person.beInfected();//让这个幸运的市民成为感染者
        }
    }

}
