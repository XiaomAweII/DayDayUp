"""
案例: 演示在 类外定义 和 获取 属性.

格式:
    添加属性   对象名.属性名 = 属性值
    获取属性   对象名.属性名
"""

# 案例: 定义汽车类, 在类外给汽车对象 设置属性, 颜色=红色, 轮胎 = 4, 获取属性值并打印.


# 1. 定义汽车类.
class Car():
    # 2. 定义 run() 函数.
    def run(self):
        print('汽车会跑!')


# main函数
if __name__ == '__main__':
    # 3. 创建汽车类对象.
    c1 = Car()
    # 4. 调用 行为.
    c1.run()

    # 5. 设置属性.
    c1.color = 'Red'    # 汽车颜色: 红色
    c1.num = 4          # 汽车轮胎数: 4

    # 6. 获取属性.
    print(f'汽车颜色: {c1.color}, 轮胎数: {c1.num}')
    print('-' * 20)

    # 7. 我们再次创建1个汽车对象, 请问: 有上述的 color, num属性吗?
    c2 = Car()
    c2.run()
    print(f'汽车颜色: {c2.color}, 轮胎数: {c2.num}')  # 报错, 无该属性.
