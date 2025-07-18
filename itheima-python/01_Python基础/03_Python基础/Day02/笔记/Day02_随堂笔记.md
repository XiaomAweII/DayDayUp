#### 今日内容大纲介绍

* 流程控制语句
  * 顺序结构(默认结构)
  * 分支结构
  * 循环结构
* 分支结构
  * if单分支
  * if双分支
  * if多分支
  * 分支嵌套
* 循环结构
  * for循环
  * while循环
  * 控制跳转语句
    * break
    * continue
  * 循环嵌套

---

#### 1.顺序结构

```python
"""
流程控制语句介绍:
    概述:
        它就是用来控制Python代码执行的, 先执行谁, 后执行谁, 不执行谁, 重复执行谁等...
    分类:
        顺序结构: 默认结构.
            即: 代码会按照 从上往下, 从左往右, 依次逐行 执行.
        分支结构:
            当条件满足的时候, 才会执行的内容, 且只执行一次.
        循环结构:
            当条件满足的时候, 才会执行的内容, 且会重复执行.
"""

# 演示: 顺序结构
print('----- start -----')
print('hello 1' + 'aa' + '5' + '10')
print('hello2')
print('hello3')
print('----- end -----')
```



#### 2.if-单分支

```python
"""
分支结构介绍:
    概述:
        在条件满足的情况下, 才会被执行的内容, 且只执行一次, 就是: 分支结构.
        分支结构特指的是: if语句.

    if 单分支格式如下:
        if 判断条件:
            语句体

    执行流程:
        1. 先执行判断条件, 看其是否成立.
        2. 如果判断条件成立, 则执行 对应的语句体, 然后if语句执行结果.
        3. 如果判断条件不成立, 则什么都不做.

    细节:
        1. if语句的语句体 较之于 if语句, 是有缩进的(默认是4个空格)
        2. if语句只能控制它的语句体, 外界语句, if是无法控制的.
"""

# 案例1: if格式演示.
a = 10
# if a > 3:
if a < 5:
    print('if 语句体1')
    print('if 语句体2')

print('因为我和if是对齐的, 所以我们是平级, 无论if判断条件是否成立, 我都会执行!')
print('-' * 28)

# 案例2: 上网案例, 键盘录入年龄并接收, 判断是否可以上网. 即: 年龄大于或者等于18岁, 可以上网.
# 1. 键盘录入年龄并接收.
# age_str = input('请录入您的年龄: ')
# 2. 把上述的年龄转成 => int类型
# age = eval(age_str)     # '20' -> 20

# 合并版写法.
# age = eval(input('请录入您的年龄: '))
age = int(input('请录入您的年龄: '))

# 3. 判断是否可以上网.
if age >= 18:
    # 4. 如果可以上网, 则: 打印提示信息.
    print('哥已成年, 可以上网了!')


print('----- end -----')
```



#### 3.比较运算符和逻辑运算符

