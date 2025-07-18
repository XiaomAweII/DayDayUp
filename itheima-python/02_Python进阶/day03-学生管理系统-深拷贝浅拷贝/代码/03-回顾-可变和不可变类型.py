"""
回顾: 可变 和 不可变类型
    概述:
        可变 和 不可变类型区分依据是: 在不改变地址值的情况下, 内容是否可以发生变化, 可以: 可变类型, 不可以: 不可变类型.
    分类:
        可变类型:  列表, 字典, 集合
        不可变类型: 整型, 浮点型, 字符串, 布尔型, 元组.

深浅拷贝解释:
    概述:
        深浅拷贝一般在面试的时候出现的几率比较大, 简单来说: 深拷贝拷贝的多, 浅拷贝拷贝的少.
    细节:
        1. 深浅拷贝都可以操作 可变 和 不可变类型, 但是一般操作 可变类型. 几乎不会操作 不可变类型.
        2. 深浅拷贝其实指的就是: copy模块的两个函数.
            深拷贝: deepcopy()
            浅拷贝: copy()
        3. 浅拷贝只拷贝第1层(引用层), 不拷贝(数据层), 深拷贝: 除了拷贝引用层, 还会拷贝数据层.
"""

# 需求: 演示 可变 和 不可变类型.
# 演示 不可变类型
a = 10
print(a)                    # 10
print(f'a的地址: {id(a)}')   # 0x01
a = 20
print(a)                    # 20
print(f'a的地址: {id(a)}')   # 0x02
print('-' * 20)

# 演示可变类型
list1 = [1, 2, 3]
print(list1)                        # [1, 2, 3]
print(f'list1的地址: {id(list1)}')   # 0x03
list1[1] = 200
print(list1)                        # [1, 200, 3]
print(f'list1的地址: {id(list1)}')   # 0x03
print('-' * 20)


# python的赋值操作属于引用赋值(eg:b是a的别名, 形参是实参的别名)
def dm01_普通赋值():
    # 1 python中的赋值操作, 属于引用赋值 (把a的地址赋值给b)
    # 2 b是a的别名, b和a都指向相同的内存空间
    a = 10
    b = a
    print('id(a)-->', id(a))    # 0x01
    print('id(b)-->', id(b))    # 0x01
    print('id(10)-->', id(10))  # 0x01

    # 3 也是引用赋值 c和d指向相同的内存空间
    a = [1, 2, 3]
    b = [11, 22, 33]
    c = [a, b]
    d = c
    print('id(c)-->', id(c))    # 0x03
    print('id(d)-->', id(d))    # 0x03

    # 4 值的方式赋值 a 指向一块内存空间、b 也指向一块内存空间
    # b = a python中不支持, 这样做传参效率高

dm01_普通赋值()