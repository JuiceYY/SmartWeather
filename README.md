# 酷欧天气(MVP版本)
本项目为<<第一行代码>>书末实战"酷欧天气"的重构版本. 
- 原书代码所有逻辑都在Activity和Fragment中处理, 耦合度高, 难于拓展和修改, 这里使用mvp模式进行了重构
- 原书中是使用okhttp做网络请求, 并且只是简单把okhttp封装了一个函数, 这里使用rxjava+Retrofit组合做网络请求, 增加了网络请求拦截器打印log, 统一事件订阅者的处理等
- greendao做数据库操作.
- 使用dagger依赖注入. 有了依赖注入后, 不需要再在类中手动new对象, 而是使用注入器注入对象, 当某个类改变时, 并不会影响到依赖它的类的代码.

## mvp模式

>> - view <--> presenter --> model, model通过接口回调操作presenter
>> - Android使用Activity将所有应用组件组合在一起呈现给用户并与用户产生交互, 然而如果把所有的代码都放在Activity中, 会使Activity过于复杂, 对项目的维护和功能拓展都不利. 所以mvp模式应运而生
>> - mvp即model, view, presenter. 我们经常让view对应activity或fragment, model对应app的数据层, presenter则负责做一些逻辑的处理
>> - 与mvc的区别: MVP中view与model没有直接交互, 即activity或fragmetn不需要知道数据是怎么来的. 数据层也不用管view如何显示和处理数据
>> - 更加优秀的参考案例: [android-architecture](https://github.com/googlesamples/android-architecture)

这里以选择城市功能为例:

- 通常可以定义一个Contract接口, 里面有Presenter和View两个内部接口, 这样可以使一个activity有哪些功能一目了然

- LocalCityModel和RemoteCityModel分别为本地获取城市信息和联网获取城市信息的类, presenter在需要的时候调用它们拿到城市数据, model通过接口回调将数据返回给presenter

- CityContract.Presenter 为选择城市的presenter, 当用户选择城市时, view便调用presenter的相关方法来获取城市数据, presenter便负责判断是从本地拿数据还是从网络拿数据, 并把网络数据存在本地数据库. 

- CityContract.View 为与用户交互的界面, 负责处理用户交互, 显示从presenter拿到的数据和显示错误信息

其他的以后再补充