```python
"""
比较运算符:
    概述:
        就是用来做 比较操作的, 也称之为: 关系运算符.
        无论 比较表达式简单还是复杂, 其结果必定是: 布尔值, 要么True, 要么False.
    分类:
        >, >=, <, <=, ==, !=
    细节:
        不要把 == 写成 =, 一个=是赋值, 两个=是比较.


逻辑运算符:
    概述:
        它适用于 多条件的判断, 即: 同时满足多个条件, 还是满足多个条件的任意1个条件, 还是取反等操作.
    分类:
        and     逻辑与, 并且的意思, 即: 有False则整体为False.
        or      逻辑或, 或者的意思, 即: 有True则整体为True.
        not     逻辑非, 取反的意思, 即: False -> 取反后, True,  True -> 取反后, False
    细节:
        1. 逻辑运算符主要操作的是 关系表达式, 但是它也可以操作数字, 例如: 10 and 5
        2. 逻辑运算符操作数字的小技巧: 你把0当做False, 非0当做True即可.
"""

# 案例1: 演示比较运算符.
a, b = 10, 3
print(a > b)    # True
print(a >= b)   # True
print(a < b)    # False
print(a <= b)   # False
print(a == b)   # False
print(a != b)   # True
print(5 >= 5)   # True
print('-' * 28)

# 案例2: 演示 逻辑运算符.
print(a > 5 and b <= 3)     # True and True => True

# 演示 and     逻辑与, 并且的意思, 即: 有False则整体为False.
print(True and True)    # True
print(True and False)   # False
print(False and True)   # False
print(False and False)  # False
print('-' * 28)

# 演示 or      逻辑或, 或者的意思, 即: 有True则整体为True.
print(True or True)    # True
print(True or False)   # True
print(False or True)   # True
print(False or False)  # False
print('-' * 28)

# 演示 not     逻辑非, 取反的意思, 即: False -> 取反后, True,  True -> 取反后, False
print(not True)         # False
print(not False)        # True
print(not not False)    # False, 同一个值, 偶数次取反, 该数字值不变.
print('-' * 28)

# 扩展: 逻辑运算符操作数字, 小技巧: 你把0当做False, 非0当做True即可.
# 逻辑与操作数字, 结论(技巧): 有0则0(即: 有False则整体为False), 否则取最后1个(非0)数字.
print(10 and 0 and 5)   # 0
print(0 and 3 and 5)    # 0
print(10 and 3 and 5)   # 5
print(0 and 0 and 0)    # 0
print('-' * 28)

# 逻辑或操作数字, 结论(技巧): 有非0则非0(即: 有True则整体为True), 否则取第1个(非0)数字.
print(0 or 0 or 0)    # 0
print(10 or 0 or 5)   # 10
print(0 or 3 or 5)    # 3
print(10 or 3 or 5)   # 10
print('-' * 28)
```



#### 4.if-双分支

```python
"""
if双分支介绍:
    概述:
        它属于 分支结构 if语句的一种写法, 主要用于 两种情况的判断, 即: 要么A, 否则B.
    格式:
        if 判断条件:
            语句体1
        else:
            语句体2
    执行流程:
        1. 先执行判断条件, 看其是否成立.
        2. 如果成立(True), 则执行: 语句体1, 然后整个if语句结束.
        3. 如果不成立(False), 则执行: 语句体2, 然后整个if语句结束.

    记忆(背诵, 重点):
        else默认有 if条件的 反条件.
"""

# 需求: 键盘录入身高(cm), 如果没有超过150cm, 进动物园不用买票, 否则要买票.
# 1. 键盘录入身高, 并接收, 记得转成: int.
height = int(input('请录入您的身高: '))

# 2. 判断是否 超过 150cm, 如果没有超过, 无需买票.
if height <= 150:
    print('可以进动物园了, 无需买票')
else:
    # 3. 否则, 需要买票.
    print('您太高了, 需要买票才能进入动物园!')

```



#### 5.if-多分支

```python
"""
if多分支介绍:
    概述:
        它是分支结构if语句的一种写法, 适用于 多情况 的判断.
    格式:
        if 条件1:
            语句体1
        elif 条件2:
            语句体2
        elif 条件3:
            语句体3
        ......
        else:
            语句体n
    执行流程:
        1. 先执行条件1, 看其是否成立, 成立则执行语句体1, 然后整个if语句结束.
        2. 如果条件1不成立, 则执行条件2, 看其是否成立, 成立则执行语句体2, 然后整个if语句结束.
        3. 如果条件2不成立, 则执行条件3, 看其是否成立, 成立则执行语句体3, 然后整个if语句结束.
        4. 重复上述的步骤, 如果所有的判断条件都不成立, 则执行 最后1个else的 语句体n, 至此, if语句结束.

    细节:
        1. 根据需求 elif 可以写多个.
        2. 最后的else语句可以省略不写, 但是它代表着所有条件都不成立的情况, 所以建议加上.
        3. 以后测试数据的时候, 建议测试三种值:
            和法值.
            边界值.
            非法值.
"""

# 需求: 根据考试成立, 发放奖励.
"""
       成绩               奖励
    90 ~ 100        奖励: 游乐场套票一张
    80 ~ 89         奖励: 游乐场半天游
    70 ~ 79         奖励: 博物馆全天游
    60 ~ 69         奖励: 全套练习题
    0 ~ 59          奖励: 口头激励
"""

# 1. 提示用户录入他/她的考试成绩, 并接收, 记得转成: 整数.
score = eval(input('请录入您的考试成绩: '))

# 2. 根据考试成立发放奖励.
# 写法1: 普通版.
if score >= 90 and score <= 100:
    print('奖励: 游乐场套票一张')
elif score >= 80 and score < 90:
    print('奖励: 游乐场半天游')
elif score >= 70 and score < 80:
    print('奖励: 博物馆全天游')
elif score >= 60 and score < 70:
    print('奖励: 全套练习题')
else:
    print('奖励: 口头激励')

print('-' * 28)

# 写法2: Python独有版.
if 90 <= score <= 100:
    print('奖励: 游乐场套票一张')
elif 80 <= score < 90:
    print('奖励: 游乐场半天游')
elif 70 <= score < 80:
    print('奖励: 博物馆全天游')
elif 60 <= score < 70:
    print('奖励: 全套练习题')
elif 00 <= score < 60:
    print('奖励: 口头激励')
else:
    print('成绩无效, 男女混打 + 扫帚棒法!')

print('-' * 28)


# 写法3: 实际开发版, 能看懂即可.
if score < 0 or score > 100:                # 非法值校验
    print('成绩无效, 男女混打 + 扫帚棒法!')
elif score >= 90:                       # else的默认条件: score >= 0 and score <= 100
    print('奖励: 游乐场套票一张')
elif score >= 80:                       # else的默认条件: score >= 0 and score < 90
    print('奖励: 游乐场半天游')
elif score >= 70:                       # else的默认条件: score >= 0 and score < 80
    print('奖励: 博物馆全天游')
elif score >= 60:                       # else的默认条件: score >= 0 and score < 70
    print('奖励: 全套练习题')
else:                                   # else的默认条件: score >= 0 and score < 60
    print('奖励: 口头激励')

```



