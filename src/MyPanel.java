import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 主面板。
 *
 * @ClassName: MyPanel
 * @Description: 主面板
 * @author: Bruce Young
 * @date: 2020年02月02日 17:03
 * @修改：Zhuoliu
 * @时间：2021年3月3日
 * @修改主要内容：修改界面，矩形框内为一所学校，不同学校的人（小点）颜色不同
 */
public class MyPanel extends JPanel implements Runnable {

    Image image = Toolkit.getDefaultToolkit().getImage("images\\bg.png");

    public void paintImage(Image image) {
        this.image = image;
        this.repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, 700, 700, null);
    }
    public MyPanel() {
        super();
        this.setBackground(new Color(0xffffff));


    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //绘制学校边界
        //draw block
        g.setColor(new Color(0xaaaaaa));
        g.drawRect(0, 0,350, 350);
        g.drawRect(0, 350,350, 350);
        g.drawRect(350, 0,350, 350);
        g.drawRect(350, 350,350, 350);


        g.setColor(new Color(0x00ff00));//设置医院边界颜色
        //绘制医院边界
        g.drawRect(Hospital.getInstance().getX(), Hospital.getInstance().getY(),
                Hospital.getInstance().getWidth(), Hospital.getInstance().getHeight());
        g.setFont(new Font("微软雅黑", Font.BOLD, 16));
        g.setColor(new Color(0x00ff00));
        g.drawString("医院", Hospital.getInstance().getX() + Hospital.getInstance().getWidth() / 4, Hospital.getInstance().getY() - 16);
        //绘制代表人类的圆点
        List<Person> people = PersonPool.getInstance().getPersonList();
        if (people == null) {
            return;
        }
        for (Person person : people) {
            switch (person.getState()) {
                case Person.State.NORMAL: {
                    //健康人
                    if(person.left==350&&person.bottom==350)
                        g.setColor(new Color(0x386808));
                    else if(person.left==0&&person.bottom==350)
                        g.setColor(new Color(0x521212));
                    else if(person.left==350&&person.bottom==700)
                        g.setColor(new Color(0x73078D));
                    else if(person.left==0&&person.bottom==700)
                        g.setColor(new Color(0x225858));
                    break;
                }
                case Person.State.SHADOW: {
                    //潜伏期感染者
                    g.setColor(new Color(0xffee00));
                    break;
                }

                case Person.State.CONFIRMED: {
                    //确诊患者
                    g.setColor(new Color(0xff0000));
                    break;
                }
                case Person.State.FREEZE: {
                    //已隔离者
                    g.setColor(new Color(0x48FFFC));
                    break;
                }
                case Person.State.DEATH: {
                    //死亡患者

                    g.setColor(new Color(0xeeeeee));
                    break;
                }
            }
            person.update();//对各种状态的市民进行不同的处理

            g.fillOval(person.getX(), person.getY(), 5, 5);
        }

        int captionStartOffsetX = 700 + Hospital.getInstance().getWidth() + 40;
        int captionStartOffsetY = 40;
        int captionSize = 24;

        //显示数据信息
        g.setColor(new Color(0x000000));
        g.drawString("社区总人数：" + Constants.CITY_PERSON_SIZE, captionStartOffsetX, captionStartOffsetY);
        g.setColor(new Color(0x000000));
        g.drawString("健康者人数：" + PersonPool.getInstance().getPeopleSize(Person.State.NORMAL), captionStartOffsetX, captionStartOffsetY + captionSize);
        g.setColor(new Color(0xffee00));
        g.drawString("潜伏期人数：" + PersonPool.getInstance().getPeopleSize(Person.State.SHADOW), captionStartOffsetX, captionStartOffsetY + 2 * captionSize);
        g.setColor(new Color(0xff0000));
        g.drawString("发病者人数：" + PersonPool.getInstance().getPeopleSize(Person.State.CONFIRMED), captionStartOffsetX, captionStartOffsetY + 3 * captionSize);
        g.setColor(new Color(0x48FFFC));
        g.drawString("已隔离人数：" + PersonPool.getInstance().getPeopleSize(Person.State.FREEZE), captionStartOffsetX, captionStartOffsetY + 4 * captionSize);
        g.setColor(new Color(0x00ff00));
        g.drawString("空余病床：" + Math.max(Constants.BED_COUNT - PersonPool.getInstance().getPeopleSize(Person.State.FREEZE), 0), captionStartOffsetX, captionStartOffsetY + 5 * captionSize);
        g.setColor(new Color(0xE39476));
        //暂定急需病床数量为 NEED = 确诊发病者数量 - 已隔离住院数量
        //
        int needBeds = PersonPool.getInstance().getPeopleSize(Person.State.CONFIRMED)
                - PersonPool.getInstance().getPeopleSize(Person.State.FREEZE);

        g.drawString("急需病床：" + (needBeds > 0 ? needBeds : 0), captionStartOffsetX, captionStartOffsetY + 6 * captionSize);
        g.setColor(new Color(0xaaaaaa));
        g.drawString("病死人数：" + PersonPool.getInstance().getPeopleSize(Person.State.DEATH), captionStartOffsetX, captionStartOffsetY + 7 * captionSize);
        g.setColor(new Color(0x000000));
        g.drawString("世界时间（天）：" + (int) (worldTime / 10.0), captionStartOffsetX, captionStartOffsetY + 8 * captionSize);

    }


    public static int worldTime = 0;//世界时间

    public Timer timer = new Timer();

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            MyPanel.this.repaint();
            worldTime++;
        }
    }

    @Override
    public void run() {
        timer.schedule(new MyTimerTask(), 0, 100);//启动世界计时器，时间开始流动（突然脑补DIO台词：時は停た）
    }


}
