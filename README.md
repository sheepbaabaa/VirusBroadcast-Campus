研究新冠病毒在人群中传播的情况<br>
===
评价疫情防治措施（封校、戴口罩、校医院及时响应）对遏制病毒传播的作用<br>


一、仿真场景
===
基于北京市海淀区学院路的地图（左图），
选取中国农业大学、北京林业大学、中国矿业大学、北京语言大学四所高校，构建模拟仿真场景（右图）。<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/101.jpg)
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/102.jpg)<br>
在仿真场景中，四个校徽对应四所学校，移动的有色小点对应学校师生，右侧是医院，校医院响应以后会将病患收治至医院并隔离。<br>
四所学校的健康者分别为暗红、草绿、暗青、暗紫色，患者潜伏期为黄色、发病期为红色、隔离期为蓝色。<br>
封校时，师生只能在代表本校的框内活动；取消封校时，所有师生可以自由出入四所学校的区域。<br>


二、代码说明
===
本程序改造了https://github.com/KikiLetGo/VirusBroadcast<br>
将原仿真系统迁移至新的校园仿真场景中，将人员的单中心分布修改为四所学校四个中心分布，并通过规定人员移动边界实现封校与不封校的仿真模拟。<br>
修改内容为：<br>
Person.java：增加成员变量并修改构造函数；修改运动边界限制；增加病毒感染条件；增加判断是否同校的函数<br>
PersonPool.java：修改区域人群对象池构造函数，改单中心分布为四中心分布<br>
Main.java：修改初始化初始感染者函数<br>
MyPanel.java：修改界面，矩形框内为一所学校，不同学校的人（小点）颜色不同<br>
Constants.java：增加三个模拟参数，分别控制是否封校、是否为单个学校爆发疫情、单个学校爆发疫情的学校编号<br>


三、仿真结果
===
3.1仿真结果统计
---
仿真结果|疫情爆发地|是否封校|是否佩戴口罩|校医院响应是否及时|累计感染病例（人）|新增病例清零所需时间（天）
---|---------|--------|-----------|----------------|-----------------|----------------------
场景一 |中国农业大学 |否 |否 |及时 |360 |50
场景二 |中国农业大学 |是 |否 |及时 |176 |24
场景三 |四所大学 |否 |否 |及时 |400 |54
场景四 |四所大学 |是 |否 |及时 |347 |31
场景五 |四所大学  |是 |否 |不及时 |603 |56
场景六 |四所大学 |是 |是 |及时 |255 |40

>注：假设佩戴口罩时疫情传播率为50%，不佩戴口罩时疫情传播率为90%；<br>
>假设校医院及时响应时发病者需要一天时间被发现，校医院不及时响应发病者则需要三天时间被发现；<br>

3.2仿真详情
---
场景一：<br>
单个学校爆发疫情 | 不封校 | 不戴口罩 | 医院响应及时 | 2000人中有20位初始感染者<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/311.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/312.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/313.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/314.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/315.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/316.jpg)<br>
场景二：<br>
单个学校爆发疫情 | 封校 | 不戴口罩 | 医院响应及时 | 2000人中有20位初始感染者<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/321.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/322.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/323.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/324.jpg)<br>
场景三：<br>
四所学校爆发疫情 | 不封校 | 不戴口罩 | 医院响应及时 | 2000人中有20位初始感染者<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/331.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/332.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/333.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/334.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/335.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/336.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/337.jpg)<br>
场景四：<br>
四所学校爆发疫情 | 封校 | 不戴口罩 | 医院响应及时 | 2000人中有20位初始感染者<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/341.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/342.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/343.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/344.jpg)<br>
场景五：<br>
四所学校爆发疫情 | 封校 | 不戴口罩 | 医院响应不及时 | 2000人中有20位初始感染者<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/351.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/352.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/353.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/354.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/355.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/356.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/357.jpg)<br>
场景六：<br>
四所学校爆发疫情 | 封校 | 戴口罩 | 医院响应及时 | 2000人中有20位初始感染者<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/361.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/362.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/363.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/364.jpg)<br>
![image](https://github.com/sheepbaabaa/VirusBroadcast-Campus/raw/main/readme_pic/365.jpg)<br>

3.3仿真结果分析
---
①其他条件相同的情况下，单个学校爆发疫情比四所学校同时爆发疫情更好控制，累计感染病例更少，零新增所需时间略短。<br>
②其他条件相同的情况下，施行校园封闭式管理可以更好地控制疫情。尤其在单个学校爆发疫情的情况下，累计感染病例和零新增所需时间均可缩短一半。<br>
③其他条件相同的情况下，校医院及时检测师生身体异常并响应可以更好地控制疫情。<br>
④其他条件相同的情况下，师生主动佩戴口罩可以更好地控制疫情。<br>


四、防疫建议
===
1.高校应坚持校园封闭式管理，鼓励师生非必要不出校。<br>
2.高校应在各个区域的进出口设置红外体温检测仪，对体温异常，有发热咳嗽等情况的师生应及时隔离。师生发现自己或他人身体异常应及时向校医院报告。<br>
3.高校应要求师生在公共场所均佩戴口罩，保持安全距离，错峰就餐，做好个人的防护。<br>