#### 6.if-嵌套

```python
"""
if嵌套解释:
    概述:
        它指的是 1个if语句的 语句体 是 另1个if语句.
    格式:
        if 条件1:
            语句体1

            if 条件:
                语句体
            ......

        else:
            语句体2

            if 条件:
                语句体
            ......
"""

# 需求: 坐公交车, 有座位可以坐下.
# 要求:  输入公交卡当前的余额, 只要够2元, 就可以上公交车. 如果车上有空座位, 就可以坐下.

# 1. 提示用户录入公交卡的余额, 并接收, 记得转成: 整数.
money = eval(input('请录入公交卡的余额: '))

# 2. 判断公交卡的余额是否 够2元, 如果够: 则可以上车.
if money >= 2:
    print('滴, 刷卡成功, 请上车!')
    # 4. 提示用户录入空座位的情况, 并接收.
    seat = int(input('请录入空座位数量: '))
    # 5. 判断是否有空座位, 如果有, 则可以坐下.
    if seat > 0:
        print('有空座位, 可以坐下了!')
    else:
        # 6. 如果没有, 则: 需要站着.
        print('哎呀, 人太多了, 没位置了, 站一会儿吧!')

# 3. 如果不够, 则不允许上车.
else:
    print('滴, 余额不足, 请充值, 本次无法乘车, 可以跟车跑!')
```



#### 7.分支案例-猜拳游戏

```python
# 案例: 猜拳游戏.
# 需求: 键盘录入玩家出的手势, 且和 电脑人的手势(随机生成), 进行比较, 打印结果.


# 铺垫知识, 如何获取一个指定范围内的随机数.
# 1. 导包, 即: 通过第三方的包来实现.
import random

# 2. 通过 random.randint(a, b) 函数实现 获取随机数.
print(random.randint(1, 3))     # 包左包右, 生成 1 ~ 3之间的随机数.
print('-' * 28)

# 具体的完成 猜拳游戏案例, 规则: 石头 -> 1, 剪刀 -> 2, 布 -> 3.
# 1. 键盘录入玩家的手势编号, 并接收. 记得转成数值.
player = int(input('请输入您的手势编号, 规则: 石头(1), 剪刀(2), 布(3)  => '))

# 2. 随机生成 电脑人的 手势编号.
pc = random.randint(1, 3)
# print(f'电脑人的手势编号为: {pc}')

# 3. 比较 玩家 和 电脑人的手势编号, 并打印结果.
# 情况1: 玩家胜利.   玩家:石头, 电脑: 剪刀,    玩家:剪刀, 电脑:布,    玩家: 布, 电脑: 石头
if (player == 1 and pc == 2) or (player == 2 and pc == 3) or (player==3 and pc == 1):
    print('玩家 获得胜利')
elif pc == player:
    # 情况2: 平局.
    print('哎呀, 平局了!')
elif (player == 1 and pc == 3) or (player == 2 and pc == 1) or (player==3 and pc == 2):
    # 情况3: 电脑人 胜利
    print('电脑人 获得胜利!')
else:
    print('手势编号有误, 该轮无成绩!')

```

