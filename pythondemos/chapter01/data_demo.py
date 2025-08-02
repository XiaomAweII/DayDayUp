import pandas as pd
import matplotlib.pyplot as plt

# 创建一个简单的DataFrame
data = {
    'Month': ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
    'Sales': [200, 220, 210, 250, 270]}
df = pd.DataFrame(data)

# 打印数据表
print(df)

# 绘制销售趋势图
# 使用月份和销售数据绘制折线图
plt.plot(df['Month'], df['Sales'])
# 添加x轴标签
plt.xlabel('Month')
# 添加y轴标签
plt.ylabel('Sales')
# 添加图表标题
plt.title('Monthly Sales')
# 显示图表
plt.show()
