"""
回顾: 方法重写
    概述:
        子类出现和父类一模一样的方法时, 称为: 方法重写.
    应用场景:
        子类需要沿袭父类的功能, 但是功能主体又有自己独有需求的时候, 就可以考虑使用方法重写了.
"""

# 需求: 定义手机类, 有打电话的功能.  定义新式手机类, 继承手机类, 重写 打电话的行为.
# 1. 定义手机类.
class Phone(object):
    # 打电话的行为
    def call(self, name):
        print(f'----- 拨号: {name} 的手机号')
        print('(拨号方)手机将 信号包发送到 (拨号方)就近的 基站')
        print('基站解析信号包, 获取目标方的位置')
        print('基站通过地下电缆将信号包 发送到 目标方就近的基站')
        print('目标方就近的基站 将信号包发送到 目标方的手机上')
        print('盲音等待, 嘟嘟嘟...')

# 2. 定义新式手机类.
class NewPhone(Phone):
    # 打电话的行为
    def call(self, name):
        # 沿袭父类的功能.
        super().call(name)
        # 功能主体又有自己独有需求的时候.
        print('新式手机的性能, 播放彩铃, 求佛...')

# main方法中完成测试.
if __name__ == '__main__':
    # 3. 创建手机类对象, 打电话.
    p = Phone()
    p.call('刘亦菲')
    print('*' * 20)

    # 4. 创建新手机类对象, 打电话.
    np = NewPhone()
    np.call('胡歌')