#### 8.扩展-三元表达式

```python
"""
三元(三目)表达式介绍:
    概述:
        它是一种特殊写法, 可以用来优化 if.else 的操作.
    格式:
        值1 if 判断条件 else 值2
    执行流程:
        1. 先执行判断条件, 看其结果是否成立.
        2. 成立就返回 值1
        3. 不成立就返回 值2
"""

# 需求1: 获取两个整数的最大值.
a, b = 10, 31

# 方式1: if.else语法
if a >= b:
    max = a
else:
    max = b
print(f'最大值为: {max}')


# 方式2: 三元表达式.
max2 = a if a > b else b

print(f'最大值为: {max2}')


# 扩展: 三元表达式嵌套.
# 需求: 求3个整数的最大值.
c, d, e = 10, 20, 15

# 场景1: if.else实现
if c >= d:
    # c大, 比较 c 和 e
    if c >= e:
        max = c
    else:
        max = e
else:
    # d大, 比较 d 和 e
    if d >= e:
        max = d
    else:
        max = e
print(f'最大值为: {max}')

# 场景2: 用三元搞定.
# max2 = c大 if c >= d else d大

#          比较 C 和 E          比较 C 和 D       比较 D 和 E
# max2 = (c if c >= e else e) if c >= d else (d if d >= e else e)      # 小括号可以省略.
max2 = c if c >= e else e if c >= d else d if d >= e else e      # 小括号可以省略.
print(f'最大值为: {max2}')

```

#### 9.扩展-Debug断点调试

```python
"""
Debug断点调试:
    概述:
        它不仅可以帮助我们 排查程序的错误,  还可以辅助我们快速上手, 了解项目业务逻辑.
    使用步骤:
        1. 加断点.
            原则: 哪里不会点哪里.
        2. 启动调试.
            右键 => Debug as ...
        3. 看哪里.
            下左: MainThread,  可以查看程序当前执行到哪里了.
            下中: Variables,  可以查看变量的变化过程.
            下右: Console,    可以查看程序的执行结果.
        4. 如何进行下一步调试.
            F7: 逐过程调试, 即: 遇到我们自己写的函数, 会跳转到函数内部.
            F8: 逐行调试, 即: 即使遇到我们自己写的函数, 也只是当做一行代码来处理, 不会进入到函数内部...
            F9: 逐断点调试, 即: 直接跳转到下一个断点. 如果当前断点已经是最后1个断点, 再次按下F9, 会一次性执行完后续所有的代码.
        5. 如何结束调试.
            场景1: 调试完.
            场景2: 手动终止.
            场景3: 最后1个断点的情况, 直接按F9
        6. 如何删除断点.
            场景1: 再次点击即可.
            场景2: 点击 break points 图表, 移除断点.
"""
# 需求: 求3个整数的最大值.
# c, d, e = 10, 20, 15

c = 10
d = 20
e = 15

# 场景1: if.else实现
if c >= d:
    # c大, 比较 c 和 e
    if c >= e:
        max = c
    else:
        max = e
else:
    # d大, 比较 d 和 e
    if d >= e:
        max = d
    else:
        max = e
print(f'最大值为: {max}')
print('hello 1')
print('hello 2')
print('hello 3')
print('hello 4')
print('hello 5')
```

#### 10.while循环入门

