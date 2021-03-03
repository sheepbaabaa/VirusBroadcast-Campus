import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 区域人群对象池
 *
 * @ClassName: PersonPool
 * @Description: 区域人群对象池，该地区假设为一个近似封闭的环境，拥有几乎不变的民众数量
 * @author: Bruce Young
 * @date: 2020年02月02日 17:21
 * @修改：Zhuoliu
 * @时间：2021年3月3日
 * @修改主要内容：修改区域人群对象池构造函数，改单中心分布为四中心分布
 */
public class PersonPool {
    private static PersonPool personPool = new PersonPool();

    public static PersonPool getInstance() {
        return personPool;
    }

    List<Person> personList = new ArrayList<Person>();

    public List<Person> getPersonList() {
        return personList;
    }


    /**
     * @param state 市民类型 Person.State的值，若为-1则返回当前总数目
     * @return 获取指定人群数量
     */
    public int getPeopleSize(int state) {
        if (state == -1) {
            return personList.size();
        }
        int i = 0;
        for (Person person : personList) {
            if (person.getState() == state) {
                i++;
            }
        }
        return i;
    }
    /**
     * 修改函数
     */
    private PersonPool() {
        City city = new City(350, 350);//设置城市中心为坐标(350，350)
        //添加城市居民
        for (int i = 0,k = 0; i < Constants.CITY_PERSON_SIZE; i++) {
            Random random = new Random();
            //产生N(a,b)的数：Math.sqrt(b)*random.nextGaussian()+a
            int x, y,centerx=350,centery=350;
            int campus=k;//k为0.1.2.3，对应即为学校编号
            int left=0,right=700,top=0,bottom=700;//人员运动边界
            switch (k){//根据k值确定人员的坐标中心（四个学校，对应四个人员分布中心）和运动边界
                case 0:
                    centerx=250;centery=250;
                    left=0;right=350;top=0;bottom=350;
                    k++;
                    break;
                case 1:
                    centerx=450;centery=250;
                    left=350;right=700;top=0;bottom=350;
                    k++;
                    break;
                case 2:
                    centerx=250;centery=450;
                    left=0;right=350;top=350;bottom=700;
                    k++;
                    break;
                case 3:
                    centerx=450;centery=450;
                    left=350;right=700;top=350;bottom=700;
                    k=0;
                    break;
            }
            //人员坐标遵循以（centerx，centery）为中心的正态分布
            x = (int) (50 * random.nextGaussian() + centerx);
            y = (int) (50 * random.nextGaussian() + centery);
            //越界修改
            if (x > 700) x = 700;
            if (x < 0)  x = 0;
            if (y > 700) y = 700;
            if (y < 0) y = 0;
            //添加人员
            personList.add(new Person(city, x, y, top,bottom,left,right,campus));

        }
    }
}
