/**
 * 模拟参数
 *
 * @ClassName: Constants
 * @Description: 模拟参数
 * @author: Bruce Young
 * @date: 2020年02月02日 21:40
 * @修改：Zhuoliu
 * @时间：2021年3月3日
 * @修改主要内容：增加三个模拟参数
 */
public class Constants {

    public static int ORIGINAL_COUNT = 20;//初始感染数量
    public static float BROAD_RATE = 0.5f;//传播率
    public static float SHADOW_TIME = 140;//潜伏时间，14天为140
    public static int HOSPITAL_RECEIVE_TIME = 10;//医院收治响应时间
    public static int BED_COUNT = 1000;//医院床位
    /**
     * 流动意向平均值，建议调整范围：[-0.99,0.99]
     * <p>
     * -0.99 人群流动最慢速率，甚至完全控制疫情传播
     * 0.99为人群流动最快速率, 可导致全城感染
     */
    public static float u = 0.99f;
    public static int CITY_PERSON_SIZE = 2000;//城市总人口数量
    public static float FATALITY_RATE = 0.02f;//fatality_rate病死率，根据2月6日数据估算（病死数/确诊数）为0.02
    public static int DIE_TIME = 100;//死亡时间均值，30天，从发病（确诊）时开始计时
    public static double DIE_VARIANCE = 1;//死亡时间方差
    /**
     * 城市大小即窗口边界，限制不允许出城
     */
    public static final int CITY_WIDTH = 700;
    public static final int CITY_HEIGHT = 700;

    /**
     * 增加三个模拟参数
     */
    public static final boolean CLOSE_DOOR = true;//是否封校
    public static final boolean IS_SINGLE = false;//是否为单个学校爆发疫情
    public static final int CAMPUS_NUMBER = 1;//若为单个学校爆发疫情，该学校的代码{0,1,2,3}

}