```python
"""
循环结构介绍:
    概述:
        它属于流程控制语句的一种, 表示: 周而复始, 即: 满足某些条件的情况下, 重复的做某些事儿.
    分类:
        while循环         更适用于 循环次数不固定的情况.
        for循环           更适用于 循环次数固定的情况.
    循环的要素:
        1. 初始化条件.    表示 循环从哪里开始执行.
        2. 判断条件.      表示 循环什么时候终止, 只要判断条件满足, 循环就一直执行.
        3. 循环体.       表示 判断条件成立的情况下, 重复做的事儿.
        4. 控制条件.     表示 控制初始化条件变化的.

    while循环详解:
        格式:
            初始化条件1
            while 判断条件2:
                循环体3
                控制条件4

        执行流程:
            第1次循环:   1, 2, 3, 4           假设: 判断条件成立.
            重复循环:    2, 3, 4              假设: 判断条件成立.
            最后1次循环: 2                    判断条件不成立, 循环结束.
"""

# 需求: 打印 5次 HellWorld.
i = 1                                           # 初始化条件, 表示循环从 1 开始
while i <= 5:       # i的值: 1, 2, 3, 4, 5       # 判断条件, 成立则循环继续向下执行.
    # 走这里, 说明 判断条件成立, 执行循环体.
    print(f'Hello World! {i}')                  # 循环体, 表示判断条件满足的情况下, 重复的做某些事儿.

    # 循环执行一次, i 就 + 1
    # i = i + 1
    i += 1                                      # 控制条件, 控制变量(初始化条件)变化的.

```

#### 11.while循环-求和思想

```python
"""
需求1: while循环 计算 1 ~ 100之间的数字和.  5050
需求2: while循环 计算 1 ~ 100之间的偶数和.  2550        奇数和: 2500

循环求和思路, 细节如下:
    1. 求和变量 要定义到循环的 外边.
    2. 循环内部是具体的 计算过程.
    3. 计算完毕后, 在循环外打印结果即可
"""

# 需求1: while循环 计算 1 ~ 100之间的数字和.
# 1. 定义求和变量sum, 记录数字的累加和.
sum = 0

# 2. 定义变量i, 表示 while循环的 初始化条件, 且 i是从1开始获取的.
i = 1

# 3. 通过 while循环, 获取到 1 ~ 100之间所有的数字.
while i <= 100:     # i的值: 1, 2, 3, 4, 5......
    # 4. 具体的累加(求和)动作, 将获取到的数字 累加给 sum
    # print(i)
    sum = sum + i

    # 5. 每次操作完毕后, 记得 修改初始化条件的值, 即: 这步是 控制条件.
    i += 1

# 6. 循环执行结束后, 打印结果即可.
print(f'1 ~ 100之间的数字和为: {sum}')
print('-' * 28)



# 需求2: while循环 计算 1 ~ 100之间的偶数和.
# 思路1: 加入if判断.
# 1. 定义求和变量sum, 记录数字的累加和.
sum = 0
# 2. 定义变量i, 表示 while循环的 初始化条件, 且 i是从1开始获取的.
i = 1

# 3. 通过 while循环, 获取到 1 ~ 100之间所有的数字.
while i <= 100:     # i的值: 1, 2, 3, 4, 5......
    # 4. 具体的累加(求和)动作, 判断是否是偶数, 如果是, 将获取到的数字 累加给 sum
    if i % 2 == 0:
        sum = sum + i

    # 5. 每次操作完毕后, 记得 修改初始化条件的值, 即: 这步是 控制条件.
    i += 1

# 6. 循环执行结束后, 打印结果即可.
print(f'1 ~ 100之间的数字和为: {sum}')



# 思路2: 初始化值从0开始数, 每次加 += 2
# 1. 定义求和变量sum, 记录数字的累加和.
sum = 0
# 2. 定义变量i, 表示 while循环的 初始化条件, 且 i是从0开始获取的.
i = 2
# 3. 通过 while循环, 获取到 1 ~ 100之间所有的数字.
while i <= 100:     # i的值: 1, 2, 3, 4, 5......
    # 4. 具体的累加(求和)动作.
    sum = sum + i

    # 5. 每次操作完毕后, 记得 修改初始化条件的值, 即: 用于更新 判断条件的结果.
    i += 2

# 6. 循环执行结束后, 打印结果即可.
print(f'1 ~ 100之间的数字和为: {sum}')

```

#### 12.while循环-统计思想

