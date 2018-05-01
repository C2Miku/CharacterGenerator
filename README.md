# CharacterGenerator
图片灰度转文字转换器
## 简介
将彩色图片转换为灰度图片，然后计算每个分区块的平均灰度，用设置好的不同灰度等级的字符输出。支持直接保存到本地。
## 使用方法
**直接将```CharacterGenerator/src/CharacterGenerator.java```下载下来用IDE打开即可。我使用的IDE是IntelliJ IDEA CE 2018。**

如果需要，根据自己的情况修改以下常量：
```
GAP             //分区块大小（即计算密度，越小显示越精确）
THROW_GAP       //舍弃的纵向分区块间隔（根据字体胖瘦调整，Consolas字体可使用默认值）
GRAY_LEVEL_0    //灰度等级0对应的字符
GRAY_LEVEL_1    //灰度等级1对应的字符
GRAY_LEVEL_2    //灰度等级2对应的字符
GRAY_LEVEL_3    //灰度等级3对应的字符
GRAY_LEVEL_4    //灰度等级4对应的字符
GRAY_LEVEL_5    //灰度等级5对应的字符
IF_SAVE         //是否保存，默认为true
SAVE_PATH       //保存路径，默认为D:\
```