```python
"""
关于循环, 另一种比较重要的就是 统计思想了, 例如: 统计水仙花数的个数.

需求1: 打印所有的水仙花数.
需求2: 统计水仙花数的个数.

水仙花数解释:
    1. 它是1个三位数.
    2. 它的各个位数的立方和相加 = 它本身.
    3. 例如: 153 = 1*1*1 + 5*5*5 + 3*3*3 = 153, 所以 153就是水仙花数.
"""

# 需求1: 打印所有的水仙花数
# 1. 定义初始化值, 从 100 开始.
i = 100
# 2. while循环, 获取到 100 ~ 1000之间所有的值, 包左不包右.
while i < 1000:     # 假设:  i = 2153
    # 3. 获取该数字的各个位数 数字.
    ge = i  // 1  % 10
    shi = i // 10 % 10
    bai = i // 100 % 10

    # 4. 判断是否是水仙花数, 如果是, 就打印.
    if ge * ge * ge + shi * shi * shi + bai * bai * bai == i:
        # 走这里, 是水仙花数.
        print(i)

    # 5. 控制条件, 记得: i += 1
    i += 1
print('-' * 28)


# 需求2: 统计水仙花数的个数.

# 定义求和变量count, 用于记录 水仙花数的个数.
count = 0

# 1. 定义初始化值, 从 100 开始.
i = 100
# 2. while循环, 获取到 100 ~ 1000之间所有的值, 包左不包右.
while i < 1000:     # 假设:  i = 2153
    # 3. 获取该数字的各个位数 数字.
    ge = i  // 1  % 10
    shi = i // 10 % 10
    bai = i // 100 % 10

    # 4. 判断是否是水仙花数, 如果是, 就打印.
    if ge * ge * ge + shi * shi * shi + bai * bai * bai == i:
        # 走这里, 是水仙花数. 计数器 + 1
        count += 1

    # 5. 控制条件, 记得: i += 1
    i += 1
# 6. 循环结束后, 打印程序的运行结果即可.
print(f'水仙花数的个数为: {count}')
```

#### 13.循环嵌套-打印矩形

```python
"""
循环嵌套解释:
    概述:
        循环嵌套指的是 1个循环的 循环体 是另1个循环语句.
    特点:
        外循环执行1次, 内循环执行一圈(一轮).
    案例:
        1. 打印矩形
            *****
            *****
            *****
            *****
            *****
        2. 打印正三角.
        3. 打印倒三角
        4. 打印99乘法表.
"""

# 需求: 打印5行5列的矩形, 要求一次打印 1个*
# 方式1: 分解版.
# 打印第1行
j = 1
while j <= 5:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.

# 打印第2行.
j = 1
while j <= 5:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.

# 打印第3行.
j = 1
while j <= 5:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.

# 打印第4行.
j = 1
while j <= 5:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.


# 打印第5行.
j = 1
while j <= 5:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.


print('-' * 28)


# 方式2: 合并版.
i = 1
while i <= 5:       # i的值: 1, 2, 3, 4, 5   外循环控制 行数.
    # 循环体, 就是具体的 打印每一行的操作.
    j = 1
    while j <= 5:   # j的值: 1, 2, 3, 4, 5  内循环控制 每行的列数
        print('*', end='')
        j += 1
    print()  # 核心代码, 打印完一行之后, 记得换行.

    i += 1       # 控制条件
```

#### 14.循环嵌套-打印正三角

```python
"""
循环嵌套解释:
    概述:
        循环嵌套指的是 1个循环的 循环体 是另1个循环语句.
    特点:
        外循环执行1次, 内循环执行一圈(一轮).
    案例:
        1. 打印矩形
        2. 打印正三角.
            *
            **
            ***
            ****
            *****
        3. 打印倒三角
        4. 打印99乘法表.

    记忆:
        打印正三角和打印矩形相比, 内循环的 判断条件 发生改变.
        打印倒三角和打印矩形相比, 内循环的 ** 发生改变.
"""

# 需求: 打印5行5列的矩形, 要求一次打印 1个*
# 方式1: 分解版.
# 打印第1行
j = 1
while j <= 1:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.


# 打印第2行.
j = 1
while j <= 2:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.

# 打印第3行.
j = 1
while j <= 3:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.

# 打印第4行.
j = 1
while j <= 4:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.


# 打印第5行.
j = 1
while j <= 5:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.


print('-' * 28)


# 方式2: 合并版.
i = 1
while i <= 5:       # i的值: 1,        2,      3,      4,         5   外循环控制 行数.
    # 循环体, 就是具体的 打印每一行的操作.
    j = 1
    while j <= i:   # j的值: 1,        1,2,    1,2,3,  1,2,3,4,   1,2,3,4,5  内循环控制 每行的列数
        print('*', end='')
        j += 1
    print()  # 核心代码, 打印完一行之后, 记得换行.

    i += 1       # 控制条件
```

#### 15.循环嵌套-打印倒三角

```python
"""
循环嵌套解释:
    概述:
        循环嵌套指的是 1个循环的 循环体 是另1个循环语句.
    特点:
        外循环执行1次, 内循环执行一圈(一轮).
    案例:
        1. 打印矩形
        2. 打印正三角.
        3. 打印倒三角
            *****
            ****
            ***
            **
            *
        4. 打印99乘法表.
    记忆:
        打印正三角和打印矩形相比, 内循环的 判断条件 发生改变.
        打印倒三角和打印矩形相比, 内循环的 初始化条件 发生改变.
"""

# 需求: 打印5行5列的矩形, 要求一次打印 1个*
# 方式1: 分解版.
# 打印第1行
j = 1
while j <= 5:
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.

# 打印第2行.
j = 2
while j <= 5:           # 2, 3, 4, 5
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.

# 打印第3行.
j = 3
while j <= 5:           # 3, 4, 5
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.

# 打印第4行.
j = 4
while j <= 5:           # 4, 5
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.


# 打印第5行.
j = 5
while j <= 5:           # 5
    print('*', end='')
    j += 1
print()     # 核心代码, 打印完一行之后, 记得换行.


print('-' * 28)


# 方式2: 合并版.
i = 1
while i <= 5:       # i的值: 1,           2,          3,      4,      5   外循环控制 行数.
    # 循环体, 就是具体的 打印每一行的操作.
    j = i
    while j <= 5:   # j的值: 1,2,3,4,5    2,3,4,5     3,4,5   4,5     5 内循环控制 每行的列数
        print('*', end='')
        j += 1
    print()  # 核心代码, 打印完一行之后, 记得换行.

    i += 1       # 控制条件
```

#### 16.循环嵌套-99乘法表

```python
"""
需求: 打印99乘法表, 具体如下:
    1 * 1 = 1
    1 * 2 = 2   2 * 2 = 4
    1 * 3 = 3   2 * 3 = 6   3 * 3 = 9
    ......

思路: 学会拆解.
    1. 打印9行9列的矩形 *
    2. 把上述的图形改为 正三角形.
    3. 用 1 * 3 = 3 的格式, 来替换上述的 *
    4. 用 i 和 j 这两个变量, 来填充 乘法公式, 即: 1 * 3 = 3    =>  {j} * {i} = {i * j}
"""

# Step1: 打印9行9列的矩形 *
# i = 1
# while i <= 9:       # 外循环控制 行数
#
#     j = 1
#     while j <= 9:   # 内循环控制: 每行的列数
#         print('*', end='')
#         j += 1  # 内循环的 控制条件.
#     print()     # 核心代码, 换行.
#
#     i += 1  # 外循环的 控制条件.


# Step2: 把上述的图形改为 正三角形.   修改内循环的 判断条件.
# i = 1
# while i <= 9:       # 外循环控制 行数
#
#     j = 1
#     while j <= i:   # 内循环控制: 每行的列数
#         print('*', end='\t')
#         j += 1  # 内循环的 控制条件.
#     print()     # 核心代码, 换行.
#
#     i += 1  # 外循环的 控制条件.


# Step3: 用 1 * 3 = 3 的格式, 来替换上述的 *
# i = 1
# while i <= 9:       # 外循环控制 行数
#
#     j = 1
#     while j <= i:   # 内循环控制: 每行的列数
#         print('1 * 3 = 3', end='\t')
#         j += 1  # 内循环的 控制条件.
#     print()     # 核心代码, 换行.
#
#     i += 1  # 外循环的 控制条件.


# Step4: 用 i 和 j 这两个变量, 来填充 乘法公式, 即: 1 * 3 = 3    =>  {j} * {i} = {i * j}
i = 1
while i <= 9:       # 外循环控制 行数        假设 i = 3

    j = 1
    while j <= i:   # 内循环控制: 每行的列数  此时 j = 1, 2, 3
        print(f'{j} * {i} = {i * j}', end='\t')
        j += 1  # 内循环的 控制条件.
    print()     # 核心代码, 换行.

    i += 1  # 外循环的 控制条件.

```

